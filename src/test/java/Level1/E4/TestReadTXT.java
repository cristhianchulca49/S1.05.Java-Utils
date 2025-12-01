package Level1.E4;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;


public class TestReadTXT {

    @Test
    void testReadTXT(@TempDir Path tempDir) throws IOException {
        Path filePath = tempDir.resolve("Test.txt");
        Files.writeString(filePath, "First Line\nSecond Line");

    }
}