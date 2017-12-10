package com.ironman.ma.DataStructures.Array.noble.integer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by 127.0.0.1.ma on 25/08/17.
 */
public class Solution {
    public int solve(ArrayList<Integer> A) {
        Collections.sort(A);


        int N = A.size();
        int num_great = 0;
        if (A.get(N-1)==0)
            return 1;
        for (int i = N - 2; i > -1; i--) {
            if (A.get(i) < A.get(i + 1)) {
                num_great = N - i - 1;
                if (A.get(i) == N - i - 1)
                    return 1;
            } else {
                if (A.get(i) == num_great)
                    return 1;
            }
        }
        return -1;
    }
}
