package prswe2.ss21.ue06.filesafe;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardWatchEventKinds.*;

public class FileSafe {

    private static final int INITIAL_DELAY = 2;
    private static final int SAVE_INTERVAL = 10;                            // saving frequency
    private static final String FILES_GLOB = "glob:**.{java,xml,txt}";      // file types to save

    private final Path src;                                                 // path that should be saved
    private final Path dst;                                                 // path that should be saved

    private final FileChanges fileChanges;                                  // contains Info for Files to save

    private final PathMatcher pathMatcher;                                  /// matches file types

    private WatchService watchService;
    private Thread watchThread;
    private boolean runFileSafe = true;

    private FileSafe.SaveRunnable saveRunnable;
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
                    if (e == ENTRY_CREATE || e == ENTRY_MODIFY) {
                        try {
                            Files.copy(p, dst.resolve(p.getFileName()), StandardCopyOption.COPY_ATTRIBUTES,
                                    StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("File: " + p.getFileName() + " saved!");
                        } catch (IOException ioe) {
                            System.out.println("File: " + p.getFileName() + " not found (anymore)!");
                        }
                        // UE06 Tutor Feedback: remove only if successful -1 CORRECTED
                        // COMMENT file should be removed from change queue either way, otherwise created and instantly
                        // deleted file would stay in queue
                        fileChanges.removeSaveFile(p);
                    } else if (e == ENTRY_DELETE) {
                        try {
                            // UE06 Tutor Feedback: deleteIfExists -0,5 CORRECTED
                            if (Files.exists(dst.resolve(p.getFileName()))) {
                                Files.delete(dst.resolve(p.getFileName()));
                                System.out.println("File: " + p.getFileName() + " deleted!");
                            } else {
                                System.out.println("File doesn't exists anymore - no delete action needed!");
                            }
                            // remove from queue either way - we don't want to try multiple times, if file doesn't exist anymore
                            fileChanges.removeSaveFile(p);
                            System.out.println("File: " + p.getFileName() + " removed from queue!");
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
        this.saveRunnable = new FileSafe.SaveRunnable();
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
            // Initial sync to sync all changes that were made while program was not running
            try {
                Files.walk(src).forEach(f -> {
                    if (this.pathMatcher.matches(f)) {
                        // UE06 Tutor Feedback: don't use WatchEvent raw -0,5 CORRECTED
                        this.fileChanges.addSaveFile(f, ENTRY_CREATE);
                        System.out.println(f);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                // UE06 Tutor Feedback: k is assigned but never accessed -1 CORRECTED
                this.src.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
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
                            this.fileChanges.addSaveFile(absPath, (WatchEvent.Kind<Path>) evt.kind());
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