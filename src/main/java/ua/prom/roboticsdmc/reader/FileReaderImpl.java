package ua.prom.roboticsdmc.reader;

import ua.prom.roboticsdmc.domain.EncryptingData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class FileReaderImpl implements FileReader {
    @Override
    public String read(EncryptingData encryptingData) {
        String sourceText;
        try {
            sourceText = Files.readString(Paths.get(encryptingData.getSourceTextPath()));
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't read file", e);
        }
        return sourceText;
    }
}
