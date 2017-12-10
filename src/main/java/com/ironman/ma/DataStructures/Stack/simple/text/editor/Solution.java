package com.ironman.ma.DataStructures.Stack.simple.text.editor;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by 127.0.0.1.ma on 20/10/17.
 * <p>
 * In this challenge, you must implement a simple text editor. Initially, your editor contains an empty string, . You must perform  operations of the following  types:
 * <p>
 * append - Append string  to the end of .
 * delete - Delete the last  characters of .
 * print - Print the  character of .
 * undo - Undo the last (not previously undone) operation of type  or , reverting  to the state it was in prior to that operation.
 */
public class Solution {
//    class Action {
//        int actionType;
//        int actionValueInt;
//        String actionValueStr;
//    }
//
//    static Stack<Action> actionStack = new Stack<Action>();
//    static Stack<String> hotDataStack = new Stack<String>();
//    static Stack<String> deletedDataStack = new Stack<String>();
//    static HashMap<Character, Integer> indexMap = new HashMap<Character, Integer>();

    static String superStr = "";

    static void print(int caseType) {
        System.out.println(caseType + " : " + superStr);
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        Stack<String> moveStack = new Stack<String>();
        for (int i = 0; i < cases; i++) {
            int caseType = input.nextInt();
            switch (caseType) {
                case 1:
                    String app = input.next();
                    superStr += app;
                    moveStack.push("+" + app.length());
                    break;
                case 2:
                    int removals = input.nextInt();
                    int len = superStr.length();
                    String rem = superStr.substring(len - removals, len);
                    superStr = superStr.substring(0,len - removals);
                    moveStack.push("-" + rem);
                    break;
                case 3:
                    int location = input.nextInt() - 1;
                    System.out.println(superStr.charAt(location));
                    break;
                case 4:
                    String actionData = moveStack.pop();
                    if (actionData.charAt(0) == ('+')) {
                        /*
                        remove rem
                         */
                        int lenToBeRemoved = Integer.parseInt(actionData.substring(1));
                        superStr = superStr.substring(0, superStr.length() - lenToBeRemoved);
                    } else {
                        String toBeAdded = actionData.substring(1);
                        superStr += toBeAdded;
                    }
                    break;
                default:
                    break;
            }
//            print(caseType);
        }
        input.close();
    }
}
