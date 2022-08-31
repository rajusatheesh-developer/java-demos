package com.demos.java.sdk.features;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FunctionalNewFeatures {
    public List<Integer> getEvenNumbers(List<Integer> numbers) {
        return numbers.stream().filter(FunctionalNewFeatures::isEven).collect(Collectors.toList());
    }

    public List<Integer> getOddNumbers(List<Integer> numbers) {
        return numbers.stream().filter(Predicate.not(FunctionalNewFeatures::isEven)).collect(Collectors.toList());
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
}
