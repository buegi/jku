package prswe2.ss21.ue07.demo.server;

import static prswe2.ss21.ue07.filesafe.config.Configuration.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class DemoServer {

    private volatile boolean terminate = false;
    private final ServerSocket server;

    DemoServer() throws IOException {
        server = new ServerSocket(PORT);
    }

    void start() throws IOException {
        while (!terminate) {
            try {
                System.out.println("Server waiting to accept client");
                Socket clientSocket = server.accept();
                System.out.println("Server accepted client");
                new Thread(new ClientHandler(clientSocket)).start();
            } catch (SocketException se) {
                System.out.println("Server closed with " + se.toString());
            }
        }
    }

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