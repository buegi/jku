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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DemoServerAsync {

    private static final int TIMEOUT = 5000;
    static Charset CSET = Charset.forName("UTF-8");

    private volatile boolean terminate = false;
    private ServerSocketChannel server;

    private Selector selector;
    private Thread selectorThread;

    DemoServerAsync() throws IOException {

    }

    void start() throws IOException {

        // selector
        this.selectorThread = new Thread(new SelectorRunnable());
        selectorThread.start();

        // accept client loop
        while (!terminate) {
            System.out.println("Server waiting to accept client");
            SocketChannel clientChannel = null;
            try {

            } catch (Exception e) {
                System.out.println("Exception " + e.toString());
            }
        }
    }

    public void terminate() throws IOException {
        terminate = true;
        server.close();
    }

    private class SelectorRunnable implements Runnable {

        @Override
        public void run() {
            try {

            } catch (Exception e) {
                System.out.println("Exception " + e.toString());
            }
        }

    }

    // -- client handler ------------

    private class ClientHandler {

        final SocketChannel channel;
        final ByteBuffer buffer;

        public ClientHandler(SocketChannel channel) {
            super();
            this.channel = channel;
            this.buffer = ByteBuffer.allocate(1024);
        }
    }
}