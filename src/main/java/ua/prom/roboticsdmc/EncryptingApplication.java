package ua.prom.roboticsdmc;

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

        Validator fileValidator = new ValidatorImpl();
        FileReader fileReader = new FileReaderImpl();
        Encrypter encrypter = new EncrypterImpl();
        PathCreater pathCreater = new PathCreaterImpl();
        FileCreater fileWriter = new FileCreateImpl();
        EncryptingProvider encryptingProvider = new EncryptingProvider(fileValidator, fileReader, encrypter, pathCreater, fileWriter);

        if (args.length == 0) {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Input File Path: ");
                System.out.println("For example: C:\\workspaceIntellij\\Encrypter\\src\\main\\resources\\SourceTextEN.txt");
                String sourceFilePath = scanner.nextLine();
                System.out.println("Input command: ");
                System.out.println("For example: ENCRYPT, DECRYPT, BRUTE_FORCE");
                String command = scanner.nextLine();
                if (!command.equals("BRUTE_FORCE")) {
                    System.out.println("Input key: ");
                    System.out.println("It must be number, if you chose null, the text doesn't change");
                    int key = scanner.nextInt();
                    encryptingProvider.provideEncrypting(sourceFilePath, command, key);
                } else {
                    int key = 1; // it doesn't matter what number provided here, it doesn't take part in Brute Force process, but method needs this parameter.
                    encryptingProvider.provideEncrypting(sourceFilePath, command, key);
                }
                System.out.println("File was successfully created");
            }
        } else {
            String sourceFilePath = args[1];
            String command = args[0];
            int key = Integer.parseInt(args[2]);
            encryptingProvider.provideEncrypting(sourceFilePath, command, key);
            System.out.println("File was successfully created");
        }
    }
}
