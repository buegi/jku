package prswe2.ss21.ue07.filesafe.server;

import static prswe2.ss21.ue07.filesafe.config.Configuration.*;

public class FileSafeServerMain {

    public static void main(String[] args) {

        FileSafeServer fileSafeServer;
        if (SYNC_SERVER) {
            fileSafeServer = new SyncFileSafeServer();
        } else {
            fileSafeServer = new AsyncFileSafeServer();
        }
        fileSafeServer.init();
    }
}