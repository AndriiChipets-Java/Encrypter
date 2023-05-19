package ua.prom.roboticsdmc.encrypter;

import org.apache.commons.lang3.StringUtils;
import ua.prom.roboticsdmc.domain.AlphabetEn;
import ua.prom.roboticsdmc.domain.Command;

import java.util.*;

public class EncrypterImpl implements Encrypter {
    @Override
    public String encryptText(String sourceText, String command, int key) {
        String result = "";
        AlphabetEn alphabetEn = new AlphabetEn();
        List<Character> rotatedAlphabet = new ArrayList<>(alphabetEn.getEngAlphabet());

        if (command.equals(String.valueOf(Command.ENCRYPT))) {
            Collections.rotate(rotatedAlphabet, key % alphabetEn.getEngAlphabetSize() * -1);
            result = processText(sourceText, alphabetEn.getEngAlphabet(), rotatedAlphabet, alphabetEn.getEngAlphabetSize());
        } else if (command.equals(String.valueOf(Command.DECRYPT))) {
            Collections.rotate(rotatedAlphabet, key % alphabetEn.getEngAlphabetSize());
            result = processText(sourceText, alphabetEn.getEngAlphabet(), rotatedAlphabet, alphabetEn.getEngAlphabetSize());
        } else if (command.equals(String.valueOf(Command.BRUTE_FORCE))) {
            result = executeBruteForce(sourceText, alphabetEn.getEngAlphabet(), alphabetEn.getEngAlphabetSize(), alphabetEn.getEngLanguageIdentifiers());
        }
        return result;
    }

    private String executeBruteForce(String sourceText, List<Character> alphabet, int alphabetSize, List<String> languageIdentifiers) {
        StringBuilder resultStrBdr = new StringBuilder();
        String message = "This is expected key number -> %d < - if you find it weird you can choose some more suitable variant from provided below\n\n";
        String keyNumber = "\n\n======================== The Brute force key is:-> %d <- ========================\n\n";
        ArrayList<Integer> identifireMatches = new ArrayList<>();
        ArrayList<String> textVariants = new ArrayList<>();

        for (int i = 0; i < alphabetSize; i++) {
            int matchesNum = 0;
            List<Character> rotatedAlphabet = new ArrayList<>(alphabet);
            Collections.rotate(rotatedAlphabet, i);
            String currentVariant = processText(sourceText, alphabet, rotatedAlphabet, alphabetSize);
            for (String identifier : languageIdentifiers) {
                matchesNum += StringUtils.countMatches(currentVariant, identifier);
            }
            identifireMatches.add(i, matchesNum);
            textVariants.add(i, currentVariant);
        }
        int max = Collections.max(identifireMatches);
        resultStrBdr.append(String.format(message, identifireMatches.indexOf(max)));
        resultStrBdr.append(textVariants.get(identifireMatches.indexOf(max)));
        for (int i = 0; i < textVariants.size(); i++) {
            resultStrBdr.append(String.format(keyNumber, i));
            resultStrBdr.append(textVariants.get(i));
        }
        return resultStrBdr.toString();
    }

    private String processText(String sourceText, List<Character> alphabet, List<Character> rotatedChars, int alphabetSize) {
        StringBuilder currentStrBdr = new StringBuilder();
        boolean isSymbolExist = false;
        String encryptedSymbol = "";
        char[] textChars = sourceText.toCharArray();
        for (char currTextChar : textChars) {
            for (int i = 0; i < alphabetSize; i++) {
                char currAlphabetLetter = alphabet.get(i);
                isSymbolExist = false;
                if (String.valueOf(currTextChar).equalsIgnoreCase(String.valueOf(currAlphabetLetter))) {
                    encryptedSymbol = setCase(rotatedChars, currTextChar, i);
                    isSymbolExist = true;
                    break;
                }
            }
            buildText(currentStrBdr, isSymbolExist, encryptedSymbol, currTextChar);
        }
        return currentStrBdr.toString();
    }

    private String setCase(List<Character> rotatedChars, char currTextChar, int letterPosition) {
        if (Character.isAlphabetic(currTextChar) && Character.isUpperCase(currTextChar)) {
            return String.valueOf(rotatedChars.get(letterPosition)).toUpperCase();
        }
        return String.valueOf(rotatedChars.get(letterPosition)).toLowerCase();
    }

    private void buildText(StringBuilder currentStrBdr, boolean isSymbolExist, String encryptedSymbol, char currTextChar) {
        if (isSymbolExist) {
            currentStrBdr.append(encryptedSymbol);
        } else {
            currentStrBdr.append(currTextChar);
        }
    }
}
