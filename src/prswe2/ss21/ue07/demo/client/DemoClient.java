package prswe2.ss21.ue07.demo.client;

import static prswe2.ss21.ue07.demo.Constants.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DemoClient {

    public static DemoClient create(String name) throws IOException {
        return new DemoClient(name);
    }

    // -----

    private final String name;

    private DemoClient(String name) throws IOException {
        super();
        this.name = name;
    }

    public void start() throws IOException {
        new Thread(() -> {
            communicate();
        }).start();
    }

    private void communicate() {
        try (Socket socket = new Socket(SERVER, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream())) {
            String reply = receive(in);
            if (!reply.startsWith(HELO_FROM)) {
                System.out.println(HELO_FROM + "expected but received " + reply);
                return;
            }
            send(out, LOGIN + name);
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
}