package com.ironman.ma.Algorithms.Searching.negation.frequency.counting;

/**
 * Created by 127.0.0.1.ma on 10/12/17.
 */
public class Solution {

    private static int[] swap(int pos1, int pos2, int[] A) {
        int temp = A[pos1];
        A[pos1] = A[pos2];
        A[pos2] = temp;
        System.out.printf("\npost swapping %d and %d\n", pos1, pos2);
        for (int i = 0; i < A.length; i++) {
            System.out.printf("(%d,%d), ", i, A[i]);
        }
        return A;
    }

    private static void countFreq(int[] A) {
        System.out.printf("\nBEGIN %d\n", A.length);
        for (int i = 0; i < A.length; i++) {
            System.out.printf("(%d,%d), ", i, A[i]);
        }
        int pos = 0;
        do {
            int expectedPos = A[pos] - 1;
            if (A[pos] > 0 && A[expectedPos] > 0) {
//                int temp = A[pos];
//                A[pos] = A[expectedPos];
//                A[expectedPos] = temp;
                swap(A[pos], A[expectedPos], A);
                A[expectedPos] = -1;
            } else if (A[pos] > 0) {
                A[expectedPos]--;
                A[pos++] = 0;
            } else {
                pos++;
            }
            System.out.println("");
            for (int i = 0; i < A.length; i++) {
                System.out.printf("(%d,%d), ", i, A[i]);
            }
            System.out.printf("pos:%d, exPos:%d", pos, expectedPos);
        } while (pos < A.length);
        System.out.println("");
        for (int i = 0; i < A.length; i++) {
            System.out.printf("%d freq:%d \n", i + 1, Math.abs(A[i]));
        }
    }

    public static void main(String[] args) {
        countFreq(new int[]{10, 10, 9, 4, 7, 6, 5, 2, 3, 2, 1});
    }
}
