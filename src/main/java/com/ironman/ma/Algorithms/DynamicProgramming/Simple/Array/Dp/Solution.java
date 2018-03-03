package com.ironman.ma.Algorithms.DynamicProgramming.Simple.Array.Dp;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Solution {
    public int longestSubsequenceLength(final List<Integer> A) {
        int[] LDS_RL = new int[A.size()];
        int[] LDS_LR = new int[A.size()];
        int allMax = 0;
        //fill lds first
        LDS_RL[A.size() - 1] = 1;
        for (int i = A.size() - 2; i >= 0; i--) {
            int max = 0;
            for (int j = i + 1; j < A.size(); j++) {
                if (A.get(j) < A.get(i)) {
                    max = Math.max(LDS_RL[j], max);
                }
            }
            LDS_RL[i] = max + 1;
        }
        //max out on LIS
        LDS_LR[0] = 1;
        for (int i = 0; i < A.size(); i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (A.get(j) < A.get(i)) {
                    max = Math.max(LDS_LR[j], max);
                }
            }
            LDS_LR[i] = max + 1;
            allMax = Math.max(allMax, LDS_LR[i] + LDS_RL[i] - 1);
        }
        return allMax;
    }

    public int solve(int[][] A) {
        int high = 0;
        int[][] aux = new int[A.length][A[0].length];

        for (int col = 0; col < A[0].length; col++) {
            int max1 = 0;
            for (int row = 0; row < A.length; row++) {
                if (A[row][col] == 1) {
                    max1++;
                } else {
                    max1 = 0;
                }
                aux[row][col] = max1;
            }
        }
        for (int[] anAux : aux) {
            Arrays.sort(anAux);
            for (int col = 0; col < aux[0].length; col++) {
                high = Math.max(high, (aux[0].length - col) * anAux[col]);
            }
        }
        //sort columns
        return high;
    }

    public int numDecodings(String A) {
        if (A == null || A.isEmpty()) {
            return 0;
        }
        char[] arr = A.toCharArray();
        long[] sumArr = new long[arr.length];
        sumArr[0] = 1;
        if (arr[0] == '0') {
            sumArr[0] = 0;
        }
        if (arr.length == 1) {
            return (int) sumArr[0];
        }

        if (arr[1] != '0') {
            sumArr[1] = sumArr[0];
        }
        if (Integer.parseInt(arr[0] + "" + arr[1]) < 27) {
            if (arr[0] == '0' && arr[1] == '0') {
                sumArr[1] = 0;
            } else if (arr[0] != '0') {
                sumArr[1] += 1;
            } else if (arr[0] == '0') {
                sumArr[1] = 1;
            }
        } else {
            if (arr[1] != '0') {
                sumArr[1] = sumArr[0];
            }
        }

        for (int i = 2; i < arr.length; i++) {
            long ans = 0;
            if (arr[i] != '0') {
                ans += sumArr[i - 1];
            }
            if (Integer.parseInt(arr[i - 1] + "" + arr[i]) < 27) {
                if (arr[i - 1] == '0' && arr[i] == '0') {
                    ans = 0;
                } else if (arr[i - 1] != '0') {
                    ans += sumArr[i - 2];
                }
            } else {
                if (arr[i] != '0') {
                    ans = sumArr[i - 1];
                }
            }
            sumArr[i] = ans;
        }
        return (int) sumArr[sumArr.length - 1];
    }

}
