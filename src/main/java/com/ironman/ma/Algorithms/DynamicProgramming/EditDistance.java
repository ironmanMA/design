package com.ironman.ma.Algorithms.DynamicProgramming;

import java.util.HashMap;

/**
 * Created by 127.0.0.1.ma on 20/08/17.
 */
public class EditDistance {
    // java.util.* has been imported for this problem.
// You don't need any other imports.

    static HashMap<String, Integer> memo = new HashMap<String, Integer>();

    static int editSubDistance(String a, String b, int aPos, int bPos) {
        System.out.println("a:" + a + ", b:" + b + ", ap:" + aPos + ", bp:" + bPos);
        if (memo.containsKey(aPos + "|" + bPos)) {
            return memo.get(aPos + "|" + bPos);
        }

        if (aPos >= a.length() && bPos >= b.length()) {
            return 0;
        }
        if (aPos >= a.length() && bPos < b.length()) {
            return b.length() - bPos;
        }
        if (bPos >= b.length() && aPos < a.length()) {
            return a.length() - aPos;
        }

        if (a.charAt(aPos) == b.charAt(bPos)) {
            int count = editSubDistance(a, b, aPos + 1, bPos + 1);
            memo.put(aPos + "|" + bPos, count);
            return count;
        } else {
            int c1 = editSubDistance(a, b, aPos + 1, bPos + 1);
            memo.put((aPos + 1) + "|" + (bPos + 1), c1);
            int c2 = editSubDistance(a, b, aPos + 1, bPos);
            memo.put((aPos + 1) + "|" + bPos, c2);
            int c3 = editSubDistance(a, b, aPos, bPos + 1);
            memo.put(aPos + "|" + (bPos + 1), c3);
            return 1 + Math.min(c1, Math.min(c2, c3));
        }

    }

    public static int editDistance(String a, String b) {
        memo = new HashMap<String, Integer>();
        if (b == null || b.isEmpty()) {
            return a.length();
        }

        if (a == null || a.isEmpty()) {
            return b.length();
        }

        return editSubDistance(a, b, 0, 0);


    }
}
