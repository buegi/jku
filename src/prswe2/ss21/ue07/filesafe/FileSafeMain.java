package prswe2.ss21.ue07.filesafe;

import prswe2.ss21.ue07.filesafe.protocol.Constants;
import prswe2.ss21.ue07.filesafe.server.AsyncServer;
import prswe2.ss21.ue07.filesafe.server.Server;
import prswe2.ss21.ue07.filesafe.server.SyncServer;
import prswe2.ss21.ue07.inout.In;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSafeMain {

    public static void main(String[] args) {

        Path src = Paths.get("C://dev//test//client");
        Path dst = Paths.get("C://dev//test//server");
        String loginName = "Stefan";

        // just for ease of use the preferred server type is started on same machine
        // this can be removed and ServerMain can be started manually on another machine
        Server server;
        if (Constants.SYNC_SERVER) {
            server = new SyncServer(dst);
        } else {
            server = new AsyncServer(dst);
        }
        server.start();

        // start file safe client
        FileSafe filesafe = new FileSafe(src, loginName);

        for (; ; ) {
            // UE06 Tutor Feedback: inform user how to stop -0,5 CORRECTED
            System.out.println("You can always stop program with x [ENTER]");
            char c = In.readChar();
            if (c == 'x') {
                filesafe.stop();
                // can also be removed, if server is running on another machine
                server.terminate();
            }
        }
    }
}