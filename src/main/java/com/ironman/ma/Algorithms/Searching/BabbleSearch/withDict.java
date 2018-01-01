package com.ironman.ma.Algorithms.Searching.BabbleSearch;

import com.sun.org.apache.xml.internal.utils.Trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class withDict {


    static int rowLimit = 0;
    static int colLimit = 0;
    static int[][] visited;

    static ArrayList<String> searchBgWord(char[][] board, Trie dict, BgWord bgw) {
        ArrayList<String> results = new ArrayList<String>();

        ArrayList<BgWord> possibs = new ArrayList<BgWord>();


        if (visited[bgw.row][bgw.col] == 1) {
            return results;
        }

        if (dict.searchWord(bgw.word)) {
            results.add(bgw.word);
        }


        visited[bgw.row][bgw.col] = 1;

        if (bgw.col - 1 >= 0 && dict.searchPrefix(bgw.word + board[bgw.row][bgw.col - 1])) {
            possibs.add(new BgWord(bgw.row, bgw.col - 1, bgw.word + board[bgw.row][bgw.col - 1]));
        }
        if (bgw.col + 1 < colLimit && dict.searchPrefix(bgw.word + board[bgw.row][bgw.col + 1])) {
            possibs.add(new BgWord(bgw.row, bgw.col + 1, bgw.word + board[bgw.row][bgw.col + 1]));
        }
        if (bgw.row - 1 >= 0 && dict.searchPrefix(bgw.word + board[bgw.row - 1][bgw.col])) {
            possibs.add(new BgWord(bgw.row - 1, bgw.col, bgw.word + board[bgw.row - 1][bgw.col]));
        }
        if (bgw.row + 1 < rowLimit && dict.searchPrefix(bgw.word + board[bgw.row + 1][bgw.col])) {
            possibs.add(new BgWord(bgw.row + 1, bgw.col, bgw.word + board[bgw.row + 1][bgw.col]));
        }


        HashSet<String> set = new HashSet<String>();
        for (BgWord sbgw : possibs) {
            for (String wFound : searchBgWord(board, sbgw)) {
                if (!set.contains(wFound)) {
                    results.add(wFound);
                }
            }
        }

        return results;
    }

    public ArrayList<String> boggleSearchWithDict(char[][] board, Trie dict) {

        if (board == null || dict == null || board.length == 0 || board[0].length == 0) {
            return null;
        }

        rowLimit = board.length;
        colLimit = board[0].length;
        visited = new int[rowLimit][colLimit];

        ArrayList<String> result = new ArrayList<String>();
        HashSet<String> set = new HashSet<String>();
        for (int i = 0; i < rowLimit; i++) {
            for (int j = 0; j < rowLimit; j++) {
                if (dict.searchPrefix("" + board[i][j])) {
                    visited = new int[rowLimit][colLimit];
                    for (String s : searchBgWord(board, dict, new BgWord(i, j, "" + board[i][j]))) {
                        if (!set.contains(s)) {
                            result.add(s);
                        }
                    }
                }

            }
        }

        Collections.sort(result);

        return result;

    }

    static class BgWord {
        int row = 0;
        int col = 0;
        String word = null;
        boolean isPres = false;

        public BgWord(int r, int c, String w) {
            this.row = r;
            this.col = c;
            this.word = w;
        }

        public void setPres(boolean p) {
            this.isPres = p;
        }

        public boolean getPres(boolean p) {
            return isPres;
        }

    }
}
