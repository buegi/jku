package prswe2.ss21.ue07.filesafe.client;

import java.io.*;
import java.net.Socket;
import java.nio.file.Path;

import static prswe2.ss21.ue07.filesafe.protocol.Constants.*;

public class SyncFileSafeClient extends FileSafeClient {

    public SyncFileSafeClient(String loginName) throws IOException {
        super(loginName);
    }

    public static SyncFileSafeClient create(String name) throws IOException {
        return new SyncFileSafeClient(name);
    }

    // -----

    public void init() throws IOException, InterruptedException {
        SyncFileSafeClient client = SyncFileSafeClient.create(super.getLoginName());
        Thread clientThread = new Thread(() -> {
            try {
                client.start();
            } catch (IOException e) {
                System.out.println("Not able to start client " + super.getLoginName());
            }
        });
        clientThread.start();
        clientThread.join();
    }

    public void start() throws IOException {
        new Thread(() -> {
        }).start();
    }

    public void communicate(String action, Path file) {
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
            send(out, LOGIN + super.getLoginName());
            reply = receive(in);
            if (!reply.startsWith(OK_LOGIN)) {
                System.out.println(OK_LOGIN + "expected but received " + reply);
                return;
            }

            // ACTION CREATE OR CHANGE
            if (action.equals(E_CREATE) || action.equals(E_MODIFY)) {
                send(out, action);
                reply = receive(in);
                if (!(reply.startsWith(E_CREATE) || (reply.startsWith(E_MODIFY)))) {
                    System.out.println(E_CREATE + " or " + E_MODIFY + " expected but received " + reply);
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

                // send File
                System.out.println("Sending file: " + file);
                BufferedReader fileInput = new BufferedReader(new FileReader(CLIENT_SOURCE + "//" + file));
                BufferedWriter fileOutput = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                while (fileInput.ready()) {
                    fileOutput.flush();
                    String x = fileInput.readLine();
                    fileOutput.write(x);
                }
                fileInput.close();

                // send end of file
                send(out, END_OF_FILE);
                reply = receive(in);
                if (!reply.startsWith(END_OF_FILE)) {
                    System.out.println(END_OF_FILE + " expected but received " + reply);
                    return;
                }
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