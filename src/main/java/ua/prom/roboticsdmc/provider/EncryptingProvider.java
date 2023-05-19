package ua.prom.roboticsdmc.provider;

import ua.prom.roboticsdmc.encrypter.Encrypter;
import ua.prom.roboticsdmc.encrypter.PathCreater;
import ua.prom.roboticsdmc.reader.FileReader;
import ua.prom.roboticsdmc.validator.Validator;
import ua.prom.roboticsdmc.creater.FileCreater;

public class EncryptingProvider {
    private final Validator fileValidator;
    private final FileReader fileReader;
    private final Encrypter encrypter;
    private final PathCreater pathCreater;
    private final FileCreater fileCreater;

    public EncryptingProvider(Validator fileValidator, FileReader fileReader, Encrypter encrypter, PathCreater pathCreater, FileCreater fileCreater) {
        this.fileValidator = fileValidator;
        this.fileReader = fileReader;
        this.encrypter = encrypter;
        this.pathCreater = pathCreater;
        this.fileCreater = fileCreater;
    }

    public void provideEncrypting(String sourceFilePath, String command, int key) {
        fileValidator.validate(sourceFilePath, command, key);
        String sourceText = fileReader.read(sourceFilePath);
        String resultText = encrypter.encryptText(sourceText, command, key);
        String resultFilePath = pathCreater.createPath(sourceFilePath, command);
        fileCreater.createFile(resultText, resultFilePath);
    }
}
