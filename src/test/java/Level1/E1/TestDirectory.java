package Level1.E1;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestDirectory {
    File directory;

    @AfterEach
    void tearDown() {
        directory.delete();
    }

    @Test
    void testNotExistDirectory() {
        directory = new File("Not Exist");

        assertThrows(IllegalArgumentException.class, () -> Directory.sortAlphabetically(directory), "Should throw NullPointerException if directory is doesnt exist");
    }

    @Test
    void testEmptyDirectory() {
        directory = new File("Empty");
        directory.mkdir();

        assertThrows(IllegalArgumentException.class, () -> Directory.sortAlphabetically(directory), "Should throw exception if directory is empty");
    }

    @Test
    void testSortAlphabetically() {
        directory = new File("User");
        directory.mkdir();
        new File(directory, "Music").mkdir();
        new File(directory, "Photos").mkdir();
        try {
            new File(directory, "Audio2.mp3").createNewFile();
            new File(directory, "Password.txt").createNewFile();
        } catch (Exception e) {
            System.out.println("Error: test files no created");
        }

        String[] expected = {"Audio2.mp3", "Music", "Password.txt", "Photos"};
        String[] result = Directory.sortAlphabetically(directory);

        assertArrayEquals(expected, result);
    }
}
