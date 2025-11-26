package Level1.E1;

import java.io.File;
import java.util.Arrays;

public class Directory {
    public static String[] sortAlphabetically(File directory) {
        String[] files = directory.list();
        if (files == null || files.length == 0) {
            throw new IllegalArgumentException("Must have at least one file");
        }
        Arrays.sort(files, String::compareToIgnoreCase);
        return files;
    }
}