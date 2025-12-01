package Level1.E5;

import Level1.E4.ReadTXT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDirectorySerialized {
    File root;
    File outputFile;
    File serFile;

    @BeforeEach
    public void setUp(@TempDir File tempDir) throws IOException {
        root = new File("TestRoot");
        root.mkdir();

        File alpha = new File(root, "Alpha");
        File sub = new File(alpha, "Sub");
        File zeta = new File(root, "Zeta");

        sub.mkdirs();
        zeta.mkdir();

        new File(root, "music.mp3").createNewFile();
        new File(sub, "notes.txt").createNewFile();
        new File(sub, "aaa.log").createNewFile();

        outputFile = new File("tree_output.txt");
        serFile = new File("directory.ser");
    }

    @Test
    void testInvalidDirectoryThrowsException() {
        File invalid = new File("invalid");
        assertThrows(IllegalArgumentException.class, () ->
                DirectorySerialized.directoryToFile(invalid, outputFile)
        );
    }

    @Test
    void testSaveTreeToFile() throws IOException {

        DirectorySerialized.directoryToFile(root, outputFile);

        assertTrue(outputFile.exists(), "Output file should exist");

        List<String> lines = Files.readAllLines(outputFile.toPath());

        assertTrue(lines.stream().anyMatch(l -> l.contains("D - Alpha")));
        assertTrue(lines.stream().anyMatch(l -> l.contains("D - Sub")));
        assertTrue(lines.stream().anyMatch(l -> l.contains("F - aaa.log")));
        assertTrue(lines.stream().anyMatch(l -> l.contains("F - notes.txt")));
        assertTrue(lines.stream().anyMatch(l -> l.contains("D - Zeta")));
        assertTrue(lines.stream().anyMatch(l -> l.contains("F - music.mp3")));

    }

    @Test
    void testReadTXT(@TempDir Path tempDir) throws IOException {
        Path filePath = tempDir.resolve("Test.txt");
        Files.writeString(filePath, "First Line\nSecond Line");
        List<String> result = ReadTXT.readTxtFile(filePath);

        assertEquals(2, result.size());
        assertEquals("First Line", result.get(0));
        assertEquals("Second Line", result.get(1));
    }

    @Test
    void testDirectoryToFileAndSerialization() throws Exception {

        List<String> lines = DirectorySerialized.readTxtFile(outputFile.toPath());
        DirectorySerialized.serialize(lines, serFile);
        assertTrue(serFile.exists());

        @SuppressWarnings("unchecked")
        List<String> deserialized = (List<String>) DirectorySerialized.deserialize(serFile);
        assertEquals(lines, deserialized);
    }
}
