package com.ironman.ma.DataStructures.Array.saddle.points;

import java.util.Scanner;

/**
 * Created by 127.0.0.1.ma on 20/11/17.
 */
public class Solution {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][] matrix = new int[5][5];
        int[] rowMax = new int[5];
        int[] rowMaxIndex = new int[5];
        int[] colMin = new int[5];
        int[] colMinIndex = new int[5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int num = input.nextInt();
                matrix[i][j] = num;
                rowMax[i] = Math.max(rowMax[i], num);

            }
        }
    }
}
