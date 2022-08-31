package com.demos.java.sdk.features;

import java.util.*;

public class CollectionsNewFeatures {
    public List<Integer> getImmutableWithCopyOf(ArrayList<Integer> arrayList) {
        return List.copyOf(arrayList);
    }

    public Set<Integer> getImmutableWithCopyOf(HashSet<Integer> hashSet) {
        return Set.copyOf(hashSet);
    }

    public Map<String, Integer> getImmutableWithCopyOf(Map<String, Integer> hashMap) {
        return Map.copyOf(hashMap);
    }
}
