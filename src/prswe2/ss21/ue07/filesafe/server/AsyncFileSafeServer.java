package prswe2.ss21.ue07.filesafe.server;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;

import static prswe2.ss21.ue07.filesafe.config.Configuration.*;

public class AsyncFileSafeServer extends FileSafeServer {

    private static final int TIMEOUT = 5000;
    private static Charset CSET = Charset.forName("UTF-8");

    private volatile boolean terminate = false;
    private ServerSocketChannel server;

    private Selector selector;
    private Thread selectorThread;

    public AsyncFileSafeServer() {
        try {
            server = ServerSocketChannel.open();
            server.socket().bind(new InetSocketAddress(PORT));
            selector = Selector.open();
        } catch (IOException e) {
            System.out.println("Couldn't initialize server port!");
        }
    }

    @Override
    public void start() throws IOException {

        System.out.println("DemoServerAsync started on port " + PORT);

        // selector
        this.selectorThread = new Thread(new AsyncFileSafeServer.SelectorRunnable());
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
                AsyncFileSafeServer.ClientHandler handler = new AsyncFileSafeServer.ClientHandler(clientChannel);
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
            while (!terminate) {
                try {
                    // -----------------------------
                    int n = selector.select(TIMEOUT);
                    // -----------------------------
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> keysIt = selectedKeys.iterator();
                    while (keysIt.hasNext()) {
                        SelectionKey key = keysIt.next();
                        if (key.isReadable()) {
                            AsyncFileSafeServer.ClientHandler handler = (AsyncFileSafeServer.ClientHandler) key.attachment();
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
        private AsyncFileSafeServer.State state;

        ClientHandler(SocketChannel channel) {
            super();
            this.channel = channel;
            this.buffer = ByteBuffer.allocate(1024);
            this.state = AsyncFileSafeServer.State.START;
        }

        private void sayHello() throws IOException {
            writeMessage(HELO_FROM + server.toString());
        }

        private void handleMessage(SelectionKey key) throws IOException {
            String msg = readMessage();
            String clientName="";
            switch (state) {
                case START:
                    if (!msg.startsWith(LOGIN)) {
                        System.out.println(LOGIN + "expected but received " + msg);
                        return;
                    }
                    clientName = msg.substring(6, msg.indexOf(LINE_SEP));
                    File userDir = new File(SERVER_ROOT + "//" + clientName);
                    if (!userDir.isDirectory()) {
                        userDir.mkdir();
                        System.out.println("User directory for " + clientName + " created!");
                    } else {
                        System.out.println("User directory for " + clientName + " already existent!");
                    }
                    writeMessage(OK_LOGIN + clientName);
                    state = AsyncFileSafeServer.State.LOGGED_IN;
                    break;
                case LOGGED_IN:
                    // TODO receive event
                    msg = readMessage();
                    if (msg.startsWith(E_CREATE) || msg.startsWith(E_MODIFY)) {
                        System.out.println(E_CREATE + " ok");
                        writeMessage(msg);
                        // TODO ack send file
                        msg = readMessage();
                        writeMessage(msg);

                        // TODO receive file name
                        msg = readMessage();
                        String fileName = msg;
                        writeMessage(msg);

                        // TODO receive & delete & create file
                        System.out.println("Receiving File: " + fileName);
                        if (Files.exists(Paths.get(SERVER_ROOT + "//" + clientName + "//" + fileName))) {
                            Files.delete(Paths.get(SERVER_ROOT + "//" + clientName + "//" + fileName));
                        }
                        BufferedWriter fileOutput = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(SERVER_ROOT + "//" + clientName + "//" + fileName)));
                        BufferedReader fileInput = new BufferedReader(new InputStreamReader(Channels.newInputStream(channel)));
                        do {
                            fileOutput.flush();
                            String x = fileInput.readLine();
                            String v = x.replace(END_OF_FILE, "");
                            fileOutput.write(v);
                            fileOutput.flush();
                        } while (fileInput.ready());
                        fileOutput.close();
                        msg = END_OF_FILE;

                        // TODO ack end of file
                        writeMessage(msg);

                    } else if (msg.startsWith(E_DELETE)) {
                        System.out.println(E_DELETE + " ok");
                        writeMessage(msg);

                        // TODO receive filename
                        msg = readMessage();
                        String fileName = msg;
                        writeMessage(msg);

                        // TODO delete File
                        try {
                            if (Files.exists(Paths.get(SERVER_ROOT + "//" + clientName).resolve(fileName))) {
                                Files.delete(Paths.get(SERVER_ROOT + "//" + clientName + "//" + fileName));
                                System.out.println("File: " + fileName + " deleted!");
                            } else {
                                System.out.println("File doesn't exists anymore - no delete action needed!");
                            }
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }

                    } else {
                        System.out.println(E_CREATE + " or " + E_MODIFY + " or " + E_DELETE + "expected but received " + msg);
                        return;
                    }
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