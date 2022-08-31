package com.demos.java.sdk.features;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RecordsTest {
    record Person(String name, int age) {

    }

    @Test
    void test_records_equals() {
        var person1 = new Person("Satheesh", 10);
        var person2 = new Person("Satheesh", 10);
        Assertions.assertEquals(person1, person2);
    }

    @Test
    void test_records_equals_not() {
        var person1 = new Person("Satheesh", 10);
        var person2 = new Person("Raju", 10);
        Assertions.assertNotEquals(person1, person2);
    }
}
