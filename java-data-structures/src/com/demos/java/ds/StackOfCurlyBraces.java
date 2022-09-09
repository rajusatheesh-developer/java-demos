package com.demos.java.ds;

import java.util.Stack;

/**
 * For each character of the given string, take one of the following decisions:
 * a. If the character is an opening curly brace, {, then put it on the stack.
 * <p>
 * b. If the character is a closing curly brace, }, then do the following:
 * <p>
 * i. Check the top of stack, and if it is {, pop and move it to the next character.
 * <p>
 * ii. If it is not {, then return false.
 * <p>
 * If the stack is empty, return true (we found all pairs); otherwise, return false (the stack contains curly braces that do not match).
 */

public class StackOfCurlyBraces {
    public static void main(String[] args) {
        String str = "{{{}}}{}{{}}{";
        System.out.println(isValid(str));
    }

    private static boolean isValid(String str) {

        char[] chars = str.toCharArray();
        Stack<Character> characterStack = new Stack<>();
        for (char ch : chars) {
            if (ch == '{') {
                characterStack.push(ch);
            } else if (ch == '}') {
                if (characterStack.isEmpty()) { // we found a mismatch
                    return false;
                }
                char top = characterStack.peek();
                if (top == '{') {

                    characterStack.pop();

                } else
                    return false;
            }
        }
        return characterStack.empty();
    }
}
