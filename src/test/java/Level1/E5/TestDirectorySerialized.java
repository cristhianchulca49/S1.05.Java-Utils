package Level1.E5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestDirectorySerialized {
    @BeforeEach
    public void setUp(@TempDir Path tempDir) throws IOException {
        Path rootDir = Path.of("TestRoot");
        Files.createDirectories(rootDir);

        Path alpha = rootDir.resolve("Alpha");
        Path sub = alpha.resolve("Sub");
        Path zeta = rootDir.resolve("Zeta");

        Files.createDirectories(sub);
        Files.createDirectories(zeta);

        Files.createFile(rootDir.resolve("music.mp3"));
        Files.createFile(sub.resolve("notes.txt"));
        Files.createFile(sub.resolve("aaa.log"));
    }

}
