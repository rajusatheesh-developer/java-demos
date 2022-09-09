package com.demos.java.ds;

import java.util.Stack;

public class ReverseStringUsingStack {
    public static void main(String[] args) {
        String str = "Satheesh";
        Stack<Character> stack = new Stack<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i = i + 1) {
            stack.push(chars[i]);
        }

        for (int i = 0; i < chars.length; i = i + 1) {
            chars[i] = stack.pop();
        }

        System.out.println(chars);
    }
}
