package ua.prom.roboticsdmc.encrypter;

import org.apache.commons.lang3.StringUtils;
import ua.prom.roboticsdmc.domain.AlphabetEn;
import ua.prom.roboticsdmc.domain.Command;
import ua.prom.roboticsdmc.domain.EncryptingData;

import java.util.*;

public class EncrypterImpl implements Encrypter {
    @Override
    public String encryptText(String sourceText, EncryptingData encryptingData) {
        String result = "";
        AlphabetEn alphabetEn = new AlphabetEn();
        List<Character> rotatedAlphabet = new ArrayList<>(alphabetEn.getEngAlphabet());

        if (Command.ENCRYPT.equals(encryptingData.getCommand())) {
            Collections.rotate(rotatedAlphabet, encryptingData.getKey() % alphabetEn.getEngAlphabetSize() * -1);
            result = processText(sourceText,
                alphabetEn.getEngAlphabet(),
                rotatedAlphabet,
                alphabetEn.getEngAlphabetSize());
        } else if (Command.DECRYPT.equals(encryptingData.getCommand())) {
            Collections.rotate(rotatedAlphabet, encryptingData.getKey() % alphabetEn.getEngAlphabetSize());
            result = processText(sourceText,
                alphabetEn.getEngAlphabet(),
                rotatedAlphabet,
                alphabetEn.getEngAlphabetSize());
        } else if (Command.BRUTE_FORCE.equals(encryptingData.getCommand())) {
            result = executeBruteForce(sourceText,
                alphabetEn.getEngAlphabet(),
                alphabetEn.getEngAlphabetSize(),
                alphabetEn.getEngLanguageIdentifiers());
        }
        return result;
    }

    private String executeBruteForce(String sourceText,
                                     List<Character> alphabet,
                                     int alphabetSize,
                                     List<String> languageIdentifiers) {
        ArrayList<Integer> matchIdentifiers = new ArrayList<>();
        ArrayList<String> textVariants = new ArrayList<>();

        for (int i = 0; i < alphabetSize; i++) {
            int matchesNum = 0;
            List<Character> rotatedAlphabet = new ArrayList<>(alphabet);
            Collections.rotate(rotatedAlphabet, i);
            String currentVariant = processText(sourceText, alphabet, rotatedAlphabet, alphabetSize);
            for (String identifier : languageIdentifiers) {
                matchesNum += StringUtils.countMatches(currentVariant, identifier);
            }
            matchIdentifiers.add(i, matchesNum);
            textVariants.add(i, currentVariant);
        }
        int max = Collections.max(matchIdentifiers);
        return new StringBuilder().append(textVariants.get(matchIdentifiers.indexOf(max))).toString();
    }

    private String processText(String sourceText,
                               List<Character> alphabet,
                               List<Character> rotatedChars,
                               int alphabetSize) {
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

    private void buildText(StringBuilder currentStrBdr,
                           boolean isSymbolExist,
                           String encryptedSymbol,
                           char currTextChar) {
        if (isSymbolExist) {
            currentStrBdr.append(encryptedSymbol);
        } else {
            currentStrBdr.append(currTextChar);
        }
    }
}
