package ua.prom.roboticsdmc.encrypter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PathCreaterImplTest {

    PathCreater pathCreater = new PathCreaterImpl();

    @Test
    void createPath_shouldReturnCorrectPathWithEncrypted_whenCommandIsEncryptAndSourceFilePathContainsDecryptedOrBruteForced() {
        String sourcePath = "src\\main\\resources\\SourceTextEN[BRUTE_FORCED].txt";
        String command = "ENCRYPT";

        assertEquals("src\\main\\resources\\SourceTextEN[ENCRYPTED].txt", pathCreater.createPath(sourcePath, command));
    }

    @Test
    void createPath_shouldReturnCorrectPathWithDecrypted_whenCommandIsDecryptAndSourceFilePathContainsEncrypted() {
        String sourcePath = "src\\main\\resources\\SourceTextEN[ENCRYPTED].txt";
        String command = "DECRYPT";

        assertEquals("src\\main\\resources\\SourceTextEN[DECRYPTED].txt", pathCreater.createPath(sourcePath, command));
    }

    @Test
    void createPath_shouldReturnCorrectPathWithBruteForced_whenCommandIsBruteForceAndSourceFilePathContainsDecryptedOrEncrypted() {
        String sourcePath = "src\\main\\resources\\SourceTextEN[ENCRYPTED].txt";
        String command = "BRUTE_FORCE";

        assertEquals("src\\main\\resources\\SourceTextEN[BRUTE_FORCED].txt", pathCreater.createPath(sourcePath, command));
    }

    @Test
    void createPath_shouldReturnCorrectPathWithEncrypted_whenCommandIsEncryptAndSourceFilePathDoesNotContainDecryptedOrBruteForced() {
        String sourcePath = "src\\main\\resources\\SourceTextEN.txt";
        String command = "ENCRYPT";

        assertEquals("src\\main\\resources\\SourceTextEN[ENCRYPTED].txt", pathCreater.createPath(sourcePath, command));
    }

    @Test
    void createPath_shouldReturnCorrectPathWithDecrypted_whenCommandIsDecryptAndSourceFilePathDoeNotContainEncrypted() {
        String sourcePath = "src\\main\\resources\\SourceTextEN.txt";
        String command = "DECRYPT";

        assertEquals("src\\main\\resources\\SourceTextEN[DECRYPTED].txt", pathCreater.createPath(sourcePath, command));
    }

    @Test
    void createPath_shouldReturnCorrectPathWithBruteForced_whenCommandIsBruteForceAndSourceFilePathDoesNotContainDecryptedOrEncrypted() {
        String sourcePath = "src\\main\\resources\\SourceTextEN.txt";
        String command = "BRUTE_FORCE";

        assertEquals("src\\main\\resources\\SourceTextEN[BRUTE_FORCED].txt", pathCreater.createPath(sourcePath, command));
    }

}
