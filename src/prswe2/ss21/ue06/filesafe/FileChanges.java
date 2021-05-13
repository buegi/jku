package prswe2.ss21.ue06.filesafe;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileChanges {

    private final List<Path> changedFiles;

    public FileChanges() {
        this.changedFiles = new ArrayList<>();
    }

    protected void addSaveFile(Path saveFile) {
        System.out.println("Add Saved File: " + saveFile);
        System.out.println(this);
        this.changedFiles.add(saveFile);
    }

    protected void removeSaveFile(Path saveFile) {
        System.out.println("Remove Saved File: " + saveFile);
        System.out.println(this);
        this.changedFiles.remove(saveFile);
    }

    protected List<Path> getChangedFiles() {
        System.out.println("Get Changed Files: ");
        System.out.println(this);
        return this.changedFiles;
    }

    protected boolean contains(Path path) {
        return this.changedFiles.contains(path);
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{Path: ");
        this.changedFiles.forEach(p -> sb.append(p).append(", "));
        sb.delete(sb.length() - 2, sb.length());
        sb.append("}");
        return sb.toString();
    }
}