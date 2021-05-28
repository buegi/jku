package prswe2.ss21.ue06.filesafe;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileChanges {

    // UE06 Tutor Feedback: don't use WatchEvent raw -1 [CORRECTED]
    private final ConcurrentHashMap<Path, WatchEvent.Kind<Path>> changedFiles;

    public FileChanges() {
        this.changedFiles = new ConcurrentHashMap<>();
    }

    protected void addSaveFile(Path saveFile, WatchEvent.Kind<Path> watchKind) {
        System.out.println("Add Changed File to Queue: " + saveFile + " ActionType: " + watchKind);
        System.out.println("Actual FileChange Queue: " + this);
        this.changedFiles.put(saveFile, watchKind);
    }

    protected void removeSaveFile(Path saveFile) {
        System.out.println("Remove Saved File from Queue: " + saveFile);
        System.out.println("Actual FileChange Queue: " + this);
        this.changedFiles.remove(saveFile);
    }

    protected Map<Path, WatchEvent.Kind<Path>> getChangedFiles() {
        System.out.println("Actual FileChange Queue: " + this);
        return this.changedFiles;
    }

    protected boolean contains(Path path) {
        return this.changedFiles.containsKey(path);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Changed Paths: {");
        this.changedFiles.forEach((p, e) -> sb.append(p).append(", "));
        if (this.changedFiles.size() > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("}");
        return sb.toString();
    }
}