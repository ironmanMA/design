package com.ironman.ma.DataStructures.Stack.maximum.element;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by 127.0.0.1.ma on 07/10/17.
 */
public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Stack<Integer> queryStack = new Stack<Integer>();
        int currMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int queryType = input.nextInt();
            switch (queryType) {
                case 1:
                    int dataToPush = input.nextInt();
                    int actualPush = dataToPush;
                    if (dataToPush > currMax) {
                        if (queryStack.size() > 0) {
                            actualPush = 2 * dataToPush - currMax;
                        }
                        currMax = dataToPush;
                    }
                    queryStack.push(actualPush);
//                    System.out.println(actualPush);
//                    System.out.println(queryType + ", " + queryStack.size() + ", " + currMax);
                    break;
                case 2:
                    int popedData = queryStack.pop();
                    if (popedData > currMax) {
                        currMax = 2 * (currMax) - popedData;
                    }
                    if (queryStack.size() == 0) {
                        currMax = Integer.MIN_VALUE;
                    }
//                    System.out.println(popedData);
//                    System.out.println(queryType + ", " + queryStack.size() + ", " + currMax);
                    break;
                case 3:
                    System.out.println(currMax);
//                    System.out.println(queryType + ", " + queryStack.size() + ", " + currMax);
                    break;
                default:
                    break;
            }
        }
        input.close();
    }
}
