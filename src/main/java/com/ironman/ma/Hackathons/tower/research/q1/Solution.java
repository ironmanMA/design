package com.ironman.ma.Hackathons.tower.research.q1;

import java.util.Scanner;

/**
 * Created by mohammad arafath on 19/10/17.
 * <p>
 * On a chessboard Figure out minimum number of steps Knight can take to reach a target.
 */
public class Solution {
    static Double returnMinDist(int startX, int startY, int endX, int endY) {
        endX = endX - startX;
        endY = endY - startY;
        endX = Math.abs(endX);
        endY = Math.abs(endY);

        if (endX < endY) {
            int t = endX;
            endX = endY;
            endY = t;
        }
        if (endX == 1 && endY == 0) {
            return 3.0;
        }
        if (endX == 2 && endY == 2) {
            return 4.0;
        }
        int delta = endX - endY;
        if (endY > delta) {
            Double magic = (delta - endY) / (double) 3;
            return (delta - 2 * Math.floor(magic));
        } else {
            Double magic = (delta - endY) / (double) 4;
            return (delta - 2 * Math.floor(magic));
        }
    }


    public static void main(String[] args) {
//        System.out.println(Math.floor(-0.3));
        Scanner input = new Scanner(System.in);
//        int cases = input.nextInt();
//        for (int i = 0; i < cases; i++) {
        int startX = input.nextInt();
        int startY = input.nextInt();
        int endX = input.nextInt();
        int endY = input.nextInt();
//            System.out.println(startX+", "+startY+", "+endX+", "+endY);
        System.out.println(returnMinDist(startX, startY, endX, endY).intValue());
//        }
        input.close();
    }
}
