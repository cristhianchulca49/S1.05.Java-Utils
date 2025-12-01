package Level1.E5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;


public class DirectorySerialized {
    private static void sortAlphabetically(Path directory) throws IOException {
        try (Stream<Path> filesDirectory = Files.walk(directory)) {
            filesDirectory
                    .sorted(Comparator.comparing(file -> file.getFileName().toString(), String.CASE_INSENSITIVE_ORDER))
                    .toList();
        }
    }

    private static void validateDirectory(Path directory) {
        if (Files.notExists(directory) || !Files.isDirectory(directory)) {
            throw new IllegalArgumentException("Directory does not valid");
        }
    }
}
