package prswe2.ss21.ue07.filesafe.client;

import java.io.IOException;
import java.nio.file.Path;

public class AsyncFileSafeClient extends FileSafeClient {

    public AsyncFileSafeClient(String loginName) throws IOException {
        super(loginName);
    }

    public static AsyncFileSafeClient create(String name) throws IOException {
        return new AsyncFileSafeClient(name);
    }

    @Override
    public void communicate(String action, Path file) {

    }
}