package com.demos.java.absics;

public class A {
    static {
        System.out.println("static");
    }

    {
        System.out.println("instance");
    }

    static class B {
    }

    ;

    class C {
    }

    ;

    enum E {}

    ;

    interface I {
    }

    public static void main(String[] args) {
        A a=new A();

    }
}