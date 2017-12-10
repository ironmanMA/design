package com.ironman.ma.DataStructures.Queue.castle.on.the.grid;

import java.util.Scanner;

/**
 * Created by 127.0.0.1.ma on 28/10/17.
 * <p>
 * You are given a grid with both sides equal to . Rows and columns are numbered from  to . There is a castle on the intersection (, ). In a single step you move from a point () to a point () if there is a straight horizontal line or a straight vertical line connecting these two and not containing any forbidden cell. Here, "X" denotes a forbidden cell.
 * <p>
 * Your task is to calculate the minimum number of steps it would take to move the castle from its initial position to the goal position ().
 * <p>
 * It is guaranteed that it is possible to reach the goal position from the initial position.
 * <p>
 * Input Format
 * <p>
 * The first line contains an integer , the size of the grid.
 * The following  lines contains a string of length  that consists of one of the following characters: "X" or ".". Here, "X" denotes a forbidden cell, and "." denotes an allowed cell.
 * The last line contains , , denoting the initial position of the castle, and , , denoting the goal position. Here, , ,  and  are space separated.
 */
public class Solution {

    static int minimumMoves(String[] grid, int startX, int startY, int goalX, int goalY) {
        // Complete this function

        return 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] grid = new String[n];
        for (int grid_i = 0; grid_i < n; grid_i++) {
            grid[grid_i] = in.next();
        }
        int startX = in.nextInt();
        int startY = in.nextInt();
        int goalX = in.nextInt();
        int goalY = in.nextInt();
        int result = minimumMoves(grid, startX, startY, goalX, goalY);
        in.close();
    }
}
