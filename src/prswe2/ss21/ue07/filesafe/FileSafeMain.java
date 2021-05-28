package prswe2.ss21.ue07.filesafe;

import prswe2.ss21.ue07.inout.In;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSafeMain {

    public static void main(String[] args) {

        Path src = Paths.get("C://dev//test//client");
        Path dst = Paths.get("C://dev//test//server");
        String loginName = "Stefan";
        // select server type here: true for synchronous/blocking server, false for asynchronous/non-blocking
        boolean syncServer = true;

        FileSafe filesafe = new FileSafe(src, dst, syncServer);

        for (; ; ) {
            // UE06 Tutor Feedback: inform user how to stop -0,5 CORRECTED
            System.out.println("You can always stop program with x [ENTER]");
            char c = In.readChar();
            if (c == 'x') {
                filesafe.stop();
            }
        }
    }
}