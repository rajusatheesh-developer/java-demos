package com.demos.java.sdk.features;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class CollectionsNewFeaturesTest {

    private CollectionsNewFeatures collectionsNewFeatures;

    @BeforeEach
    void setUp() {
        collectionsNewFeatures = new CollectionsNewFeatures();
    }


    @Test
    @DisplayName("Test checks instance creation")
    void testInstance() {
        Assertions.assertNotNull(collectionsNewFeatures);
    }

    @Test
    @DisplayName("Test validates list is immutable or not when we use List.CopyOf()")
    void copyOfShouldReturnImmutableList() {

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        List<Integer> list = collectionsNewFeatures.getImmutableWithCopyOf(arrayList);
        Assertions.assertEquals(arrayList.size(), list.size());

        Assertions.assertThrows(UnsupportedOperationException.class, () -> list.add(3));

    }

    @Test
    @DisplayName("Test validates set is immutable or not when we use Set.CopyOf()")
    void copyOfShouldReturnImmutableSet() {

        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        Set<Integer> resultSet = collectionsNewFeatures.getImmutableWithCopyOf(hashSet);
        Assertions.assertEquals(hashSet.size(), resultSet.size());

        Assertions.assertThrows(UnsupportedOperationException.class, () -> resultSet.add(3));

    }

    @Test
    @DisplayName("Test validates Map is immutable or not when we use Map.CopyOf()")
    void copyOfShouldReturnImmutableMap() {

        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put(UUID.randomUUID().toString(), 1);
        hashMap.put(UUID.randomUUID().toString(), 2);

        Map<String, Integer> resultSet = collectionsNewFeatures.getImmutableWithCopyOf(hashMap);
        Assertions.assertEquals(hashMap.size(), resultSet.size());

        Assertions.assertThrows(UnsupportedOperationException.class, () -> resultSet.put(UUID.randomUUID().toString(), 3));

    }
}
