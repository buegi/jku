package prswe2.ss21.ue07.filesafe.server;

import static prswe2.ss21.ue07.filesafe.config.Configuration.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
                String clientName = msg.substring(6);
                if (!msg.startsWith(LOGIN)) {
                    System.out.println(LOGIN + "expected but received " + msg);
                    return;
                } else {
                    File userDir = new File(SERVER_ROOT + "//" + clientName);
                    if (!userDir.isDirectory()) {
                        userDir.mkdir();
                        System.out.println("User directory for " + clientName + " created!");
                    } else {
                        System.out.println("User directory for " + clientName + " already existent!");
                    }
                }
                send(out, OK_LOGIN + clientName);

                // TODO receive event
                msg = receive(in);
                if (msg.startsWith(E_CREATE) || msg.startsWith(E_MODIFY)) {
                    System.out.println(E_CREATE + " ok");
                    send(out, msg);
                    // TODO ack send file
                    msg = receive(in);
                    send(out, msg);

                    // TODO receive file name
                    msg = receive(in);
                    String fileName = msg;
                    send(out, msg);

                    // TODO receive & delete & create file
                    System.out.println("Receiving File: " + fileName);
                    if (Files.exists(Paths.get(SERVER_ROOT + "//" + clientName + "//" + fileName))) {
                        Files.delete(Paths.get(SERVER_ROOT + "//" + clientName + "//" + fileName));
                    }
                    BufferedWriter fileOutput = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(SERVER_ROOT + "//" + clientName + "//" + fileName)));
                    BufferedReader fileInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    do {
                        fileOutput.flush();
                        String x = fileInput.readLine();
                        String v = x.replace(END_OF_FILE, "");
                        fileOutput.write(v);
                    } while (fileInput.ready());
                    fileOutput.close();
                    msg = END_OF_FILE;

                    // TODO ack end of file
                    send(out, msg);

                } else if (msg.startsWith(E_DELETE)) {
                    System.out.println(E_DELETE + " ok");
                    send(out, msg);

                    // TODO receive filename
                    msg = receive(in);
                    String fileName = msg;
                    send(out, msg);

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

                msg = receive(in);
                if (!msg.startsWith(DONE)) {
                    System.out.println(DONE + " expected but received " + msg);
                    return;
                }
                send(out, BYE);
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
    }
}