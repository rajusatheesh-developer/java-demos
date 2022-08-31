package com.demos.java.sdk.features;

import java.util.List;
import java.util.stream.Collectors;

public class LocalVariableInference {
    public List<Integer> exclude(List<Integer> list, int i) {
        var result = list.stream().filter(d -> d != i).collect(Collectors.toList());
        return result;
    }
}
