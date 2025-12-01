package Level1.E5;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;


public class DirectorySerialized {
    public static void directoryToFile(Path directory, Path pathNameTXTFile) throws IOException {
        validateDirectory(directory);
        writeTXTFile(sortAlphabetically(directory), pathNameTXTFile);
    }

    private static List<Path> sortAlphabetically(Path directory) throws IOException {
        try (Stream<Path> filesDirectory = Files.walk(directory)) {
            return filesDirectory
                    .sorted(Comparator.comparing(file -> file.getFileName().toString(), String.CASE_INSENSITIVE_ORDER))
                    .toList();
        }
    }

    private static void writeTXTFile(List<Path> directory, Path pathName) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(pathName)) {
            for (Path path : directory) {
                String type = Files.isDirectory(path) ? "D" : "F";
                String name = path.getFileName().toString();
                String lastModified = Files.getLastModifiedTime(path)
                        .toInstant()
                        .toString()
                        .replace("T", " ")
                        .substring(0, 19);

                writer.write(type + " - " + name + " | " + lastModified);
                writer.newLine();
            }
        }
    }

    private static void validateDirectory(Path directory) {
        if (Files.notExists(directory) || !Files.isDirectory(directory)) {
            throw new IllegalArgumentException("Directory does not valid");
        }
    }

    public static List<String> readTxtTFile(Path path) throws IOException {
        List<String> fileRead = Files.readAllLines(path);
        fileRead.forEach(System.out::println);
        return fileRead;
    }
}
