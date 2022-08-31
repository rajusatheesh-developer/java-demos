package com.demos.java.sdk.features;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class FilesNewFeaturesTest {

    private FilesNewFeatures filesNewFeatures;

    @BeforeEach
    void setUp() {
        filesNewFeatures = new FilesNewFeatures();
    }

    @Test
    void testInstance() {
        Assertions.assertNotNull(filesNewFeatures);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "D:\\Java-Int\\Java\\JavaDemos\\files\\file1.txt,sample text",
            "D:\\Java-Int\\Java\\JavaDemos\\files\\file2.txt, sample text2",
            "D:\\Java-Int\\Java\\JavaDemos\\files\\file3.txt, sample text3"
    })
    @DisplayName("Test Files.readString()/writeString() ")
    void shouldReadStringAsContentForGivenPath(String fileName, String expectedContent) {
        String result = null;
        try {
            result = filesNewFeatures.read(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(expectedContent, result);
    }

    @Test
    void test_writeString_and_textblocks_method() throws IOException {
        var lines = """
                Satheesh
                Raju
                Siva
                Vani
                """;
        var fileName = filesNewFeatures.write(lines);
        var result = filesNewFeatures.read(fileName);
        Assertions.assertEquals(lines, result);

    }
}
