package com.ironman.ma.DataStructures.Array.max.non.negative.subarray;

import java.util.ArrayList;

/**
 * Created by 127.0.0.1.ma on 25/08/17.
 */
public class Solution {
    public static void main(String[] args) {
//        4 1967513926 1540383426 - 1303455736 - 521595368
//        Integer a = 3507897352;
        int[] list = {1, 2, 5, -7, 2, 3};
    }

    public ArrayList<Integer> maxset(ArrayList<Integer> a) {

        ArrayList<Integer> maxArr = new ArrayList<Integer>();
        float maxSum = -1, minStartIndex = -1;
        ArrayList<Integer> subArr = new ArrayList<Integer>();
        float subSum = 0, startIndex = -1;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) < 0) {
                if (subArr.size() > 0) {
                    if (subSum > maxSum) {
                        maxArr = (ArrayList<Integer>) subArr.clone();
                        maxSum = subSum;
                    } else if (subSum == maxSum) {
                        if (subArr.size() > maxArr.size()) {
                            maxArr = (ArrayList<Integer>) subArr.clone();
                        }
                    }
                }
                subArr = new ArrayList<Integer>();
                subSum = 0;
            } else {
                subArr.add(a.get(i));
                subSum += a.get(i) / 1000;
            }
        }

        if (subArr.size() > 0) {
            if (subSum > maxSum) {
                maxArr = (ArrayList<Integer>) subArr.clone();
            } else if (subSum == maxSum) {
                if (subArr.size() > maxArr.size()) {
                    maxArr = (ArrayList<Integer>) subArr.clone();
                }
            }
        }
        return maxArr;
    }
}
