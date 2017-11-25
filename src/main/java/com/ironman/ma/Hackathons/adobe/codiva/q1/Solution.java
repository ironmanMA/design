package com.ironman.ma.Hackathons.adobe.codiva.q1;

import java.util.Scanner;

/**
 * Created by 127.0.0.1.ma on 25/11/17.
 */
public class Solution {
//    ArrayList<Integer> primes = new ArrayList<>(range);

    public static boolean isPrime(int n) {
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    static void findDiff(int start, int end) {
        int p1 = 0;
        int p2 = 0;
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                p1 = i;
                break;
            }
        }

        for (int j = end; j >= start; j--) {
            if (isPrime(j)) {
                p2 = j;
                break;
            }
        }
        System.out.println(p1 + "," + p2 + " diff: " + Math.abs(p1 - p2));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numCases = input.nextInt();
        for (int i = 0; i < numCases; i++) {
            findDiff(input.nextInt(), input.nextInt());
        }
    }
}
