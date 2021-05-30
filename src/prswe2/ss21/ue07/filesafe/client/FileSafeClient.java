package prswe2.ss21.ue07.filesafe.client;

import java.io.IOException;
import java.nio.file.Path;

public abstract class FileSafeClient {

    private final String loginName;

    public FileSafeClient(String loginName) throws IOException {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public abstract void communicate(String action, Path file);
}