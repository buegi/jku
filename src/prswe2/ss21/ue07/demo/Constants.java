package prswe2.ss21.ue07.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Constants {

    public static final String SERVER = "localhost";
    public static final int PORT = 9876;

    public static final String HELO_FROM = "HELO FROM ";
    public static final String LOGIN = "LOGIN ";
    public static final String OK_LOGIN = "OK LOGIN ";
    public static final String DONE = "DONE ";
    public static final String BYE = "BYE ";
    public static final String LINE_SEP = "\r\n";

    private Constants() {
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