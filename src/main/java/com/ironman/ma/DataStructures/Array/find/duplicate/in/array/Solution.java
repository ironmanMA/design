package com.ironman.ma.DataStructures.Array.find.duplicate.in.array;

import java.util.HashSet;
import java.util.List;

/**
 * Created by 127.0.0.1.ma on 25/08/17.
 */
public class Solution {

    public int repeatedNumber(final List<Integer> a) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i=0;i<a.size();i++){
            if (set.contains(a.get(i))){
                return a.get(i);
            }else {
                set.add(a.get(i));
            }
        }
        return -1;
    }
}
