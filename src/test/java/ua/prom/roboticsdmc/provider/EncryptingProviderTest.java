package ua.prom.roboticsdmc.provider;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.prom.roboticsdmc.creater.FileCreater;
import ua.prom.roboticsdmc.domain.EncryptingData;
import ua.prom.roboticsdmc.encrypter.Encrypter;
import ua.prom.roboticsdmc.encrypter.PathCreater;
import ua.prom.roboticsdmc.reader.FileReader;
import ua.prom.roboticsdmc.validator.Validator;

@ExtendWith(MockitoExtension.class)
class EncryptingProviderTest {

    @Mock
    Validator validator;

    @Mock
    FileReader fileReader;

    @Mock
    Encrypter encrypter;

    @Mock
    PathCreater pathCreater;

    @Mock
    FileCreater fileCreater;

    @InjectMocks
    EncryptingProvider encryptingProvider;

    @TempDir
    static Path tempDir;

    @Test
    void provideEncrypting_shouldReturnCorrectFile_whenInputPathAndCommandAndKeyAreCorrect()
        throws IOException {

        String command = "ENCRYPT";
        int key = 10;
        Path tmpSrcFilePath = tempDir.resolve("TempFile.txt");
        Path tmpResFilePath = tempDir.resolve("[ENCRYPTED]TempFile.txt");

        String sourceFileText =
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

        String resultText =
            "TNU Kvzrk kxn Lodk (1995)\n"
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

        Files.writeString(tmpSrcFilePath, sourceFileText);
        String tmpSrcFilePathStr = tmpSrcFilePath.toString();
        String tmpResFilePathStr = tmpResFilePath.toString();
        File resultFile = new File(tmpResFilePathStr);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile))) {
            writer.write(resultText);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        EncryptingData encryptingData = EncryptingData.builder()
            .withSourceTextPath(tmpSrcFilePathStr)
            .withCommand(command)
            .withKey(key).build();

        doNothing().when(validator).validate(encryptingData);
        when(fileReader.read(encryptingData)).thenReturn(sourceFileText);
        when(encrypter.encryptText(sourceFileText, encryptingData)).thenReturn(resultText);
        when(pathCreater.createPath(encryptingData)).thenReturn(tmpResFilePathStr);
        doNothing().when(fileCreater).createFile(resultText, tmpResFilePathStr);

        assertAll(
            () -> encryptingProvider.provideEncrypting(encryptingData),
            () -> assertTrue(resultFile.exists()),
            () -> assertTrue(resultFile.length() > 0)
        );

        InOrder inOrder = inOrder(validator, fileReader, encrypter, pathCreater, fileCreater);
        inOrder.verify(validator).validate(encryptingData);
        inOrder.verify(fileReader).read(encryptingData);
        inOrder.verify(encrypter).encryptText(sourceFileText, encryptingData);
        inOrder.verify(pathCreater).createPath(encryptingData);
        inOrder.verify(fileCreater).createFile(resultText, tmpResFilePathStr);
    }
}
