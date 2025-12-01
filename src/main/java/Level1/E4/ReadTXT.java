package Level1.E4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadTXT {
    public static List<String> readTxtFile(Path path) throws IOException {
        List<String> fileRead= Files.readAllLines(path);
        fileRead.forEach(System.out::println);
        return fileRead;
    }
}
