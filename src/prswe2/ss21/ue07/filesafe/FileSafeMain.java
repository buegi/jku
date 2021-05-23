package prswe2.ss21.ue07.filesafe;

import prswe2.ss21.ue07.inout.In;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSafeMain {

    public static void main(String[] args) {

        Path src = Paths.get("C://dev//test//src");
        Path dst = Paths.get("C://dev//test//dst");

        FileSafe filesafe = new FileSafe(src, dst);

        for (; ; ) {
            char c = In.readChar();
            if (c == 'x') {
                filesafe.stop();
            }
        }
    }
}