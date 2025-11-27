package Level1.E3;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class TreeDirectoryToTXT {

    public static void listTreeDirectory(File directory, File output) throws IOException {
        validateDirectory(directory);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))) {
            sortAlphabetically(directory, 0, writer);
        }
    }

    private static void sortAlphabetically(File directory, int ident,  BufferedWriter writer) throws IOException {
        File[] files = directory.listFiles();
        Arrays.sort(files, Comparator.comparing(File::getName, String.CASE_INSENSITIVE_ORDER));
        for (File file : files) {
            writeFileInfo(file, ident, writer);
            if (file.isDirectory()) {
                sortAlphabetically(file, ident + 1, writer);
            }
        }
    }

    private static void writeFileInfo(File file, int ident, BufferedWriter writer) throws IOException {
        String level = "    ".repeat(ident);
        String type = file.isDirectory() ? "D" : "F";
        String name = file.getName();
        String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(file.lastModified());

        writer.write(level + type + " - " + name + " | " + date);
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
