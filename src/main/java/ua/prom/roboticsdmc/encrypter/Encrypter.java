package ua.prom.roboticsdmc.encrypter;

import ua.prom.roboticsdmc.domain.EncryptingData;

public interface Encrypter {
    String encryptText(String sourceText, EncryptingData encryptingData);
}
