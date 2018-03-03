package com.ironman.ma.Algorithms.DynamicProgramming.Simple.Array.Dp;

import junit.framework.TestCase;

import java.util.ArrayList;

public class SolutionTest extends TestCase {

    public void testLongestSubsequenceLength() {
//        int[] arr = new int[]{1};
//        ArrayList<Integer> arrL = new ArrayList<Integer>();
//        for (int a : arr) {
//            arrL.add(a);
//        }
//        System.out.println(new Solution().longestSubsequenceLength(arrL));
        int[][] matrix = {
                {0, 1, 0, 1, 0},
                {0, 1, 0, 1, 1},
                {1, 1, 0, 1, 0}
        };
//        System.out.println(new Solution().solve(matrix));
//        System.out.println(new Solution().numDecodings("261105"));
//        System.out.println(new Solution().numDecodings("2611055"));
//        System.out.println(new Solution().numDecodings("26110559"));
//        System.out.println(new Solution().numDecodings("261105597"));
//        System.out.println(new Solution().numDecodings("2611055971756562"));
//        System.out.println(new Solution().numDecodings("32925665678138257423442343752148360796465852883409126159293254159974370974059818198867156827877059067081419274873853679038"));
        System.out.println(new Solution().numDecodings("10"));
        System.out.println(new Solution().numDecodings("60"));
        System.out.println(new Solution().numDecodings("28"));
        System.out.println(new Solution().numDecodings("05"));
    }
}