package ua.prom.roboticsdmc.encrypter;

public interface Encrypter {
    String encryptText(String sourceText, String command, int key);
}
