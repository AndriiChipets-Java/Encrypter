package ua.prom.roboticsdmc.encrypter;

import ua.prom.roboticsdmc.domain.Command;
import ua.prom.roboticsdmc.domain.EncryptingData;

public class PathCreaterImpl implements PathCreater {

    @Override
    public String createPath(EncryptingData encryptingData) {

        final String encrypted = "[ENCRYPTED]";
        final String decrypted = "[DECRYPTED]";
        final String bruteForced = "[BRUTE_FORCED]";
        String sourcePath = encryptingData.getSourceTextPath();
        String command = encryptingData.getCommand();

        String newFilePath = "";
        int indexOfDot = sourcePath.lastIndexOf(".");
        int indexOfClosedBracket = sourcePath.lastIndexOf("]");
        int indexOfOpenedBracket = sourcePath.lastIndexOf("[", indexOfClosedBracket);


        if (sourcePath.contains(decrypted)
            || sourcePath.contains(encrypted)
            || sourcePath.contains(bruteForced)) {
            if (Command.ENCRYPT.equals(command)) {
                newFilePath = sourcePath.substring(0, indexOfOpenedBracket)
                    + encrypted + sourcePath.substring(indexOfClosedBracket + 1);
            } else if (Command.DECRYPT.equals(command)) {
                newFilePath = sourcePath.substring(0, indexOfOpenedBracket)
                    + decrypted + sourcePath.substring(indexOfClosedBracket + 1);
            } else if (Command.BRUTE_FORCE.equals(command)) {
                newFilePath = sourcePath.substring(0, indexOfOpenedBracket)
                    + bruteForced + sourcePath.substring(indexOfClosedBracket + 1);
            }
        } else {
            if (Command.ENCRYPT.equals(command)) {
                newFilePath = sourcePath.substring(0, indexOfDot)
                    + encrypted + sourcePath.substring(indexOfDot);
            } else if (Command.DECRYPT.equals(command)) {
                newFilePath = sourcePath.substring(0, indexOfDot)
                    + decrypted + sourcePath.substring(indexOfDot);
            } else if (Command.BRUTE_FORCE.equals(command)) {
                newFilePath = sourcePath.substring(0, indexOfDot)
                    + bruteForced + sourcePath.substring(indexOfDot);
            }
        }
        return newFilePath;
    }
}
