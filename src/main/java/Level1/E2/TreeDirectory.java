package Level1.E2;

import java.io.File;

public class TreeDirectory {

    private static void validateDirectory(File directory) {
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Directory does not valid");
        }
        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            throw new IllegalArgumentException("Directory is empty");
        }
    }
}
