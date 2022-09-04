package com.demos.java.sdk.features;

import java.util.List;

public class ProcessManager {

    public String process(Object data) {
        return switch (data) {
            case null -> "Null obj";
            case String i -> "String obj";
            case Integer i -> "Integer obj";
            case Integer  i -> "Integer obj";
            default -> throw new IllegalArgumentException("Not matched");
        };
        return null;
    }
}
