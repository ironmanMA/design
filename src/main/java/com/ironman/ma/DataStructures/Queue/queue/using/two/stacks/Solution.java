package com.ironman.ma.DataStructures.Queue.queue.using.two.stacks;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by 127.0.0.1.ma on 21/10/17.
 */
public class Solution {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();

        Stack<Integer> stackIns = new Stack<Integer>(),
                stackOut = new Stack<Integer>();

        for (int i = 0; i < cases; i++) {
            int queryType = input.nextInt();
            switch (queryType) {
                case 1:
                    // Enqueue
                    int node = input.nextInt();
                    stackIns.push(node);
                    break;
                case 2:
                    if (stackOut.size() == 0) {
                        while (stackIns.size() > 0) {
                            int cand = stackIns.pop();
                            stackOut.push(cand);
                        }
                    }
                    stackOut.pop();
                    break;
                case 3:
                    if (stackOut.size() == 0) {
                        while (stackIns.size() > 0) {
                            int cand = stackIns.pop();
                            stackOut.push(cand);
                        }
                    }
                    System.out.println(stackOut.peek());
                    break;
                default:
                    break;
            }
        }
        input.close();
    }
}
