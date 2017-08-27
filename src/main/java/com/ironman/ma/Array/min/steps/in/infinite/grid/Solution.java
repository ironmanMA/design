package com.ironman.ma.Array.min.steps.in.infinite.grid;

import java.util.ArrayList;

/**
 * Created by 127.0.0.1.ma on 25/08/17.
 */
public class Solution {
    public int coverPoints(ArrayList<Integer> X, ArrayList<Integer> Y) {
        int steps=0;
        int cases = X.size();
        int intX=X.get(0),intY=Y.get(0);
        for (int i=1;i<cases;i++){
            int xDiff=Math.abs(X.get(i)-intX),yDiff=Math.abs(Y.get(i)-intY);
            steps += Math.max(xDiff,yDiff);
            intX=X.get(i);
            intY=Y.get(i);
        }
        return steps;
    }
}
