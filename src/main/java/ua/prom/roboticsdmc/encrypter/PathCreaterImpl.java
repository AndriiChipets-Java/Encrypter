package ua.prom.roboticsdmc.encrypter;

public class PathCreaterImpl implements PathCreater {

    @Override
    public String createPath(String sourcePath, String command) {

        String encrypted = "[ENCRYPTED]";
        String decrypted = "[DECRYPTED]";
        String bruteForced = "[BRUTE_FORCED]";

        String newFilePath = "";
        int indexOfDot = sourcePath.lastIndexOf(".");
        int indexOfClosedBracket = sourcePath.lastIndexOf("]");
        int indexOfOpenedBracket = sourcePath.lastIndexOf("[", indexOfClosedBracket);


        if (sourcePath.contains(decrypted) || sourcePath.contains(encrypted) || sourcePath.contains(bruteForced)) {
            if (command.equals("ENCRYPT")) {
                newFilePath = sourcePath.substring(0, indexOfOpenedBracket) + encrypted + sourcePath.substring(indexOfClosedBracket + 1);
            } else if (command.equals("DECRYPT")) {
                newFilePath = sourcePath.substring(0, indexOfOpenedBracket) + decrypted + sourcePath.substring(indexOfClosedBracket + 1);
            } else if (command.equals("BRUTE_FORCE")) {
                newFilePath = sourcePath.substring(0, indexOfOpenedBracket) + bruteForced + sourcePath.substring(indexOfClosedBracket + 1);
            }
        } else {
            if (command.equals("ENCRYPT")) {
                newFilePath = sourcePath.substring(0, indexOfDot) + encrypted + sourcePath.substring(indexOfDot);
            } else if (command.equals("DECRYPT")) {
                newFilePath = sourcePath.substring(0, indexOfDot) + decrypted + sourcePath.substring(indexOfDot);
            } else if (command.equals("BRUTE_FORCE")) {
                newFilePath = sourcePath.substring(0, indexOfDot) + bruteForced + sourcePath.substring(indexOfDot);
            }
        }
        return newFilePath;
    }
}
