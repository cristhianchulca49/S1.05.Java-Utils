package Level1.E2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.File;

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
}

