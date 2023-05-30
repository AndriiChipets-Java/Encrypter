package ua.prom.roboticsdmc;

import ua.prom.roboticsdmc.domain.Command;
import ua.prom.roboticsdmc.domain.EncryptingData;
import ua.prom.roboticsdmc.encrypter.Encrypter;
import ua.prom.roboticsdmc.encrypter.EncrypterImpl;
import ua.prom.roboticsdmc.encrypter.PathCreater;
import ua.prom.roboticsdmc.encrypter.PathCreaterImpl;
import ua.prom.roboticsdmc.provider.EncryptingProvider;
import ua.prom.roboticsdmc.reader.FileReader;
import ua.prom.roboticsdmc.reader.FileReaderImpl;
import ua.prom.roboticsdmc.validator.Validator;
import ua.prom.roboticsdmc.validator.ValidatorImpl;
import ua.prom.roboticsdmc.creater.FileCreater;
import ua.prom.roboticsdmc.creater.FileCreateImpl;

import java.util.Scanner;

public class EncryptingApplication {
    public static void main(String[] args) {

        String sourceFilePath;
        String command;
        int key = 0;
        EncryptingData encryptingData;
        Validator fileValidator = new ValidatorImpl();
        FileReader fileReader = new FileReaderImpl();
        Encrypter encrypter = new EncrypterImpl();
        PathCreater pathCreater = new PathCreaterImpl();
        FileCreater fileWriter = new FileCreateImpl();
        EncryptingProvider encryptingProvider = new EncryptingProvider(fileValidator,
            fileReader, encrypter, pathCreater, fileWriter);

        if (args.length == 0) {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Input File Path: ");
                System.out.println("For example: C:\\workspaceIntellij\\Encrypter\\src\\main\\resources\\SourceTextEN.txt");
                sourceFilePath = scanner.nextLine();
                System.out.println("Input command: ");
                System.out.println("For example: "
                    + Command.ENCRYPT + ", "
                    + Command.DECRYPT + ", "
                    + Command.BRUTE_FORCE);
                command = scanner.nextLine();
                if (!Command.BRUTE_FORCE.equals(command)) {
                    System.out.println("Input key (it must be a number, if you chose null, the text doesn't change): ");
                    key = scanner.nextInt();
                }
            }
        } else {
            sourceFilePath = args[1];
            command = args[0];
            if (args.length > 2) {
                key = Integer.parseInt(args[2]);
            }
        }
        encryptingData = EncryptingData.builder()
            .withSourceTextPath(sourceFilePath)
            .withCommand(command)
            .withKey(key).build();
        encryptingProvider.provideEncrypting(encryptingData);
        System.out.println("File was successfully created");
    }
}
