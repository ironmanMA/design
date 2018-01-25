package com.ironman.ma.Algorithms.Strings.Min.Length.For.Palidrome;

import junit.framework.TestCase;

public class SolutionTest extends TestCase {

    public void testSolve() {
        System.out.println(new Solution().solve("aaaaa"));
        System.out.println(new Solution().solve("hqghumeaylnlfdxfi"));
        System.out.println(new Solution().solve("AACECAAAA"));
        System.out.println(new Solution().solve("ABC"));
        System.out.println(new Solution().solve("mmtatbdzqsoemuvnpppsu"));
    }
}