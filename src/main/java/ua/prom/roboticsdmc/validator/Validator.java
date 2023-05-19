package ua.prom.roboticsdmc.validator;

public interface Validator {
    void validate(String sourceFilePath, String command, int key);
}
