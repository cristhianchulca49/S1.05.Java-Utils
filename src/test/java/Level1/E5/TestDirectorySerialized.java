package Level1.E5;

import Level1.E3.TreeDirectoryToTXT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestDirectorySerialized {
    Path root;
    Path outputFile;

    @BeforeEach
    public void setUp(@TempDir Path tempDir) throws IOException {
        root = tempDir.resolve("TestRoot");
        Files.createDirectories(root);

        Path alpha = root.resolve("Alpha");
        Path sub = alpha.resolve("Sub");
        Path zeta = tempDir.resolve("Zeta");

        Files.createDirectories(sub);
        Files.createDirectories(zeta);

        Files.createFile(tempDir.resolve("music.mp3"));
        Files.createFile(sub.resolve("notes.txt"));
        Files.createFile(sub.resolve("aaa.log"));

        outputFile = tempDir.resolve("output.txt");
        Files.createFile(outputFile);
    }

    @Test
    void testInvalidDirectoryThrowsException() {
        Path invalid = root.resolve("invalid");
        assertThrows(IllegalArgumentException.class, () ->
                DirectorySerialized.directoryToFile(invalid, outputFile)
        );
    }
}
