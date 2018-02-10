package com.ironman.ma.Algorithms.PandC.BackTracking.NQueen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class Solution {
    HashSet<Integer> rowTaken = new HashSet<Integer>();
    HashSet<Integer> colTaken = new HashSet<Integer>();
    HashMap<Integer, HashSet<String>> RTLBdiagTaken = new HashMap<Integer, HashSet<String>>();
    HashMap<Integer, HashSet<String>> LTRBdiagTaken = new HashMap<Integer, HashSet<String>>();
    ArrayList<ArrayList<String>> ans = new ArrayList<ArrayList<String>>();


    private ArrayList<String> fillUp(int[][] board) {
        ArrayList<String> ans = new ArrayList<String>();
        for (int r = 0; r < board.length; r++) {
            StringBuilder row = new StringBuilder();
            for (int c : board[r]) {
                if (c == 1) {
                    row.append("Q");
                } else {
                    row.append(".");
                }
            }
            ans.add(row.toString());
        }
        return ans;
    }

    private boolean isOpen(int row, int col) {
        if (rowTaken.contains(row) || colTaken.contains(col)
                || RTLBdiagTaken.containsKey(row + col) || LTRBdiagTaken.containsKey(row - col)) {
            return false;
        }
        return true;
    }

    private void register(int row, int col) {
        rowTaken.add(row);
        colTaken.add(col);

        HashSet<String> k = new HashSet<String>();
        if (RTLBdiagTaken.containsKey(row + col)) {
            k = RTLBdiagTaken.get(row + col);
        }
        k.add(row + "_" + col);
        RTLBdiagTaken.put(row + col, k);

        k = new HashSet<String>();
        if (LTRBdiagTaken.containsKey(row - col)) {
            k = LTRBdiagTaken.get(row - col);
        }
        k.add(row + "_" + col);
        LTRBdiagTaken.put(row - col, k);


    }

    private void unRegister(int row, int col) {
        rowTaken.remove(row);
        colTaken.remove(col);

        RTLBdiagTaken.get(row + col).remove(row + "_" + col);
        if (RTLBdiagTaken.get(row + col).size() == 0) {
            RTLBdiagTaken.remove(row + col);
        }

        LTRBdiagTaken.get(row - col).remove(row + "_" + col);
        if (LTRBdiagTaken.get(row - col).size() == 0) {
            LTRBdiagTaken.remove(row - col);
        }
    }

    public ArrayList<ArrayList<String>> solveNQueens(int a) {
        ans = new ArrayList<ArrayList<String>>();
        int[][] board = new int[a][a];
        rowTaken = new HashSet<Integer>();
        colTaken = new HashSet<Integer>();
        RTLBdiagTaken = new HashMap<Integer, HashSet<String>>();
        LTRBdiagTaken = new HashMap<Integer, HashSet<String>>();

        solveSubQueens(board, 1, a);
        return ans;
    }

    public int[][] solveSubQueens(int[][] board, int nextRow, int max) {
        if (rowTaken.size() == max) {
            return board;
        }
        for (int row = nextRow; row <= max; row++) {
            for (int col = 1; col <= max; col++) {
                if (isOpen(row, col)) {
                    register(row, col);
                    board[row - 1][col - 1] = 1;
                    int[][] subAns = solveSubQueens(board, row + 1, max);

                    if (subAns != null) {
                        if (rowTaken.size() == max) {
                            ans.add(fillUp(subAns));
                        }
                    }
                    unRegister(row, col);
                    board[row - 1][col - 1] = 0;
                }
            }
        }
        return null;
    }

}
