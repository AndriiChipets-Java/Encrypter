package ua.prom.roboticsdmc.domain;

import java.util.ArrayList;
import java.util.List;

public class AlphabetEn {

    private final List<Character> engAlphabet;
    private final List<String> engLanguageIdentifiers;
    private final int engAlphabetSize;

    public AlphabetEn() {
        this.engAlphabet = new ArrayList<>(List.of(
            'A', 'B', 'C',
            'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z'
        ));
        this.engAlphabetSize = engAlphabet.size();
        this.engLanguageIdentifiers = new ArrayList<>(List.of(
            "th", "with", "at", "by", "to", "in", "for", "from", "of", "on"
        ));
    }

    public List<Character> getEngAlphabet() {
        return engAlphabet;
    }

    public List<String> getEngLanguageIdentifiers() {
        return engLanguageIdentifiers;
    }

    public int getEngAlphabetSize() {
        return engAlphabetSize;
    }
}
