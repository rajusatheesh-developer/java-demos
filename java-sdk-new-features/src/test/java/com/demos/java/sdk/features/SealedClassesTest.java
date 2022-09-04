package com.demos.java.sdk.features;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SealedClassesTest {

    private TrafficController trafficController;

    @BeforeEach
    void setUp() {
        trafficController = new TrafficController();
    }

    @Test
    void testInstance() {
        Assertions.assertNotNull(trafficController);
    }

    @Test
    void test_controller() {
        String signal = "Red";
        String expected = "Stop";
        Assertions.assertEquals(expected, trafficController.onSignal(signal));
    }

    @Test
    void test_controller_next() {
        String signal = "Red";
        String expected = "Stop";
        Assertions.assertEquals(expected, trafficController.next(signal));
    }
}
