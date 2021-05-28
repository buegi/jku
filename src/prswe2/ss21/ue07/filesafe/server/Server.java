package prswe2.ss21.ue07.filesafe.server;

import java.nio.file.Path;

public abstract class Server {

    private final Path dst;

    Server(Path dst) {
        this.dst = dst;
    }

    public abstract void start();

    public abstract void terminate();

}