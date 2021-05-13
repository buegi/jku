package prswe2.ss21.ue06.filesafe;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

import static java.nio.file.StandardWatchEventKinds.*;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class FileSafe {

    private static final int SAVE_INTERVAL = 10;                            // saving frequency
    private static final String FILES_GLOB = "glob:**{java, html, txt}";    // file types to save

    private final Path src;                                                 // path that should be saved
    private final Path dst;

    private final FileChanges fileChanges;                                  // contains Info for Files to save

    private WatchService watchService;
    private Thread watchThread;
    private boolean stopWatcherThread;


    public FileSafe(Path src, Path dst) {
        this.src = src;
        this.dst = dst;
        this.fileChanges = new FileChanges();
        this.init();
        this.start();

    }

    private void init() {
        this.stopWatcherThread = false;
        try {
            this.watchService = FileSystems.getDefault().newWatchService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected void start() {
        this.startWatcher();
        this.startSaver();
    }

    protected void stop() {
        this.stopWatcher();
        this.stopSaver();
    }


    private void startWatcher() {
        watchThread = new Thread(() -> {
            WatchKey key = null;
            int counter = 0;
            while (!stopWatcherThread) {
                System.out.println(counter);
                try {
                    try {
                        WatchKey k = this.src.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
                    } catch (IOException e) {
                        System.out.println("registerWatcherPath");
                        e.printStackTrace();
                    }
                    key = watchService.take();
                    for (WatchEvent<?> evt : key.pollEvents()) {
                        WatchEvent<Path> pevt = (WatchEvent<Path>) evt;
                        Path relPath = pevt.context();
                        Path dirPath = (Path) key.watchable();
                        Path absPath = dirPath.resolve(relPath);
                        if (pevt.kind() == ENTRY_CREATE || pevt.kind() == ENTRY_MODIFY || pevt.kind() == ENTRY_DELETE) {
                            this.fileChanges.addSaveFile(absPath);
                        }

                    }
                } catch (InterruptedException e) {
                    System.out.println("startWatcher");
                    e.printStackTrace();
                } finally {
                    if (key != null) key.reset();
                }
                System.out.println(fileChanges);
                counter++;
            }
        });
        watchThread.start();
    }

    protected void stopWatcher() {
        this.stopWatcherThread = true;
    }


    protected void startSaver() {
        // TODO
    }

    protected void stopSaver() {
        // TODO
    }
}