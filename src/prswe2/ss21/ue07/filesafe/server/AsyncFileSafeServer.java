package prswe2.ss21.ue07.filesafe.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class AsyncFileSafeServer extends FileSafeServer {

    private static final int TIMEOUT = 5000;
    static Charset CSET = Charset.forName("UTF-8");

    private ServerSocketChannel server;

    private Selector selector;
    private Thread selectorThread;

    public AsyncFileSafeServer() {
    }

    @Override
    public void start() throws IOException {

        // selector
        this.selectorThread = new Thread(new AsyncFileSafeServer.SelectorRunnable());
        selectorThread.start();

        // accept client loop
        while (!terminate) {
            System.out.println("FileSafeAsyncServer waiting to accept client");
            SocketChannel clientChannel = null;
            try {

            } catch (Exception e) {
                System.out.println("Exception " + e.toString());
            }
        }
    }

    @Override
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