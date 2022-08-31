package com.demos.java.sdk.features;

public class SwitchExpressions {
    public String findDayOfWeek(int number) {
        return switch (number) {
            case 0 -> "Sunday";
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            default -> throw new IllegalStateException("Unexpected value: " + number);
        };
    }
}
