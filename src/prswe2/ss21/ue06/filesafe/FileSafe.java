package prswe2.ss21.ue06.filesafe;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardWatchEventKinds.*;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class FileSafe {

    private static final int INITIAL_DELAY = 2;
    private static final int SAVE_INTERVAL = 5;                            // saving frequency
    private static final String FILES_GLOB = "glob:**.{java, html, txt}";   // file types to save

    private final Path src;                                                 // path that should be saved
    private final Path dst;

    private final FileChanges fileChanges;                                  // contains Info for Files to save

    private final PathMatcher pathMatcher;                                  /// matches file types

    private WatchService watchService;
    private Thread watchThread;
    private boolean runFileSafe = true;

    private SaveRunnable saveRunnable;
    private ScheduledExecutorService saveExecutor;

    public FileSafe(Path src, Path dst) {
        this.src = src;
        this.dst = dst;
        this.fileChanges = new FileChanges();
        this.pathMatcher = FileSystems.getDefault().getPathMatcher(FILES_GLOB);
        this.saveExecutor = Executors.newScheduledThreadPool(1);
        this.init();
        this.start();

    }

    private class SaveRunnable implements Runnable {
        @Override
        public void run() {
            try {
                fileChanges.getChangedFiles().forEach((p, e) -> {
                    if (e.kind() == ENTRY_CREATE || e.kind() == ENTRY_MODIFY) {
                        try {
                            Files.copy(p, dst.resolve(p.getFileName()), StandardCopyOption.COPY_ATTRIBUTES,
                                    StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                        fileChanges.removeSaveFile(p);
                        System.out.println("File: " + p.getFileName() + " saved!");
                    } else if (e.kind() == ENTRY_DELETE) {
                        try {
                            Files.delete(dst.resolve(p.getFileName()));
                            fileChanges.removeSaveFile(p);
                            System.out.println("File: " + p.getFileName() + " deleted!");
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void init() {
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

    protected void startSaver() {
        this.saveExecutor.scheduleAtFixedRate(this.saveRunnable, INITIAL_DELAY, SAVE_INTERVAL, TimeUnit.SECONDS);
    }

    public void stop() {
        this.runFileSafe = false;
        this.saveExecutor.shutdownNow();
        this.watchThread.interrupt();
        System.out.println("Program stopped/terminated!");
        System.exit(0);
    }

    private void startWatcher() {
        watchThread = new Thread(() -> {
            WatchKey key = null;
            // TODO initially copy all files to synchronize all changes while program was not running


            try {
                WatchKey k = this.src.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (runFileSafe) {
                try {
                    key = watchService.take();
                    for (WatchEvent<?> evt : key.pollEvents()) {
                        WatchEvent<Path> pvt = (WatchEvent<Path>) evt;
                        Path relPath = pvt.context();
                        Path dirPath = (Path) key.watchable();
                        Path absPath = dirPath.resolve(relPath);
                        if ((pvt.kind() == ENTRY_CREATE || pvt.kind() == ENTRY_MODIFY || pvt.kind() == ENTRY_DELETE)
                                && this.pathMatcher.matches(absPath) && !this.fileChanges.contains(absPath)) {
                            this.fileChanges.addSaveFile(absPath, evt);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (key != null) key.reset();
                }
                System.out.println(fileChanges);
            }
        });
        watchThread.start();
    }
}