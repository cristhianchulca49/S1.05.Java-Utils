package Level1.E5;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DirectorySerialized {
    public static void directoryToFile(File directory, File pathNameTXTFile) throws IOException {
        validateDirectory(directory);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathNameTXTFile, true))) {
            writeDirectoryTree(directory, writer, 0);
        }
    }

    private static void writeDirectoryTree(File dir, BufferedWriter writer, int level) throws IOException {
        File[] files = dir.listFiles();
        if (files == null) return;

        Arrays.sort(files, Comparator.comparing(f -> f.getName().toLowerCase()));

        for (File f : files) {
            String type = f.isDirectory() ? "D" : "F";
            String date = Files.getLastModifiedTime(f.toPath())
                    .toInstant()
                    .toString()
                    .replace("T", " ")
                    .substring(0, 19);
            String indent = "    ".repeat(level);
            writer.write(indent + type + " - " + f.getName() + " | " + date);
            writer.newLine();

            if (f.isDirectory()) {
                writeDirectoryTree(f, writer, level + 1);
            }
        }
    }

    private static void validateDirectory(File directory) {
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Directory does not valid");
        }
    }

    public static List<String> readTxtTFile(Path path) throws IOException {
        List<String> fileRead = Files.readAllLines(path);
        fileRead.forEach(System.out::println);
        return fileRead;
    }
}
