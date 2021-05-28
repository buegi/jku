package prswe2.ss21.ue07.filesafe.server;

import static prswe2.ss21.ue07.filesafe.protocol.Constants.*;

import java.io.IOException;

public abstract class FileSafeServer {

    volatile boolean terminate = false;

    FileSafeServer() {
    }

    public abstract void start() throws IOException;

    public abstract void terminate() throws IOException;

}