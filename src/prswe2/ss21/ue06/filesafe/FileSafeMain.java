package prswe2.ss21.ue06.filesafe;

import swe2.inout.In;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSafeMain {

    public static void main(String[] args) {

        Path src = Paths.get("C://dev//intellij//git//jku//src//prswe2//ss21//ue06//src");
        Path dst = Paths.get("C://dev//intellij//git//jku//src//prswe2//ss21//ue06//dst");

        FileSafe filesafe = new FileSafe(src, dst);

        for (; ; ) {
            char c = In.readChar();
            if (c == 'x') {
                filesafe.stop();
            }
        }
    }
}