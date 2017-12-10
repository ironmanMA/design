package com.ironman.ma.DataStructures.Array.nearest.integer.in.a.sorted.list;

/**
 * Created by 127.0.0.1.ma on 01/12/17.
 */
public class Solution {
    public static void main(String[] args) {
        int[] list = {1, 2, 4, 5, 6, 12, 15, 22, 27};
        int numInterest = 9;
        int low = 0, high = list.length - 1;
        if (numInterest > list[high]) {
            System.out.println(list[high]);
        } else if (numInterest < list[low]) {
            System.out.println(list[low]);
        } else {
            while (low < high) {
                int comparatorIndex = low + (high - low) / 2;
                if (numInterest < list[comparatorIndex]) {
                    if (numInterest > list[comparatorIndex - 1]) {
                        if ((numInterest - list[comparatorIndex - 1]) > (list[comparatorIndex] - numInterest))
                            System.out.println(list[comparatorIndex]);
                        else
                            System.out.println(list[comparatorIndex - 1]);
                        break;
                    } else {
                        high = comparatorIndex;
                    }
                } else {
                    if (numInterest < list[comparatorIndex + 1]) {
                        if ((numInterest - list[comparatorIndex]) > (list[comparatorIndex + 1] - numInterest))
                            System.out.println(list[comparatorIndex + 1]);
                        else
                            System.out.println(list[comparatorIndex]);
                        break;
                    } else {
                        low = comparatorIndex;
                    }
                }
            }
        }
    }
}
