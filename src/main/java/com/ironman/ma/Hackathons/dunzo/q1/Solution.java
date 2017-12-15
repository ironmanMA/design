package com.ironman.ma.Hackathons.dunzo.q1;

/**
 * Created by 127.0.0.1.ma on 15/12/17.
 */
public class Solution {
    /*
   * Complete the function below.
   */

    static int minDist(int a, int b) {
        int sum = 0;
        while (true) {
            if (a < 10 && b < 10) {
                sum += Math.abs(a - b);
                break;
            } else {
                int remA = a % 10;
                int remB = b % 10;
                sum += Math.abs(remA - remB);
                a = a / 10;
                b = b / 10;
            }
        }
        return sum;
    }

    static int minimumMoves(int[] a, int[] m) {
        int allSums = 0;
        for (int i = 0; i < a.length; i++) {
            allSums += minDist(a[i], m[i]);
        }
        return allSums;
    }

    public static void main(String[] args) {
//        System.out.println(minDist(4321,3214));
        System.out.println(minimumMoves(new int[]{1234, 4321}, new int[]{2345, 3214}));
    }
}
