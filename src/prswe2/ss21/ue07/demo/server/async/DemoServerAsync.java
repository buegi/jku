package prswe2.ss21.ue07.demo.server.async;

import static prswe2.ss21.ue07.demo.Constants.BYE;
import static prswe2.ss21.ue07.demo.Constants.DONE;
import static prswe2.ss21.ue07.demo.Constants.HELO_FROM;
import static prswe2.ss21.ue07.demo.Constants.LINE_SEP;
import static prswe2.ss21.ue07.demo.Constants.LOGIN;
import static prswe2.ss21.ue07.demo.Constants.OK_LOGIN;
import static prswe2.ss21.ue07.demo.Constants.PORT;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;


public class DemoServerAsync {

    private static final int TIMEOUT = 5000;
    private static Charset CSET = Charset.forName("UTF-8");

    private volatile boolean terminate = false;
    private final ServerSocketChannel server;

    private Selector selector;
    private Thread selectorThread;

    DemoServerAsync() throws IOException {
        server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(PORT));
        selector = Selector.open();
    }

    void start() throws IOException {

        System.out.println("DemoServerAsync started on port " + PORT);

        // selector
        this.selectorThread = new Thread(new SelectorRunnable());
        selectorThread.start();

        // accept client loop
        while (!terminate) {
            SocketChannel clientChannel = null;
            try {
                System.out.println("Server waiting to accept client");
                // -- blocking --------------------------------
                clientChannel = server.accept();
                // --------------------------------------------
                System.out.println("Server accepted client");
                clientChannel.configureBlocking(false);
                ClientHandler handler = new ClientHandler(clientChannel);
                SelectionKey key = clientChannel.register(selector, SelectionKey.OP_READ);
                key.attach(handler);
                selector.wakeup();
                handler.sayHello();
            } catch (AsynchronousCloseException ace) {
                terminate = true;
            } catch (IOException e) {
                System.out.println("ERROR in accepting client " + e.toString());
            }
        }
    }

    public void terminate() throws IOException {
        terminate = true;
        server.close();
        selector.close();
    }

    private class SelectorRunnable implements Runnable {

        @Override
        public void run() {
            while(! terminate) {
                try {
                    // -----------------------------
                    int n = selector.select(TIMEOUT);
                    // -----------------------------
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> keysIt = selectedKeys.iterator();
                    while (keysIt.hasNext()) {
                        SelectionKey key = keysIt.next();
                        if (key.isReadable()) {
                            ClientHandler handler = (ClientHandler)key.attachment();
                            handler.handleMessage(key);
                        }
//						keysIt.remove();
                    }
                } catch (Exception e) {
                    System.out.println("Exception in select " + e.toString());
                }
            }
        }
    }

    // -- client handler ------------

    enum State {
        START, LOGGED_IN
    }

    private class ClientHandler {

        private final SocketChannel channel;
        private final ByteBuffer buffer;
        private State state;

        ClientHandler(SocketChannel channel) {
            super();
            this.channel = channel;
            this.buffer = ByteBuffer.allocate(1024);
            this.state = State.START;
        }

        private void sayHello() throws IOException {
            writeMessage(HELO_FROM + server.toString());
        }

        private void handleMessage(SelectionKey key) throws IOException {
            String msg = readMessage();
            switch(state) {
                case START:
                    if (!msg.startsWith(LOGIN)) {
                        System.out.println(LOGIN + "expected but received " + msg);
                        return;
                    }
                    String clientName = msg.substring(6, msg.indexOf(LINE_SEP));
                    writeMessage(OK_LOGIN + clientName);
                    state = State.LOGGED_IN;
                    break;
                case LOGGED_IN:
                    if (!msg.startsWith(DONE)) {
                        System.out.println(DONE + " expected but received " + msg);
                        return;
                    }
                    writeMessage(BYE);
                    key.cancel();
                    channel.close();
                    break;
            }
        }

        private String readMessage() throws IOException {
            buffer.clear();
            @SuppressWarnings("unused")
            int r = channel.read(buffer);
            buffer.flip();
            String data = CSET.decode(buffer).toString();
            System.out.print(" <-- " + data);
            return data;
        }

        private void writeMessage(String msg) throws IOException {
            ByteBuffer bbuf = CSET.encode(msg + LINE_SEP);
            buffer.clear();
            buffer.put(bbuf);
            buffer.flip();
            channel.write(buffer);
            System.out.println(" --> " + msg);
        }

    }
}