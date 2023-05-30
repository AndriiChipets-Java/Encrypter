package ua.prom.roboticsdmc.provider;

import ua.prom.roboticsdmc.domain.EncryptingData;
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

    public EncryptingProvider(Validator fileValidator,
                              FileReader fileReader,
                              Encrypter encrypter,
                              PathCreater pathCreater,
                              FileCreater fileCreater) {
        this.fileValidator = fileValidator;
        this.fileReader = fileReader;
        this.encrypter = encrypter;
        this.pathCreater = pathCreater;
        this.fileCreater = fileCreater;
    }

    public void provideEncrypting(EncryptingData encryptingData) {
        fileValidator.validate(encryptingData);
        String sourceText = fileReader.read(encryptingData);
        String resultText = encrypter.encryptText(sourceText, encryptingData);
        String resultFilePath = pathCreater.createPath(encryptingData);
        fileCreater.createFile(resultText, resultFilePath);
    }
}
