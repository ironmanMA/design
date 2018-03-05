package com.ironman.ma.Algorithms.DynamicProgramming.Simple.Array.Dp;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
//        System.out.println(new Solution().numDecodings("10"));
//        System.out.println(new Solution().numDecodings("60"));
//        System.out.println(new Solution().numDecodings("28"));
//        System.out.println(new Solution().numDecodings("05"));
//        System.out.println(new Solution().minDistance("aa","abbbb"));
//        System.out.println(new Solution().isMatch("cb","c*a*b"));
//        System.out.println(new Solution().isMatch("ca","a*"));
//        System.out.println(new Solution().isScramble("great", "rgtae"));
//        System.out.println(new Solution().isScramble("abcde", "caebd"));
//        System.out.println(new Solution().isScramble("abbbcbaaccacaacc", "acaaaccabcabcbcb"));
//        System.out.println(new Solution().solve(11, new int[]{6, 8, 5, 4, 7}));
//        System.out.println(new Solution().solve(12, new int[]{8, 8, 6, 5, 4}));
//        System.out.println(new Solution().solve(9383, new int[]{17786, 11924, 22802, 13344, 10395, 10501, 16658, 16430, 2371, 15036, 18699, 20068, 22772, 13935, 5549, 8435, 14181, 5745, 5220, 20377, 2576, 6438, 15791, 21539, 22871, 15132, 24076, 3144, 13938, 4811, 9031, 23067, 8078, 23176, 11402, 18465, 20, 3051, 1238, 2382, 9430, 19928, 13793, 23546, 207, 19333, 23324, 14379, 16422, 3535, 1100, 18989, 9965, 16882, 6871, 24179, 7005, 22290, 2314, 20934, 2093, 11336, 10345, 1514, 855, 21738, 11322, 866, 16133, 3904, 19591, 554, 23823, 8376, 15443, 15373, 19052, 13759, 21096, 1817, 17285, 22187, 20797, 18593, 5412, 2660}));
//        System.out.println(new Solution().solve(new int[]{2, 6, 8, 10, 14, 20}));
//        System.out.println(new Solution().solve(new int[]{4, 6, 8, 10, 13, 12, 16, 14}));
//        System.out.println(new Solution().solve(new int[]{882400, 892183, 901966, 202076968, 911749, 429933822, 921532, 931315, 23492165, 941098, 950881, 960664, 502522116, 970447, 980230, 990013, 946937128, 114014855, 796318860, 793721276, 999796, 946566879, 198029738, 507249631, 665354617, 1009579, 361922663, 228552800, 145066536, 274159860, 889064344, 450789662, 923350794, 459149021, 252169366, 997162555, 355515573, 89094273, 410179851, 145599399, 970229635, 555922958, 914041761, 540998258, 843528724, 27371034, 890841739, 206750545, 749976522, 531094042, 624770557, 333582624, 174151500, 710657260, 588445359, 854719322, 435087620, 662225230, 71189599, 986583203, 966204833, 32442468, 773831756, 441405759, 560906389, 197308470, 336555963, 236403096, 362977944, 776116784, 497078902, 953179324, 12279843, 710031777, 887107912, 594611932, 903524342, 44241980, 16445058}));
//        System.out.println(new Solution().solve(new int[]{1, 1, 1, 1, 1, 1, 1}));
//        System.out.println(new Solution().solve(2, 9));
//        901686122
//        948527153
        System.out.println(new Solution().solve(20, 120));
        //54
        System.out.println(new Solution().solve(3, 10));
        System.out.println(new Solution().solve(4, 12));
        System.out.println(new Solution().solve(75, 22));
//        478432066

    }
}