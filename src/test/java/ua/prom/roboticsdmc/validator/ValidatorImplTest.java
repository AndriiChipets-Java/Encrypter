package ua.prom.roboticsdmc.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ua.prom.roboticsdmc.domain.Command;
import ua.prom.roboticsdmc.domain.EncryptingData;

class ValidatorImplTest {

    @TempDir
    static Path tempDir;

    Validator validator = new ValidatorImpl();

    @Test
    void validate_shouldReturnIllegalArgumentException_whenKeyIsNegative() throws IOException {

        Path sourceFilePath = tempDir.resolve("TempFile.txt");
        String filePath = sourceFilePath.toString();
        String command = Command.ENCRYPT.toString();
        int key = -11;
        EncryptingData encryptingData = EncryptingData.builder()
            .withSourceTextPath(filePath)
            .withCommand(command)
            .withKey(key).build();
        String fileText = "File exist";

        Files.writeString(sourceFilePath, fileText);

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> validator.validate(encryptingData));
        assertEquals("Key can't be negative", exception.getMessage());
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenFilePathIsNull() {

        String filePath = null;
        String command = Command.ENCRYPT.toString();
        int key = 1;
        EncryptingData encryptingData = EncryptingData.builder()
            .withSourceTextPath(filePath)
            .withCommand(command)
            .withKey(key).build();

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> validator.validate(encryptingData));
        assertEquals("File path is null", exception.getMessage());
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenFilePathIsEmpty() {

        String filePath = "";
        String command = Command.ENCRYPT.toString();
        int key = 1;
        EncryptingData encryptingData = EncryptingData.builder()
            .withSourceTextPath(filePath)
            .withCommand(command)
            .withKey(key).build();

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> validator.validate(encryptingData));
        assertEquals("File path is empty", exception.getMessage());
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenFilePathContainsOnlySpacesOrTabs() {

        String filePath = "   ";
        String command = Command.ENCRYPT.toString();
        int key = 1;
        EncryptingData encryptingData = EncryptingData.builder()
            .withSourceTextPath(filePath)
            .withCommand(command)
            .withKey(key).build();

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> validator.validate(encryptingData));
        assertEquals("File path contains only spaces or (and) tabs", exception.getMessage());
    }

    @Test
    void validate_shouldReturnInvalidPathException_whenFilePathIsDirectory() throws IOException {

        String directoryPath = tempDir.toString();
        String command = Command.ENCRYPT.toString();
        int key = 1;
        EncryptingData encryptingData = EncryptingData.builder()
            .withSourceTextPath(directoryPath)
            .withCommand(command)
            .withKey(key).build();

        Exception exception = assertThrows(InvalidPathException.class,
            () -> validator.validate(encryptingData));
        assertEquals("There is some problem : File is directory", exception.getMessage());
    }

    @Test
    void validate_shouldReturnInvalidPathException_whenFileNotExist() throws IOException {

        Path sourceFilePath = tempDir.resolve("TempFile.txt");
        String filePath = sourceFilePath.toString();
        String wrongFilePath = "wrong" + filePath;
        String command = Command.ENCRYPT.toString();
        int key = 1;
        EncryptingData encryptingData = EncryptingData.builder()
            .withSourceTextPath(wrongFilePath)
            .withCommand(command)
            .withKey(key).build();
        String fileText = "File exist";

        Files.writeString(sourceFilePath, fileText);


        Exception exception = assertThrows(InvalidPathException.class,
            () -> validator.validate(encryptingData));
        assertEquals("There is some problem : File doesn't exist", exception.getMessage());
    }

    @Test
    void validate_shouldReturnInvalidPathException_whenFileIsEmpty() throws IOException {

        Path sourceFilePath = tempDir.resolve("TempFile.txt");
        String filePath = sourceFilePath.toString();
        String command = Command.ENCRYPT.toString();
        int key = 1;
        EncryptingData encryptingData = EncryptingData.builder()
            .withSourceTextPath(filePath)
            .withCommand(command)
            .withKey(key).build();
        String fileText = "";

        Files.writeString(sourceFilePath, fileText);

        Exception exception = assertThrows(InvalidPathException.class,
            () -> validator.validate(encryptingData));
        assertEquals("There is some problem : File is empty", exception.getMessage());
    }

    @Test
    void validate_shouldReturnNothing_whenPathAndFileAreCorrect() throws IOException {

        Path sourceFilePath = tempDir.resolve("TempFile.txt");
        String filePath = sourceFilePath.toString();
        String command = Command.ENCRYPT.toString();
        int key = 1;
        EncryptingData encryptingData = EncryptingData.builder()
            .withSourceTextPath(filePath)
            .withCommand(command)
            .withKey(key).build();
        String fileText = "Path and file are correct";

        Files.writeString(sourceFilePath, fileText);

        assertDoesNotThrow(() -> validator.validate(encryptingData));
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenCommandIsNull() throws IOException {
        String command = null;
        Path sourceFilePath = tempDir.resolve("TempFile.txt");
        String filePath = sourceFilePath.toString();
        int key = 1;
        EncryptingData encryptingData = EncryptingData.builder()
            .withSourceTextPath(filePath)
            .withCommand(command)
            .withKey(key).build();
        String fileText = "JavaRush";
        Files.writeString(sourceFilePath, fileText);

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> validator.validate(encryptingData));
        assertEquals("Command is null", exception.getMessage());
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenCommandIsEmpty() throws IOException {
        String command = "";
        Path sourceFilePath = tempDir.resolve("TempFile.txt");
        String filePath = sourceFilePath.toString();
        int key = 1;
        EncryptingData encryptingData = EncryptingData.builder()
            .withSourceTextPath(filePath)
            .withCommand(command)
            .withKey(key).build();
        String fileText = "JavaRush";
        Files.writeString(sourceFilePath, fileText);

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> validator.validate(encryptingData));
        assertEquals("Command is empty", exception.getMessage());
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenCommandContainsOnlySpacesOrTabs() throws IOException {
        String command = "   ";
        Path sourceFilePath = tempDir.resolve("TempFile.txt");
        String filePath = sourceFilePath.toString();
        int key = 1;
        EncryptingData encryptingData = EncryptingData.builder()
            .withSourceTextPath(filePath)
            .withCommand(command)
            .withKey(key).build();
        String fileText = "JavaRush";
        Files.writeString(sourceFilePath, fileText);

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> validator.validate(encryptingData));
        assertEquals("Command contains only spaces or tabs", exception.getMessage());
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenCommandIsNotENCRYPTorDECRYPTorBRUTE_FORCE() throws IOException {
        String command = "TEST";
        Path sourceFilePath = tempDir.resolve("TempFile.txt");
        String filePath = sourceFilePath.toString();
        int key = 1;
        EncryptingData encryptingData = EncryptingData.builder()
            .withSourceTextPath(filePath)
            .withCommand(command)
            .withKey(key).build();
        String fileText = "JavaRush";
        Files.writeString(sourceFilePath, fileText);

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> validator.validate(encryptingData));
        assertEquals("You enter wrong command, please chose between: "
            + Command.ENCRYPT +", "
            + Command.DECRYPT + ", "
            + Command.BRUTE_FORCE, exception.getMessage());
    }
}
