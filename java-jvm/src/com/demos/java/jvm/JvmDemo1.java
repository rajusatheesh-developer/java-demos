package com.demos.java.jvm;

import java.lang.reflect.Method;

public class JvmDemo1 {
    public static void main(String[] args) throws ClassNotFoundException {

        //alwaysLoadSingleClass();

        classInfoUsedByProgrammer();


    }

    private static void classInfoUsedByProgrammer() throws ClassNotFoundException {
        Class cls = Class.forName(String.valueOf(Student.class));
        Method[] methods = cls.getMethods();
        Method[] declaredMethods = cls.getDeclaredMethods();

        System.out.println("Method Count : " + methods.length);
        for (Method m : methods) {
            System.out.println(m.getName());
        }

        System.out.println("Declared Method Count : " + methods.length);
        for (Method m : declaredMethods) {
            System.out.println(m.getName());
        }
    }


    private static void alwaysLoadSingleClass() {
        Student student = new Student("Satheesh");
        Student student1 = new Student("Raju");

        Class cl1 = student.getClass();
        Class cl2 = student1.getClass();
        System.out.println("Student : " + cl1.hashCode());
        System.out.println("Student1 : " + cl2.hashCode());
        System.out.println(cl1 == cl2);
    }
}
