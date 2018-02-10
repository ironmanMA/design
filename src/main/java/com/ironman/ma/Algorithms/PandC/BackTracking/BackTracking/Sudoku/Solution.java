package com.ironman.ma.Algorithms.PandC.BackTracking.BackTracking.Sudoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Solution {
    public void solveSudoku(ArrayList<ArrayList<Character>> a) {
        char[][] grid = new char[a.size()][a.size()];
        for (int row = 0; row < a.size(); row++) {
            for (int col = 0; col < a.size(); col++) {
                grid[row][col] = a.get(row).get(col);

            }
        }
        grid = solveSudoku(grid);
        for (int row = 0; row < a.size(); row++) {
            for (int col = 0; col < a.size(); col++) {
                a.get(row).set(col, grid[row][col]);
            }
        }

    }

    public char[][] solveSudoku(char[][] board) {
        solve(board);
        return board;
    }

    public ArrayList<ArrayList<Character>> solveSudokuL(char[][] board) {
        solve(board);
        ArrayList<ArrayList<Character>> a = new ArrayList<ArrayList<Character>>();
        for (int row = 0; row < board.length; row++) {
            a.add(new ArrayList<Character>());
            for (int col = 0; col < board.length; col++) {
                Character c=board[row][col];
                a.get(row).add(col, c);
            }
        }
        return a;
    }

    public boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.')
                    continue;

                for (int k = 1; k <= 9; k++) {
                    board[i][j] = (char) (k + '0');
//                    board[i][j] = (char) (k);
                    if (isValid(board, i, j) && solve(board))
                        return true;
                    board[i][j] = '.';
                }

                return false;
            }
        }

        return true; // does not matter
    }

    public boolean solve(ArrayList<ArrayList<Character>> board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!board.get(i).get(j).equals('.'))
                    continue;

                for (int k = 1; k <= 9; k++) {
                    board.get(i).set(j, (char) k);
//                    board[i][j] = (char) (k);
                    if (isValid(board, i, j) && solve(board))
                        return true;
                    board.get(i).set(j, '.');
                }

                return false;
            }
        }

        return true; // does not matter
    }

    public boolean isValid(ArrayList<ArrayList<Character>> board, int i, int j) {
        HashSet<Character> set = new HashSet<Character>();

        for (int k = 0; k < 9; k++) {
            if (set.contains(board.get(i).get(j)))
                return false;

            if (!board.get(i).get(j).equals('.')) {
                set.add(board.get(i).get(j));
            }
        }

        set.clear();

        for (int k = 0; k < 9; k++) {
            if (set.contains(board.get(i).get(j)))
                return false;

            if (!board.get(i).get(j).equals('.')) {
                set.add(board.get(i).get(j));
            }
        }

        set.clear();

        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++) {
                int x = i / 3 * 3 + m;
                int y = j / 3 * 3 + n;
                if (set.contains(board.get(i).get(j)))
                    return false;

                if (!board.get(i).get(j).equals('.')) {
                    set.add(board.get(i).get(j));
                }
            }
        }

        return true;
    }

    public boolean isValid(char[][] board, int i, int j) {
        HashSet<Character> set = new HashSet<Character>();

        for (int k = 0; k < 9; k++) {
            if (set.contains(board[i][k]))
                return false;

            if (board[i][k] != '.') {
                set.add(board[i][k]);
            }
        }

        set.clear();

        for (int k = 0; k < 9; k++) {
            if (set.contains(board[k][j]))
                return false;

            if (board[k][j] != '.') {
                set.add(board[k][j]);
            }
        }

        set.clear();

        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++) {
                int x = i / 3 * 3 + m;
                int y = j / 3 * 3 + n;
                if (set.contains(board[x][y]))
                    return false;

                if (board[x][y] != '.') {
                    set.add(board[x][y]);
                }
            }
        }

        return true;
    }

    HashMap<Integer, HashSet<Integer>> rowMap = new HashMap<Integer, HashSet<Integer>>();
    HashMap<Integer, HashSet<Integer>> colMap = new HashMap<Integer, HashSet<Integer>>();
    HashMap<String, HashSet<Integer>> blockMap = new HashMap<String, HashSet<Integer>>();
    int[][] board = null;
    int[][] origBoard = null;
    int sudokusize = 0;

    public ArrayList<ArrayList<Character>> solveSudoku(int[][] boar) {
        board = boar;
        origBoard = new int[board.length][board.length];
        ArrayList<ArrayList<Character>> res = new ArrayList<ArrayList<Character>>();
        sudokusize = (int) Math.sqrt(board.length);
        rowMap = new HashMap<Integer, HashSet<Integer>>();
        colMap = new HashMap<Integer, HashSet<Integer>>();
        blockMap = new HashMap<String, HashSet<Integer>>();
        HashSet<Integer> full = new HashSet<Integer>();
        for (int i = 1; i <= board.length; i++) {
            full.add(i);
        }
        for (int row = 1; row <= board.length; row++) {
            rowMap.put(row, new HashSet<Integer>(full));
            for (int col = 1; col <= board.length; col++) {
                origBoard[row - 1][col - 1] = board[row - 1][col - 1];
                if (!colMap.containsKey(col)) {
                    colMap.put(col, new HashSet<Integer>(full));
                }
                String block = ((row - 1) / sudokusize) + "_" + ((col - 1) / sudokusize);
                if (!blockMap.containsKey(block)) {
                    blockMap.put(block, new HashSet<Integer>(full));
                }
                rowMap.get(row).remove(board[row - 1][col - 1]);
                colMap.get(col).remove(board[row - 1][col - 1]);
                blockMap.get(block).remove(board[row - 1][col - 1]);
            }
        }

//        for (Map.Entry<Integer, HashSet<Integer>> entry : rowMap.entrySet()) {
//            if (entry.getValue().size() == 0) {
//                rowMap.remove(entry.getKey());
//            }
//        }

        board = solveSubSudoku(board, 1, 1, board.length);
        for (int row = 1; row <= board.length; row++) {
            res.add(new ArrayList<Character>());
            for (int col = 1; col <= board.length; col++) {
                System.out.print(board[row - 1][col - 1]);
                res.get(row - 1).add((char) board[row - 1][col - 1]);

            }
            System.out.print("\n");
        }


        return res;
    }

    private void register(int row, int col) {
        rowMap.get(row).remove(board[row - 1][col - 1]);
        if (rowMap.get(row).size() == 0) {
            rowMap.remove(row);
        }
        colMap.get(col).remove(board[row - 1][col - 1]);
        if (colMap.get(col).size() == 0) {
            colMap.remove(col);
        }
        String block = ((row - 1) / sudokusize) + "_" + ((col - 1) / sudokusize);
        blockMap.get(block).remove(board[row - 1][col - 1]);
        if (blockMap.get(block).size() == 0) {
            blockMap.remove(block);
        }
    }

    private void unRegister(int row, int col) {

        if (!rowMap.containsKey(row)) {
            rowMap.put(row, new HashSet<Integer>());
        }
        rowMap.get(row).add(board[row - 1][col - 1]);

        if (!colMap.containsKey(col)) {
            colMap.put(col, new HashSet<Integer>());
        }
        colMap.get(col).add(board[row - 1][col - 1]);

        String block = ((row - 1) / sudokusize) + "_" + ((col - 1) / sudokusize);
        if (!blockMap.containsKey(block)) {
            blockMap.put(block, new HashSet<Integer>());
        }
        blockMap.get(block).add(board[row - 1][col - 1]);
    }

    private boolean isFull() {
        return (rowMap.size() == 0) && (colMap.size() == 0) && (blockMap.size() == 0);
    }

    private HashSet<Integer> getCommon(HashSet<Integer> r, HashSet<Integer> c, HashSet<Integer> b) {
        HashSet<Integer> ans = new HashSet<Integer>();
        if (r != null && c != null && b != null) {
            for (int num : r) {
                if (c.contains(num) && b.contains(num)) {
                    ans.add(num);
                }
            }
        }
        return ans;
    }

    private int[][] solveSubSudoku(int[][] board, int rowMin, int colMin, int size) {
        if (isFull()) {
            return board;
        }
        for (int col = colMin; col <= size; col++) {
            if (origBoard[rowMin - 1][col - 1] == 0) {

                String block = ((rowMin - 1) / sudokusize) + "_" + ((col - 1) / sudokusize);
                HashSet<Integer> rowSet = getCommon(rowMap.get(rowMin), colMap.get(col), blockMap.get(block));

                for (int k : rowSet) {
                    board[rowMin - 1][col - 1] = k;
                    register(rowMin, col);
                    solveSubSudoku(board, rowMin, col + 1, size);
                    if (isFull()) {
                        return board;
                    }
                    unRegister(rowMin, col);
                }
            }
        }
        for (int row = rowMin + 1; row <= size; row++) {
            for (int col = 1; col <= size; col++) {
                if (origBoard[row - 1][col - 1] == 0) {
                    String block = ((row - 1) / sudokusize) + "_" + ((col - 1) / sudokusize);
                    HashSet<Integer> rowSet = getCommon(rowMap.get(row), colMap.get(col), blockMap.get(block));
                    for (int k : rowSet) {
                        board[row - 1][col - 1] = k;
                        register(row, col);
                        solveSubSudoku(board, row, col + 1, size);
                        if (isFull()) {
                            return board;
                        }
                        unRegister(row, col);
                    }
                }

            }
        }

        return board;
    }
}
