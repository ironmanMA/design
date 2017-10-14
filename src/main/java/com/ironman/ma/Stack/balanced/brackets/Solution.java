package com.ironman.ma.Stack.balanced.brackets;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by 127.0.0.1.ma on 08/10/17.
 */

public class Solution {

    static String isBalanced(String s) {
        // Complete this function
        Stack<Character> symbolStack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            char stackTopChar = 'A';
            if (symbolStack.size() > 0) {
                stackTopChar = symbolStack.peek();
            }
            switch (currChar) {
                case '{':
                    symbolStack.push(currChar);
                    break;
                case '(':
                    symbolStack.push(currChar);
                    break;
                case '[':
                    symbolStack.push(currChar);
                    break;
                case ']':
                    if (stackTopChar == '[') {
                        symbolStack.pop();
                    } else {
                        return "NO";
                    }
                    break;
                case '}':
                    if (stackTopChar == '{') {
                        symbolStack.pop();
                    } else {
                        return "NO";
                    }
                    break;
                case ')':
                    if (stackTopChar == '(') {
                        symbolStack.pop();
                    } else {
                        return "NO";
                    }
                    break;
            }
        }
        if (symbolStack.size() > 0)
            return "NO";
        return "YES";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            String s = in.next();
            String result = isBalanced(s);
            System.out.println(result);
        }
        in.close();
    }
}
