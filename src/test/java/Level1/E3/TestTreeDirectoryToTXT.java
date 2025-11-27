package Level1.E3;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.Files;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TestTreeDirectoryToTXT {

    private File rootDir;
    private File outputFile;

    @BeforeEach
    void setUp() throws IOException {

        rootDir = new File("TestRoot");
        rootDir.mkdir();

        File alpha = new File(rootDir, "Alpha");
        File sub = new File(alpha, "Sub");
        File zeta = new File(rootDir, "Zeta");

        sub.mkdirs();
        zeta.mkdir();

        new File(rootDir, "music.mp3").createNewFile();
        new File(sub, "notes.txt").createNewFile();
        new File(sub, "aaa.log").createNewFile();


        outputFile = new File("tree_output.txt");
    }

    @AfterEach
    void tearDown() {
        deleteRecursively(rootDir);
        if (outputFile.exists()) {
            outputFile.delete();
        }
    }

    private void deleteRecursively(File file) {
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                deleteRecursively(child);
            }
        }
        file.delete();
    }

    @Test
    void testInvalidDirectoryThrowsException() {
        File invalid = new File("NoSuchDir");

        assertThrows(IllegalArgumentException.class, () ->
                TreeDirectoryToTXT.listTreeDirectory(invalid, outputFile)
        );
    }

    @Test
    void testEmptyDirectoryThrowsException() {
        File emptyDir = new File("EmptyDir");
        emptyDir.mkdir();

        assertThrows(IllegalArgumentException.class, () ->
                TreeDirectoryToTXT.listTreeDirectory(emptyDir, outputFile)
        );
        emptyDir.delete();
    }

    @Test
    void testSaveTreeToFile() throws IOException {

        TreeDirectoryToTXT.listTreeDirectory(rootDir, outputFile);

        assertTrue(outputFile.exists(), "Output file should exist");

        List<String> lines = Files.readAllLines(outputFile.toPath());

        assertTrue(lines.stream().anyMatch(l -> l.contains("D - Alpha")));
        assertTrue(lines.stream().anyMatch(l -> l.contains("D - Sub")));
        assertTrue(lines.stream().anyMatch(l -> l.contains("F - aaa.log")));
        assertTrue(lines.stream().anyMatch(l -> l.contains("F - notes.txt")));
        assertTrue(lines.stream().anyMatch(l -> l.contains("D - Zeta")));
        assertTrue(lines.stream().anyMatch(l -> l.contains("F - music.mp3")));

    }
}
