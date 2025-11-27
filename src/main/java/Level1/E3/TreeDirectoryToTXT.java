package Level1.E3;

import java.io.*;

public class TreeDirectoryToTXT {

    public static void listTreeDirectory(File directory, File output) throws IOException {
        validateDirectory(directory);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))) {
            sortAlphabetically(directory, 0, writer);
        }
    }

    private static void sortAlphabetically(File directory, int ident, BufferedWriter writer) throws IOException {

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
