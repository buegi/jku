package prswe2.ss21.ue06.filesafe;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.nio.file.StandardWatchEventKinds.*;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class FileSafe {

    private static final int SAVE_INTERVAL = 10;                            // saving frequency
    private static final String FILES_GLOB = "glob:**.{java, html, txt}";   // file types to save

    private final Path src;                                                 // path that should be saved
    private final Path dst;

    private final FileChanges fileChanges;                                  // contains Info for Files to save

    private final PathMatcher pathMatcher;                                  /// matches file types

    private WatchService watchService;
    private Thread watchThread;
    private boolean stopWatcherThread;

    private SaveRunnable saveRunnable;
    private ScheduledExecutorService saveExecutor;

    public FileSafe(Path src, Path dst) {
        this.src = src;
        this.dst = dst;
        this.fileChanges = new FileChanges();
        this.pathMatcher = FileSystems.getDefault().getPathMatcher(this.FILES_GLOB);
        this.saveExecutor = Executors.newScheduledThreadPool(1);
        this.init();
        this.start();

    }

    private class SaveRunnable implements Runnable {
        @Override
        public void run() {
            fileChanges.getChangedFiles().forEach(f -> {
                try {
                    Files.copy(f, Paths.get(String.valueOf(dst)), StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
                    fileChanges.removeSaveFile(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }


    private void init() {
        this.stopWatcherThread = false;
        try {
            this.watchService = FileSystems.getDefault().newWatchService();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.saveRunnable = new SaveRunnable();
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
                        if ((pevt.kind() == ENTRY_CREATE || pevt.kind() == ENTRY_MODIFY || pevt.kind() == ENTRY_DELETE)
                                && this.pathMatcher.matches(absPath) && !this.fileChanges.contains(absPath)) {
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
        this.saveExecutor.schedule(this.saveRunnable, SAVE_INTERVAL, TimeUnit.SECONDS);
    }

    protected void stopSaver() {
        // TODO
    }
}