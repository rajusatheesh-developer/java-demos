package com.demos.java.jvm;

public class ClassLoaderDemo
{

    public static void main(String[] args) {

        System.out.println(String.class.getClassLoader()); // null because Bootstrap class loader is not Java Object
        System.out.println(ClassLoaderDemo.class.getClassLoader());
        System.out.println(Student.class.getClassLoader());


    }
}
