package Level1.E3;

import org.junit.jupiter.api.*;
import java.io.*;
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
}
