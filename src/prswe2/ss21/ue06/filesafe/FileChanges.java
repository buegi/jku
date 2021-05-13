package prswe2.ss21.ue06.filesafe;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class FileChanges {

    private final ConcurrentHashMap<Path, WatchEvent> changedFiles;

    public FileChanges() {
        this.changedFiles = new ConcurrentHashMap<>();
    }

    protected void addSaveFile(Path saveFile, WatchEvent watchEvent) {
        System.out.println("Add Saved File: " + saveFile + "WatchEvent: " + watchEvent);
        System.out.println(this);
        this.changedFiles.put(saveFile, watchEvent);
    }

    protected void removeSaveFile(Path saveFile) {
        System.out.println("Remove Saved File: " + saveFile);
        System.out.println(this);
        this.changedFiles.remove(saveFile);
    }

    protected Map<Path, WatchEvent> getChangedFiles() {
        System.out.println("Get Changed Files: ");
        System.out.println(this);
        return this.changedFiles;
    }

    protected boolean contains(Path path) {
        return this.changedFiles.containsKey(path);
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{Path: ");
        this.changedFiles.forEach((p, e) -> sb.append(p).append(", ").append(e.toString()).append(", "));
        sb.delete(sb.length() - 2, sb.length());
        sb.append("}");
        return sb.toString();
    }
}