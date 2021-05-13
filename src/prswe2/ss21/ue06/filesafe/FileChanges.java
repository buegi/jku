package prswe2.ss21.ue06.filesafe;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileChanges {

    private final List<Path> changedFiles;

    public FileChanges() {
        this.changedFiles = Collections.synchronizedList(new ArrayList<>());
    }

    protected void addSaveFile(Path saveFile) {
        this.changedFiles.add(saveFile);
    }

    protected void removeSaveFile(Path saveFile) {
        this.changedFiles.remove(saveFile);
    }

    protected List<Path> getChangedFiles() {
        return this.changedFiles;
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