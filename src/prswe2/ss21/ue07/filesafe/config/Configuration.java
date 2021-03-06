package prswe2.ss21.ue07.filesafe.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Configuration {

    public static final String SERVER = "localhost";
    public static final int PORT = 9876;

    // select server type here: true for synchronous/blocking server, false for asynchronous/non-blocking
    public static final boolean SYNC_SERVER = true;

    public static final Path CLIENT_SOURCE = Paths.get("C://dev//test//client");
    public static final Path SERVER_ROOT = Paths.get("C://dev//test//server");

    public static final String HELO_FROM = "HELO FROM ";
    public static final String LOGIN = "LOGIN ";
    public static final String OK_LOGIN = "OK LOGIN ";
    public static final String DONE = "DONE ";
    public static final String BYE = "BYE ";
    public static final String LINE_SEP = "\r\n";
    public static final String E_CREATE = "ENTRY_CREATE";
    public static final String E_MODIFY = "ENTRY_MODIFY";
    public static final String E_DELETE = "ENTRY_DELETE";
    public static final String SEND_FILE = "SEND FILE";
    public static final String END_OF_FILE = "END_OF_FILE";

    private Configuration() {
    }


    /**
     * Receives a line from BufferedReader in
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static String receive(BufferedReader in) throws IOException {
        StringBuilder b = new StringBuilder();
        b.append(in.readLine());
        System.out.println(" <-- " + b);
        return b.toString();
    }

    /**
     * Sends a line to PrintWriter out
     *
     * @param out
     * @param str
     * @throws IOException
     */
    public static void send(PrintWriter out, String str) throws IOException {
        out.print(str);
        System.out.println("--> " + str);
        out.print("\r\n");
        out.flush();
    }
}