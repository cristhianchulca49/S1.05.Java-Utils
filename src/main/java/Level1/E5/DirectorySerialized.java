package Level1.E5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class DirectorySerialized {
    private static void validateDirectory(Path directory) throws IOException {
        if (Files.notExists(directory) || !Files.isDirectory(directory)) {
            throw new IllegalArgumentException("Directory does not valid");
        }
    }
}
