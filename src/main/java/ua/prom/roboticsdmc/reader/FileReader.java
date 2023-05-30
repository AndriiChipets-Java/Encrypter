package ua.prom.roboticsdmc.reader;

import ua.prom.roboticsdmc.domain.EncryptingData;

public interface FileReader {
    String read(EncryptingData encryptingData);
}
