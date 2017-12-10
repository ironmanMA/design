package com.ironman.ma.DataStructures.Array.wave.array;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by 127.0.0.1.ma on 25/08/17.
 */
public class Solution {
    public ArrayList<Integer> wave(ArrayList<Integer> A) {
        Collections.sort(A);
        ArrayList<Integer> waveArr = new ArrayList<Integer>();
        int t = 0;
        if (A.size() == 1)
            return A;
        if (A.get(t).equals(A.get(t + 1))) {
            waveArr.add(A.get(t));
            for (t = 1; t < A.size(); t++) {
                if (A.get(t).equals(A.get(t - 1))) {
                    waveArr.add(A.get(t));
                } else {
                    break;
                }
            }
        }

        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(A.get(t));
        if (t < A.size()) {
            for (int i = t + 1; i < A.size(); i++) {
                if (A.get(i) > A.get(i - 1)) {
                    if (temp.size() > 0) {
                        waveArr.add(A.get(i));
                        for (int j = 0; j < temp.size(); j++) {
                            waveArr.add(temp.get(j));
                        }
                        temp = new ArrayList<Integer>();
                    } else {
                        temp.add(A.get(i));
                    }
                } else {
                    temp.add(A.get(i));
                }
            }
        }
        waveArr.addAll(temp);
        return waveArr;
    }
}
