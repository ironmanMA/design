package com.ironman.ma.Algorithms.DynamicProgramming.ConnectedCellGrid;

import java.util.Scanner;

/*
5
5
0 1 1 1 1
1 0 0 0 1
1 1 0 1 0
0 1 0 1 1
0 1 1 1 0
 */

public class Solution {

    static int[][] numMatrix;
    static int numRow,numCol;
    private static int giveAllNeighbourMAX(int row, int col) {
        if(((row<0 || col<0) || (col>=numCol || row>=numRow))  ||  numMatrix[row][col] == 0 ||
                numMatrix[row][col] == -1 )
            return 0;
        /*
        look for
            row-1,col-1
            row-1, col
            row-1, col+1
            row, col-1
            row, col+1
            row+1,col-1
            row+1, col
            row+1, col+1

         */
        numMatrix[row][col]=-1;
        return 1+giveAllNeighbourMAX(row-1,col-1)+
                giveAllNeighbourMAX(row-1, col)+
                giveAllNeighbourMAX(row, col-1)+

                giveAllNeighbourMAX(row, col-1)+
                giveAllNeighbourMAX(row, col+1)+

                giveAllNeighbourMAX(row+1,col-1)+
                giveAllNeighbourMAX(row+1, col)+
                giveAllNeighbourMAX(row+1, col+1);
    }

    private static int giveUpperNeighbourMAX(int[][] matrix, int row, int col) {
        /*
        look for
            row, col-1
            row-1,col-1
            row-1, col
            row-1, col+1
         */
        int left = Math.max(matrix[row][col - 1], matrix[row - 1][col - 1]);
        int right = Math.max(matrix[row - 1][col], matrix[row - 1][col + 1]);
        return Math.max(left, right);
    }

    private static int reconstructForMax(int[][] matrix, int row, int col) {
        int max = -1;
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < col - 1; j++) {
                if (matrix[i][j] == 1) {
                    int connectedTillHere = giveUpperNeighbourMAX(matrix, i, j) + 1;
                    max = Math.max(max, connectedTillHere);
                    matrix[i][j] = connectedTillHere;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner input = new Scanner(System.in);
        numRow = Integer.parseInt(input.nextLine())+2;
        numCol = Integer.parseInt(input.nextLine())+2;
        int[][] matrix = new int[numRow][numCol];
        for (int i = 0; i < numRow-1; i++) {
            String[] row = input.nextLine().split(" ");
            for (int j = 0; j < numCol-2; j++) {
                matrix[i + 1][j + 1] = Integer.parseInt(row[j]);
            }
        }
        numMatrix=matrix;
        int max = -1;
        for (int i = 1; i < numRow - 1; i++) {
            for (int j = 1; j < numCol - 1; j++) {
                max=Math.max(max,giveAllNeighbourMAX(i,j));
            }
        }
        System.out.println(max);
    }
}