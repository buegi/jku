package prswe2.ss21.ue07.filesafe.server;

import static prswe2.ss21.ue07.filesafe.protocol.Constants.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class SyncFileSafeServer extends FileSafeServer {

    private ServerSocket server;

    public SyncFileSafeServer() {
        try {
            server = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("Couldn't initialize server port!");
        }
    }

    @Override
    public void start() throws IOException {
        while (!terminate) {
            try {
                System.out.println("FileSafeSyncServer waiting to accept client");
                Socket clientSocket = server.accept();
                System.out.println("FileSafeSyncServer accepted client");
                new Thread(new SyncFileSafeServer.ClientHandler(clientSocket)).start();
            } catch (SocketException se) {
                System.out.println("FileSafeSyncServer closed with " + se.toString());
            }
        }
    }

    @Override
    public void terminate() throws IOException {
        terminate = true;
        server.close();
    }


    private class ClientHandler implements Runnable {

        private final Socket socket;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            try (PrintWriter out = new PrintWriter(socket.getOutputStream());
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            ) {
                send(out, HELO_FROM + server.toString());
                String msg = receive(in);
                if (!msg.startsWith(LOGIN)) {
                    System.out.println(LOGIN + "expected but received " + msg);
                    return;
                }
                String clientName = msg.substring(6);
                send(out, OK_LOGIN + clientName);

                // TODO receive event
//                msg = receive(in);
//                if (!msg.startsWith(E_CREATE) || !msg.startsWith(E_CHANGED) || !msg.startsWith(E_DELETE)) {
//                    System.out.println(E_CREATE + " expected but received " + msg);
//                    return;
//                }
//                send(out, msg);

                msg = receive(in);
                if (!msg.startsWith(DONE)) {
                    System.out.println(DONE + " expected but received " + msg);
                    return;
                }
                send(out, BYE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}