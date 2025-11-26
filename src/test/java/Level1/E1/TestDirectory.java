package Level1.E1;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestDirectory {
    File directory;

    @AfterEach
    void tearDown() {
        directory.delete();
    }

    @Test
    void TestNotExistDirectory() {
        directory = new File("Not Exist");

        assertThrows(IllegalArgumentException.class, () -> Directory.sortAlphabetically(directory), "Should throw NullPointerException if directory is doesnt exist");
    }
}
