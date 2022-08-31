package com.demos.java.sdk.features;

import java.util.EnumSet;
import java.util.stream.Collectors;

public enum DayOfWeek {

    SUNDAY(0, "Sunday"),
    MONDAY(1, "Monday"),
    TUESDAY(2, "Tuesday"),
    WEDNESDAY(3, "Wednesday"),
    THURSDAY(4, "Thursday"),
    FRIDAY(5, "Friday"),
    SATURDAY(6, "Saturday");

    private final int number;
    private final String day;

    DayOfWeek(int number, String day) {
        this.number = number;
        this.day = day;
    }

    public static String findDay(int number) {
        return EnumSet.allOf(DayOfWeek.class)
                .stream()
                .filter(d -> d.number == number)
                .findFirst()
                .get().day;
    }
}
