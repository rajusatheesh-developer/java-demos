package com.demos.java.sdk.features;


import com.demos.java.sdk.features.providers.StringDataSourceProvider;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StringsNewFeaturesTest {
    private StringsNewFeatures stringsNewFeatures;

    @BeforeEach
    void setUp() {
        stringsNewFeatures = new StringsNewFeatures();
    }


    @Disabled
    @DisplayName("Test the instance")
    @Test
    void testInstance() {
        Assertions.assertNotNull(stringsNewFeatures);
    }

    @DisplayName("Test the isBlank()")
    @ParameterizedTest
    @CsvSource(value = {
            "' ',true",
            "raju,false"
    })
    void test_isBlank_method(String input, boolean expected) {
        Assertions.assertEquals(expected, stringsNewFeatures.isBlank(input));
    }

    @DisplayName("Test the strip()")
    @ParameterizedTest
    @ArgumentsSource(StringDataSourceProvider.class)
    void test_strip_method(String input, String expected) {
        Assertions.assertEquals(expected, stringsNewFeatures.strip(input));
    }

}
