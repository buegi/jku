package prswe2.ss21.ue07.filesafe;

import static prswe2.ss21.ue07.filesafe.protocol.Constants.*;

import prswe2.ss21.ue07.filesafe.server.AsyncFileSafeServer;
import prswe2.ss21.ue07.filesafe.server.FileSafeServer;
import prswe2.ss21.ue07.filesafe.server.SyncFileSafeServer;
import prswe2.ss21.ue07.inout.In;

import java.io.IOException;

public class FileSafeMain {

    public static void main(String[] args) {

        String loginName = "Stefan";

        // just for ease of use the preferred server type is started on same machine
        // this can be removed and ServerMain can be started manually on another machine
        FileSafeServer fileSafeServer;
        if (SYNC_SERVER) {
            fileSafeServer = new SyncFileSafeServer();
        } else {
            fileSafeServer = new AsyncFileSafeServer();
        }
        try {
            fileSafeServer.start();
        } catch (IOException e) {
            System.out.println("Server couldn't be started!");
        }

        // start file safe client
        FileSafe filesafe = new FileSafe(loginName);

        for (; ; ) {
            // UE06 Tutor Feedback: inform user how to stop -0,5 CORRECTED
            System.out.println("You can always stop program with x [ENTER]");
            char c = In.readChar();
            if (c == 'x') {
                filesafe.stop();
                // can also be removed, if server is running on another machine
                try {
                    fileSafeServer.terminate();
                } catch (IOException e) {
                    System.out.println("Server couldn't be stopped!");
                }

            }
        }
    }
}