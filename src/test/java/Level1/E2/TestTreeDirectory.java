package Level1.E2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTreeDirectory {
    File root;

    @AfterEach

    void tearDown() {
        deleteRecursively(root);
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
    void testNotExistDirectory() {
        root = new File("Not Exist");
        assertThrows(IllegalArgumentException.class, () -> TreeDirectory.listTreeDirectory(root), "Should throw IllegalArgumentException if directory is doesnt exist");
    }

    @Test
    void testEmptyDirectory() {
        root = new File("Empty");
        root.mkdir();
        assertThrows(IllegalArgumentException.class, () -> TreeDirectory.listTreeDirectory(root), "Should throw IllegalArgumentException if directory is empty");
    }

    @Test
    public void testListTreeDirectory() throws IOException {
        root = new File("TestRoot");
        root.mkdir();

        new File(root, "Zeta").mkdir();
        new File(root, "Alpha").mkdir();

        File subFolder = new File(root, "Alpha/Sub");
        subFolder.mkdirs();

        new File(root, "music.mp3").createNewFile();
        new File(subFolder, "notes.txt").createNewFile();
        new File(subFolder, "aaa.log").createNewFile();


        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));
        TreeDirectory.listTreeDirectory(root);
        System.setOut(originalOut);
        String printed = output.toString();

        assertTrue(printed.contains("D - Alpha"));
        assertTrue(printed.contains("D - Sub"));
        assertTrue(printed.contains("F - aaa.log"));
        assertTrue(printed.contains("F - notes.txt"));
        assertTrue(printed.contains("D - Zeta"));
        assertTrue(printed.contains("F - music"));
    }
}

