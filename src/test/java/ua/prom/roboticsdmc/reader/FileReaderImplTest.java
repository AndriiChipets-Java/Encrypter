package ua.prom.roboticsdmc.reader;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ua.prom.roboticsdmc.domain.EncryptingData;

class FileReaderImplTest {
    FileReader fileReader = new FileReaderImpl();

    @TempDir
    static Path tempDir;

    @Test
    void read_shouldReturnCorrectStringFromFile_whenInputIsCorrectFilePath() throws IOException {

        Path tempFilePath = tempDir.resolve("TempFile.txt");
        String tempFilePathString = tempFilePath.toString();

        String fileText =
            "JDK Alpha and Beta (1995)\n"
                + "JDK 1.0 (23rd Jan 1996)\n"
                + "JDK 1.1 (19th Feb 1997)\n"
                + "J2SE 1.2 (8th Dec 1998)\n"
                + "J2SE 1.3 (8th May 2000)\n"
                + "J2SE 1.4 (6th Feb 2002)\n"
                + "J2SE 5.0 (30th Sep 2004)\n"
                + "Java SE 6 (11th Dec 2006)\n"
                + "Java SE 7 (28th July 2011)\n"
                + "Java SE 8 (18th Mar 2014)\n"
                + "Java SE 9 (21st Sep 2017)\n"
                + "Java SE 10 (20th Mar 2018)\n"
                + "Java SE 11 (September 2018)\n"
                + "Java SE 12 (March 2019)\n"
                + "Java SE 13 (September 2019)\n"
                + "Java SE 14 (Mar 2020)\n"
                + "Java SE 15 (September 2020)\n"
                + "Java SE 16 (Mar 2021)\n"
                + "Java SE 17 (September 2021)\n"
                + "Java SE 18 (to be released by March 2022)";

        Files.writeString(tempFilePath, fileText);

        String expectedResult =
            "JDK Alpha and Beta (1995)\n"
                + "JDK 1.0 (23rd Jan 1996)\n"
                + "JDK 1.1 (19th Feb 1997)\n"
                + "J2SE 1.2 (8th Dec 1998)\n"
                + "J2SE 1.3 (8th May 2000)\n"
                + "J2SE 1.4 (6th Feb 2002)\n"
                + "J2SE 5.0 (30th Sep 2004)\n"
                + "Java SE 6 (11th Dec 2006)\n"
                + "Java SE 7 (28th July 2011)\n"
                + "Java SE 8 (18th Mar 2014)\n"
                + "Java SE 9 (21st Sep 2017)\n"
                + "Java SE 10 (20th Mar 2018)\n"
                + "Java SE 11 (September 2018)\n"
                + "Java SE 12 (March 2019)\n"
                + "Java SE 13 (September 2019)\n"
                + "Java SE 14 (Mar 2020)\n"
                + "Java SE 15 (September 2020)\n"
                + "Java SE 16 (Mar 2021)\n"
                + "Java SE 17 (September 2021)\n"
                + "Java SE 18 (to be released by March 2022)";

        EncryptingData encryptingData = EncryptingData.builder().withSourceTextPath(tempFilePathString).build();

        assertAll(
            () -> assertTrue(Files.exists(tempFilePath), "File should exist"),
            () -> assertEquals(expectedResult, fileReader.read(encryptingData)));
    }

    @Test
    void read_shouldReturnIllegalArgumentException_whenInputIsNotCorrectPathAndFileNotExist() {

        String notCorrectFilePathString = "notCorrectPath";
        EncryptingData encryptingData = EncryptingData.builder().withSourceTextPath(notCorrectFilePathString).build();
        assertThrows(IllegalArgumentException.class, () -> fileReader.read(encryptingData));
    }
}
