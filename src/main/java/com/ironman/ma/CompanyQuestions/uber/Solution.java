package com.ironman.ma.CompanyQuestions.uber;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by 127.0.0.1.ma on 26/08/17.
 */
public class Solution {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        long[] numArry = new long[n];
        for (int i = 0; i < n; i++) {
            numArry[i] = input.nextLong();

        }
        int testcases = input.nextInt();
        HashMap<String, Long> cache = new HashMap<String, Long>();
//        for (int left = 0; left < n; left++) {
//            for (int right = left; right <= R; right++) {
////                        if (cache.get(left + ", " + right) != null) {
////                            System.out.println("cache hit");
////                            max = cache.get(left + ", " + R);
////                        } else {
////                        System.out.println(left + ", " + right+ ", max:");
////                            cache.put(left + ", " + right, max);
////                        }
//                max = Math.max(max, numArry[left] - numArry[right]);
//            }
//        }
        for (int i = 0; i < testcases; i++) {
            int L = input.nextInt();
            int R = input.nextInt();
            long max = Long.MIN_VALUE;
            if (L == R) {
                System.out.println(0);
            } else {
                for (int left = L; left < R; left++) {
                    for (int right = left; right <= R; right++) {
//                        if (cache.get(left + ", " + right) != null) {
//                            System.out.println("cache hit");
//                            max = cache.get(left + ", " + R);
//                        } else {
//                        System.out.println(left + ", " + right+ ", max:");
//                            cache.put(left + ", " + right, max);
//                        }
                        max = Math.max(max, numArry[left] - numArry[right]);
                    }
                }
                System.out.println(max);
            }
        }
        input.close();
    }
}
