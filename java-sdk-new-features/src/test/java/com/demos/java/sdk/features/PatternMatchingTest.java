package com.demos.java.sdk.features;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PatternMatchingTest {
    private ProcessManager processManager;

    @BeforeEach
    void setUp() {
        processManager = new ProcessManager();
    }

    @Test
    void testInstance() {
        Assertions.assertNotNull(processManager);
    }

    @Test
    void test_instance_of() {
        var list = List.of(100, BigDecimal.ONE, "Java");
        String result = processManager.process(list);
    }
}
