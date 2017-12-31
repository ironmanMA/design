package com.ironman.ma.Algorithms.Searching;

import java.util.ArrayList;

public class BabbleSearch {
    static int rowLimit = 0;
    static int colLimit = 0;
    static int[][] visited;

    static boolean boggleSubSearch(char[][] board, Possib possiblity) {
        //check all neighbours
        if (possiblity.subWord.isEmpty()) {
            return true;
        }

        if (possiblity.subWord.length() == 1 &&
                possiblity.subWord.charAt(0) == board[possiblity.row][possiblity.col]) {
            return true;
        }

        if (visited[possiblity.row][possiblity.col] == 1) {
            return false;
        }

        visited[possiblity.row][possiblity.col] = 1;

        ArrayList<Possib> newPossibilites = new ArrayList<Possib>();

        if ((possiblity.col - 1) >= 0 && board[possiblity.row][possiblity.col] == possiblity.subWord.charAt(0)) {
            newPossibilites.add(new Possib(possiblity.row, possiblity.col - 1, possiblity.subWord.substring(1)));
        }
        if ((possiblity.col + 1) < colLimit && board[possiblity.row][possiblity.col] == possiblity.subWord.charAt(0)) {
            newPossibilites.add(new Possib(possiblity.row, possiblity.col + 1, possiblity.subWord.substring(1)));
        }
        if ((possiblity.row + 1) < rowLimit && board[possiblity.row][possiblity.col] == possiblity.subWord.charAt(0)) {
            newPossibilites.add(new Possib(possiblity.row + 1, possiblity.col, possiblity.subWord.substring(1)));
        }
        if ((possiblity.row - 1) >= 0 && board[possiblity.row][possiblity.col] == possiblity.subWord.charAt(0)) {
            newPossibilites.add(new Possib(possiblity.row - 1, possiblity.col, possiblity.subWord.substring(1)));
        }

        for (Possib p : newPossibilites) {
            if (boggleSubSearch(board, p)) {
                return true;
            }
        }

        return false;

    }

    public static boolean boggleSearch(char[][] board, String word) {

        if (board == null || board.length == 0) {
            return false;
        }

        rowLimit = board.length;
        colLimit = board[0].length;
        visited = new int[rowLimit][colLimit];
        for (int i = 0; i < rowLimit; i++) {
            for (int j = 0; j < rowLimit; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (boggleSubSearch(board,
                            new Possib(i, j, word)
                    )) {
                        return true;
                    }
                }
            }
        }

        return false;

    }

    static class Possib {
        int row;
        int col;
        String subWord = null;

        public Possib(int row, int col, String subWord) {
            this.row = row;
            this.col = col;
            this.subWord = subWord;
        }
    }
}
