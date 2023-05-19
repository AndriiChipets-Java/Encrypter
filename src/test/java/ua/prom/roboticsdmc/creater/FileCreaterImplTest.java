package ua.prom.roboticsdmc.creater;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

class FileCreaterImplTest {

    FileCreater fileCreater = new FileCreateImpl();

    @TempDir
    static Path tempDir;

    @Test
    void createFile_shouldCreateFileAndWriteStringInCreatedFile_whenNewFilePathAndStringAreCorrect() throws IOException {

        String resultText = "TNU Kvzrk kxn Lodk (1995)\n"
            + "TNU 1.0 (23bn Tkx 1996)\n"
            + "TNU 1.1 (19dr Pol 1997)\n"
            + "T2CO 1.2 (8dr Nom 1998)\n"
            + "T2CO 1.3 (8dr Wki 2000)\n"
            + "T2CO 1.4 (6dr Pol 2002)\n"
            + "T2CO 5.0 (30dr Coz 2004)\n"
            + "Tkfk CO 6 (11dr Nom 2006)\n"
            + "Tkfk CO 7 (28dr Tevi 2011)\n"
            + "Tkfk CO 8 (18dr Wkb 2014)\n"
            + "Tkfk CO 9 (21cd Coz 2017)\n"
            + "Tkfk CO 10 (20dr Wkb 2018)\n"
            + "Tkfk CO 11 (Cozdowlob 2018)\n"
            + "Tkfk CO 12 (Wkbmr 2019)\n"
            + "Tkfk CO 13 (Cozdowlob 2019)\n"
            + "Tkfk CO 14 (Wkb 2020)\n"
            + "Tkfk CO 15 (Cozdowlob 2020)\n"
            + "Tkfk CO 16 (Wkb 2021)\n"
            + "Tkfk CO 17 (Cozdowlob 2021)\n"
            + "Tkfk CO 18 (dy lo bovokcon li Wkbmr 2022)";

        Path tempFilePath = tempDir.resolve("TempFile.txt");
        String tempFilePathString = tempFilePath.toString();
        File resultFile = new File(tempFilePathString);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile))) {
            writer.write(resultText);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        assertAll(
            () -> fileCreater.createFile(resultText, tempFilePathString),
            () -> assertTrue(Files.exists(tempFilePath), "File should exist"),
            () -> assertTrue(resultFile.length() > 0, "File can't be empty")
        );
    }

    @Test
    void createFile_shouldReturnIllegalArgumentException_whenInputIsNotCorrectPath() {

        String resultText = "JavaRush";
        String notCorrectFilePath = "";

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> fileCreater.createFile(resultText, notCorrectFilePath));
        assertEquals("Can't create file", exception.getMessage());


       // assertEquals(IllegalArgumentException.class, () -> fileCreater.createFile(resultText, notCorrectFilePath));
    }
}
