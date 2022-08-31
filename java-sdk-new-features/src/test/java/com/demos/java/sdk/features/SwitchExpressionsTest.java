package com.demos.java.sdk.features;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SwitchExpressionsTest {
    private SwitchExpressions switchExpressions;

    @BeforeEach
    void setUp() {
        switchExpressions = new SwitchExpressions();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0,Sunday",
            "1,Monday",
            "2,Tuesday",
            "3,Wednesday",
            "4,Thursday",
            "5,Friday",
            "6,Saturday"
    })
    void test_switch_expressions(int number, String expectedDay) {
        Assertions.assertEquals(expectedDay, switchExpressions.findDayOfWeek(number));
    }


    @ParameterizedTest
    @CsvSource(value = {
            "0,Sunday",
            "1,Monday",
            "2,Tuesday",
            "3,Wednesday",
            "4,Thursday",
            "5,Friday",
            "6,Saturday"
    })
    void test_day_of_week(int number, String expectedDay) {
        Assertions.assertEquals(expectedDay, DayOfWeek.findDay(number));
    }
}
