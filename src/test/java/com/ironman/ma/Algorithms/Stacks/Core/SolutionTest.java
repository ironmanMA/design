package com.ironman.ma.Algorithms.Stacks.Core;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionTest extends TestCase {

    public void testSimplifyPath() {
//        System.out.println(new Solution().simplifyPath("/fic/././iak/…/…/hgy/blg/…/vzt/…/tod/.././/./bsc/./krk/…/lnb/zhj/./…/lqm/tbo/ujr/./…"));
//        System.out.println(new Solution().simplifyPath("/var///log"));
//        System.out.println(new Solution().simplifyPath("\\fic\\iak"));
//        System.out.println(new Solution().braces("((a+b)+(c+d))"));
//        System.out.println(new Solution().braces("((a + b))"));
//        System.out.println(new Solution().braces("(((a+b)+(c+d)+(e+f)))"));
//        System.out.println(new Solution().braces("((a+b)+(c+d)+(e+f))+g"));
//        int[] k=new int[]{2,1,5,6,2,3};
//        int[] k=new int[]{2};

        int[] k=new int[]{1,3,-1,-3,5,3,6,7};
        ArrayList<Integer> arr=new ArrayList<Integer>(k.length);
        for(int i:k){
            arr.add(i);
        }
//        System.out.println(new Solution().largestRectangleArea(arr));
        System.out.println(new Solution().slidingMaximum(arr,2));
    }
}