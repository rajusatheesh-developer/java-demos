package com.demos.java.absics;


public class Person {
    private String name;
    private String address;

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

    private static class PersonBuilder {

        private Person person = new Person();

        public PersonBuilder name(String name) {
            person.name = name;
            return this;
        }

        public PersonBuilder address(String address) {
            person.address = address;
            return this;
        }

        public Person build() {
            Person personInstance = person;
            person = null;
            return personInstance;
        }
    }

}
