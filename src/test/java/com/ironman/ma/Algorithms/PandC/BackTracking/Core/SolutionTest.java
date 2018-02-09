package com.ironman.ma.Algorithms.PandC.BackTracking.Core;

import junit.framework.TestCase;

import java.util.ArrayList;

public class SolutionTest extends TestCase {

    public void testCombinationSum() {
//        int[] list=new int[]{2 ,3, 6, 7};
//        int[] list=new int[]{8, 5, 12, 10, 20, 18, 13, 18, 16, 3};
        int[] list=new int[]{1, 2, 2, 3};
        ArrayList<Integer> arrList=new ArrayList<Integer>(list.length);
        for(int a:list){
            arrList.add(a);
        }
//        System.out.println(new Solution().combinationSum(arrList,7));
//        System.out.println(new Solution().combinationSum(arrList,28));
        System.out.println(new Solution().subsetsWithDup(arrList));
    }
}