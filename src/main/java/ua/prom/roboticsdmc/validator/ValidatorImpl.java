package ua.prom.roboticsdmc.validator;

import java.io.File;
import java.nio.file.InvalidPathException;

public class ValidatorImpl implements Validator {
    @Override
    public void validate(String sourceFilePath, String command, int key) {
        validateCommand(command);
        validateFilePath(sourceFilePath);
    }

    private static void validateFilePath(String sourceFilePath) {
        if (sourceFilePath == null) {
            throw new IllegalArgumentException("File path is null");
        } else if (sourceFilePath.isEmpty()) {
            throw new IllegalArgumentException("File path is empty");
        } else if (sourceFilePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path contains only spaces or (and) tabs");
        }

        File sourceFile = new File(sourceFilePath);
        if (sourceFile.isDirectory()) {
            throw new InvalidPathException("File is directory", "There is some problem ");
        } else if (!sourceFile.exists()) {
            throw new InvalidPathException("File doesn't exist", "There is some problem ");
        }else if (sourceFile.length()<=0) {
            throw new InvalidPathException("File is empty", "There is some problem ");
        }
    }

    private static void validateCommand(String command) {
        if (command == null) {
            throw new IllegalArgumentException("Command is null");
        } else if (command.isEmpty()) {
            throw new IllegalArgumentException("Command is empty");
        } else if (command.trim().isEmpty()) {
            throw new IllegalArgumentException("Command contains only spaces or tabs");
        } else if (!command.equals("ENCRYPT") && !command.equals("DECRYPT") && !command.equals("BRUTE_FORCE")) {
            throw new IllegalArgumentException("You enter wrong command, please chose between: ENCRYPT, DECRYPT or BRUTE_FORCE");
        }
    }
}
