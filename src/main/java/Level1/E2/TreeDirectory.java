package Level1.E2;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class TreeDirectory {


    private static void sortAlphabetically(File directory, int ident) {
        File[] files = directory.listFiles();
        Arrays.sort(files, Comparator.comparing(File::getName, String.CASE_INSENSITIVE_ORDER));
        for (File file : files) {
            printTreeDirectory(file, ident);
            if (file.isDirectory()) {
                sortAlphabetically(file, ident + 1);
            }
        }
    }

    private static void printTreeDirectory(File file, int ident) {

    }

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
