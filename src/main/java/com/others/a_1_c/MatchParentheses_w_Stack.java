package com.others.a_1_c;

import java.util.Map;
import java.util.Stack;

public class MatchParentheses_w_Stack {
    public static boolean matchingParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        // value is map is the matching close symbol for the key
        Map<Character, Character> blockSymbols = Map.of(')','(', ']', '[', '>','<');
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (blockSymbols.containsValue(current)) {
                stack.push(current);
                continue;
            }
            if (blockSymbols.containsKey(current) &&
                    (stack.isEmpty() ||
                            blockSymbols.get(current) != stack.pop())) {
                return false;
            }
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        String t1 = "(<[ ]>)";
        String t2 = "[<incre>ment]";
        String t3 = "<increment>";
        String t4 = "()incre<>ment<>[]";

        String t5 = "<increment(";
        String t6 = "[)incr]ement(";
        String t7 = "<i(ncr>e)ment";
        String t8 = "(<increment>";

        System.out.println(matchingParentheses(t1));
        System.out.println(matchingParentheses(t2));
        System.out.println(matchingParentheses(t3));
        System.out.println(matchingParentheses(t4));

        System.out.println(matchingParentheses(t5));
        System.out.println(matchingParentheses(t6));
        System.out.println(matchingParentheses(t7));
        System.out.println(matchingParentheses(t8));

        balParenthesis("{()}{}]");
    }

    public static boolean balParenthesis(String exp)
    {
        // base case: length of the expression must be even
        if (exp.length() % 2 == 1) {
            return false;
        }

        // take an empty stack of characters
        Stack<Character> stack = new Stack();

        // traverse the input expression
        for (char c: exp.toCharArray())
        {
            // if the current character in the expression is an opening brace, push it into the stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            // if the current character in the expression is a closing brace
            if (c == ')' || c == '}' || c == ']')
            {
                // return false if a mismatch is found (i.e., if the stack is empty,
                // the expression cannot be balanced since the total number of opening
                // braces is less than the total number of closing braces)
                if (stack.empty()) {
                    return false;
                }
                // pop character from the stack
                Character top = stack.pop();
                // if the popped character is not an opening brace or does not pair
                // with the current character of the expression
                if ((top == '(' && c != ')') || (top == '{' && c != '}')
                        || (top == '[' && c != ']')) {
                    return false;
                }
            }
        }

        // the expression is balanced only when the stack is empty at this point
        return stack.empty();
    }
}
