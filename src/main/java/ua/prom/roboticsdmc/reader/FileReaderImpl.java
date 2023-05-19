package ua.prom.roboticsdmc.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class FileReaderImpl implements FileReader {
    @Override
    public String read(String sourceFilePath) {
        String sourceText;
        try {
            sourceText = Files.readString(Paths.get(sourceFilePath));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return sourceText;
    }
}
