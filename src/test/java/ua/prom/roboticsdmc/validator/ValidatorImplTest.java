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

class ValidatorImplTest {

    @TempDir
    static Path tempDir;

    Validator validator = new ValidatorImpl();

    @Test
    void validate_shouldReturnIllegalArgumentException_whenKeyIsNegative() throws IOException {

        Path sourceFilePath = tempDir.resolve("TempFile.txt");
        String filePath = sourceFilePath.toString();
        String command = "ENCRYPT";
        int key = -11;
        String fileText = "File exist";

        Files.writeString(sourceFilePath, fileText);

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> validator.validate(filePath, command, key));
        assertEquals("Key can't be negative", exception.getMessage());
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenFilePathIsNull() {

        String sourceFilePath = null;
        String command = "ENCRYPT";
        int key = 1;

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> validator.validate(sourceFilePath, command, key));
        assertEquals("File path is null", exception.getMessage());
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenFilePathIsEmpty() {

        String sourceFilePath = "";
        String command = "ENCRYPT";
        int key = 1;

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> validator.validate(sourceFilePath, command, key));
        assertEquals("File path is empty", exception.getMessage());
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenFilePathContainsOnlySpacesOrTabs() {

        String sourceFilePath = "   ";
        String command = "ENCRYPT";
        int key = 1;

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> validator.validate(sourceFilePath, command, key));
        assertEquals("File path contains only spaces or (and) tabs", exception.getMessage());
    }

    @Test
    void validate_shouldReturnInvalidPathException_whenFilePathIsDirectory() throws IOException {

        Path sourceFilePath = tempDir.resolve("TempFile.txt");
        String directoryPath = tempDir.toString();
        String command = "ENCRYPT";
        int key = 1;
        String fileText = "File exist";

        Files.writeString(sourceFilePath, fileText);

        Exception exception = assertThrows(InvalidPathException.class,
            () -> validator.validate(directoryPath, command, key));
        assertEquals("There is some problem : File is directory", exception.getMessage());
    }

    @Test
    void validate_shouldReturnInvalidPathException_whenFileNotExist() throws IOException {

        Path sourceFilePath = tempDir.resolve("TempFile.txt");
        String filePath = sourceFilePath.toString();
        String command = "ENCRYPT";
        int key = 1;
        String fileText = "File exist";

        Files.writeString(sourceFilePath, fileText);

        String wrongFilePath = "wrong" + filePath;

        Exception exception = assertThrows(InvalidPathException.class,
            () -> validator.validate(wrongFilePath, command, key));
        assertEquals("There is some problem : File doesn't exist", exception.getMessage());
    }

    @Test
    void validate_shouldReturnInvalidPathException_whenFileIsEmpty() throws IOException {

        Path sourceFilePath = tempDir.resolve("TempFile.txt");
        String filePath = sourceFilePath.toString();
        String command = "ENCRYPT";
        int key = 1;
        String fileText = "";

        Files.writeString(sourceFilePath, fileText);

        Exception exception = assertThrows(InvalidPathException.class,
            () -> validator.validate(filePath, command, key));
        assertEquals("There is some problem : File is empty", exception.getMessage());
    }

    @Test
    void validate_shouldReturnNothing_whenPathAndFileAreCorrect() throws IOException {

        Path sourceFilePath = tempDir.resolve("TempFile.txt");
        String filePath = sourceFilePath.toString();
        String command = "ENCRYPT";
        int key = 1;
        String fileText = "Path and file are correct";

        Files.writeString(sourceFilePath, fileText);

        assertDoesNotThrow(() -> validator.validate(filePath, command, key));
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenCommandIsNull() throws IOException {
        String command = null;
        Path sourceFilePath = tempDir.resolve("TempFile.txt");
        String filePath = sourceFilePath.toString();
        int key = 1;
        String fileText = "JavaRush";
        Files.writeString(sourceFilePath, fileText);

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> validator.validate(filePath, command, key));
        assertEquals("Command is null", exception.getMessage());
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenCommandIsEmpty() throws IOException {
        String command = "";
        Path sourceFilePath = tempDir.resolve("TempFile.txt");
        String filePath = sourceFilePath.toString();
        int key = 1;
        String fileText = "JavaRush";
        Files.writeString(sourceFilePath, fileText);

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> validator.validate(filePath, command, key));
        assertEquals("Command is empty", exception.getMessage());
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenCommandContainsOnlySpacesOrTabs() throws IOException {
        String command = "   ";
        Path sourceFilePath = tempDir.resolve("TempFile.txt");
        String filePath = sourceFilePath.toString();
        int key = 1;
        String fileText = "JavaRush";
        Files.writeString(sourceFilePath, fileText);

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> validator.validate(filePath, command, key));
        assertEquals("Command contains only spaces or tabs", exception.getMessage());
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenCommandIsNotENCRYPTorDECRYPTorBRUTE_FORCE() throws IOException {
        String command = "TEST";
        Path sourceFilePath = tempDir.resolve("TempFile.txt");
        String filePath = sourceFilePath.toString();
        int key = 1;
        String fileText = "JavaRush";
        Files.writeString(sourceFilePath, fileText);

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> validator.validate(filePath, command, key));
        assertEquals("You enter wrong command, please chose between: ENCRYPT, DECRYPT or BRUTE_FORCE", exception.getMessage());
    }
}
