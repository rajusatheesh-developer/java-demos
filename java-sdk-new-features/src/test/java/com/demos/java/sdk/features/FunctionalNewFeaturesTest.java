package com.demos.java.sdk.features;


import com.demos.java.sdk.features.providers.EvenNumbersSourceProvider;
import com.demos.java.sdk.features.providers.OddNumbersSourceProvider;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FunctionalNewFeaturesTest {

    private FunctionalNewFeatures functionalNewFeatures;

    @BeforeEach
    void setUp() {
        functionalNewFeatures = new FunctionalNewFeatures();
    }

    @DisplayName("Test the instance")
    @Disabled
    @Test
    void testInstance() {
        Assertions.assertNotNull(functionalNewFeatures);
    }

    @DisplayName("Test the Predicate.not method")
    @ParameterizedTest
    @ArgumentsSource(EvenNumbersSourceProvider.class)
    void testPredicateNot(List<Integer> data, List<Integer> expected) {
        Assertions.assertEquals(expected, functionalNewFeatures.getEvenNumbers(data));
    }

    @DisplayName("Test the Predicate.not method-1")
    @ParameterizedTest
    @ArgumentsSource(OddNumbersSourceProvider.class)
    void testPredicateNot_1(List<Integer> data, List<Integer> expected) {
        Assertions.assertEquals(expected, functionalNewFeatures.getOddNumbers(data));
    }
}
