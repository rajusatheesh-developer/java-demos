package com.demos.java.sdk.features;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class LocalVariableInferenceTest {
    private LocalVariableInference localVariableInference;

    @BeforeEach
    void setUp() {
        localVariableInference = new LocalVariableInference();
    }

    @Test
    void test_var_behaviour() {
        var list = List.of(1, 10, 100);
        var expectedList = List.of(10, 100);
        var result = localVariableInference.exclude(list, 1);
        Assertions.assertEquals(expectedList, result);
    }
}
