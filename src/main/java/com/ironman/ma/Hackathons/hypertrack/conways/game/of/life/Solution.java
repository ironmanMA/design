package com.ironman.ma.Hackathons.hypertrack.conways.game.of.life;

public class Solution {
    public static final int LIVE_CELL=1;
    public static final int DEAD_CELL=-1;

    public static void chnageState(int[][] grid){
        /*
            eight neighbours, which are the cells that are
             horizontally,
             vertically, or
             diagonally adjacent.
         */

    }

    public static void main(String[] args) {
        /*
        Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
        Any live cell with two or three live neighbours lives on to the next generation.
        Any live cell with more than three live neighbours dies, as if by overpopulation.
        Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
         */
        int[][] grid=new int[][]{
                {1,1,0,},
                {0,1,0,},
                {0,0,1,},
        };

    }
}
