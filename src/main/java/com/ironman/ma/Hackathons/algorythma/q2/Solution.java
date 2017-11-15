package com.ironman.ma.Hackathons.algorythma.q2;

import java.util.Scanner;

/**
 * Created by 127.0.0.1.ma on 14/11/17.
 */
public class Solution {

    static void findSmallestIntegerWithProductEquallingN(int N) {
        int j = 0, i = 0;
        int[] res = new int[100];
        if (N < 10) {
            System.out.println(N + 10);
        }

        for (i = 9; i > 1; i--) {
            while (N % i == 0) {
                N = N / i;
                res[j] = i;
                j++;
            }
        }

        if (N > 10) {
            System.out.println("-1");
        } else {
            for (i = j - 1; i >= 0; i--)
                System.out.print(res[i]);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numCases = input.nextInt();
        for (int i = 0; i < numCases; i++) {
            findSmallestIntegerWithProductEquallingN(input.nextInt());
        }
    }
}
