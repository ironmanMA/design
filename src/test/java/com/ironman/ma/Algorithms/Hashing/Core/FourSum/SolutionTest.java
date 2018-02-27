package com.ironman.ma.Algorithms.Hashing.Core.FourSum;

import junit.framework.TestCase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.math.BigDecimal.ROUND_UNNECESSARY;

public class SolutionTest extends TestCase {

    public void testFourSum() {
        int[] arr = new int[]{86, 92, 49, 21, 62, 27, 90, 59, 23};
        ArrayList<Integer> ints = new ArrayList<Integer>();
        int[] arr2 = new int[]{2, 0, 0, 2, 0, 2, 1, 3, 0};
        ArrayList<Integer> ints2 = new ArrayList<Integer>();
        for (int ae : arr) {
            ints.add(ae);
        }
        for (int ae : arr2) {
            ints2.add(ae);
        }
//        System.out.println(new Solution().order(ints, ints2));
//        System.out.println(new Solution.Solution2().order(ints, ints2));
        new Solution().mainly();


    }

//49381776
//    49381776


    /**
     * Definition for binary tree
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }

    class TreeNodeLev extends TreeNode {
        int vLev;

        public TreeNodeLev(int lev, TreeNode x) {
            super(x.val);
            this.val = x.val;
            this.left = x.left;
            this.right = x.right;
            this.vLev = lev;
        }
    }
}