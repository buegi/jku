package prswe2.ss21.ue07.filesafe.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

            // TODO send event
            send(out, action);
            reply = receive(in);
            if (!reply.startsWith(E_CREATE)) {
                System.out.println(E_CREATE + " expected but received " + reply);
                return;
            }

            // TODO Send Filename


            // TODO send File

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