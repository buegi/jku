package prswe2.ss21.ue07.filesafe.client;

import java.io.*;
import java.net.Socket;
import java.nio.file.Path;

import static prswe2.ss21.ue07.filesafe.protocol.Constants.*;

public class FileSafeClient {

    private final String loginName;

    public FileSafeClient(String loginName) throws IOException {
        super();
        this.loginName = loginName;
    }

    public static FileSafeClient create(String name) throws IOException {
        return new FileSafeClient(name);
    }

    // -----

    public void init() throws IOException, InterruptedException {
        FileSafeClient client = FileSafeClient.create(loginName);
        Thread clientThread = new Thread(() -> {
            try {
                client.start();
            } catch (IOException e) {
                System.out.println("Not able to start client " + loginName);
            }
        });

        clientThread.start();

        clientThread.join();
    }

    public void start() throws IOException {
        new Thread(() -> {
            //communicate();
        }).start();
    }


    public void communicate() {
        try (Socket socket = new Socket(SERVER, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream())) {
            String reply = receive(in);
            if (!reply.startsWith(HELO_FROM)) {
                System.out.println(HELO_FROM + "expected but received " + reply);
                return;
            }
            send(out, LOGIN + loginName);
            reply = receive(in);
            if (!reply.startsWith(OK_LOGIN)) {
                System.out.println(OK_LOGIN + "expected but received " + reply);
                return;
            }

            send(out, DONE);
            reply = receive(in);
            if (!reply.startsWith(BYE)) {
                System.out.println(BYE + "expected but received " + reply);
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void action(String action, Path file) {
        try (Socket socket = new Socket(SERVER, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream())) {
            // HELO
            String reply = receive(in);
            if (!reply.startsWith(HELO_FROM)) {
                System.out.println(HELO_FROM + "expected but received " + reply);
                return;
            }
            // LOGIN
            send(out, LOGIN + loginName);
            reply = receive(in);
            if (!reply.startsWith(OK_LOGIN)) {
                System.out.println(OK_LOGIN + "expected but received " + reply);
                return;
            }

            // ACTION CREATE OR CHANGE
            if (action.equals(E_CREATE) || action.equals(E_CHANGED)) {
                send(out, action);
                reply = receive(in);
                if (!reply.startsWith(E_CREATE)) {
                    System.out.println(E_CREATE + " expected but received " + reply);
                    return;
                }

                // SEND_FILE
                send(out, SEND_FILE);
                reply = receive(in);
                if (!reply.startsWith(SEND_FILE)) {
                    System.out.println(SEND_FILE + "expected but received " + reply);
                    return;
                }

                // send file name
                send(out, file.toString());
                reply = receive(in);
                if (!reply.startsWith(file.toString())) {
                    System.out.println(file + "expected but received " + reply);
                    return;
                }


                // TODO send File
                System.out.println("Sending file: " + file);
                FileInputStream fileInput = new FileInputStream(CLIENT_SOURCE + "//" + file);
                byte[] b = new byte[1024];
                fileInput.read(b, 0, b.length);
                OutputStream fileOutput = socket.getOutputStream();
                fileOutput.write(b, 0, b.length);
                fileInput.close();
            }

            // ACTION DELETE
            if (action.equals(E_DELETE)) {
                send(out, E_DELETE);
                reply = receive(in);
                if (!reply.startsWith(E_DELETE)) {
                    System.out.println(E_DELETE + "expected but received " + reply);
                }

                // send file name
                send(out, file.toString());
                reply = receive(in);
                if (!reply.startsWith(file.toString())) {
                    System.out.println(file + "expected but received " + reply);
                }
            }

            // DONE
            send(out, DONE);
            reply = receive(in);
            if (!reply.startsWith(BYE)) {
                System.out.println(BYE + "expected but received " + reply);
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}