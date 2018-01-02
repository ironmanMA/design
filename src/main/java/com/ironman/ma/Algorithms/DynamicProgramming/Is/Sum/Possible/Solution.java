package com.ironman.ma.Algorithms.DynamicProgramming.Is.Sum.Possible;

import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    static HashMap<String, Boolean> memo = new HashMap<String, Boolean>();

    static boolean isSumPossib(int[] arr, int target, int startPos) {
        String cache = target + "|" + startPos;

        if (startPos >= arr.length || target < arr[startPos]) {
            memo.put(cache, false);
            return false;
        }
        if (memo.containsKey(cache)) {
            return memo.get(cache);
        }

        if (arr[startPos] == target) {
            memo.put(cache, true);
            return true;
        }

        boolean isP = isSumPossib(arr, target - arr[startPos], startPos + 1) ||
                isSumPossib(arr, target, startPos + 1);
        memo.put(cache, isP);
        return isP;
    }

    public static boolean groupSumWithNum(int[] arr, int must_have, int target) {
//groupSumWithNum({1,2,3,6,5},5,10) ==> true
        System.out.print("arr.length:" + arr.length + ", m_h:" + must_have + ", tg:" + target);
        if (arr == null | arr.length == 0) {
            return false;
        }

        int iter = 0;
        Arrays.sort(arr);

        int[] subArr = new int[arr.length - 1];
        boolean inFound = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == must_have) {
                inFound = true;
//                i++;
            } else {
                subArr[iter] = arr[i];
                iter++;
            }
        }
        if (!inFound) {
            return false;
        }

        return isSumPossib(subArr, target - must_have, 0);

    }
}
