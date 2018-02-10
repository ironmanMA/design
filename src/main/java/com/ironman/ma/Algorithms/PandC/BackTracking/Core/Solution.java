package com.ironman.ma.Algorithms.PandC.BackTracking.Core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Solution {
    public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> A, int B) {
        HashSet<Integer> uniq = new HashSet<Integer>(A);
        A = new ArrayList<Integer>(uniq);
        Collections.sort(A);
        return combinationSubSum(A, 0, B);
    }

    public ArrayList<ArrayList<Integer>> combinationSubSum(ArrayList<Integer> list, int start,
                                                           int targetSum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (list.size() == 0 || targetSum < list.get(start)) {
            return res;
        }

        for (int i = start; i < list.size(); i++) {
            int num = list.get(i);
            if (targetSum == num) {
                ArrayList<Integer> subList = new ArrayList<Integer>();
                subList.add(0, list.get(i));
                res.add(subList);
            } else if (targetSum > num) {
                ArrayList<ArrayList<Integer>> subResNC = combinationSubSum(list, i, targetSum - num);
                for (ArrayList<Integer> subList : subResNC) {
                    subList.add(0, list.get(i));
                    res.add(subList);
                }
            }
        }
        return res;
    }

    public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> a) {

        Collections.sort(a);
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> temp = new ArrayList<Integer>();

        res.add(temp);
        createSubsets(res, temp, a, 0);

        return res;

    }

    public void createSubsets(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> temp, ArrayList<Integer> a, int index) {
        for (int i = index; i < a.size(); i++) {
            if (i > index && a.get(i).equals(a.get(i - 1))) {
                continue;
            }
            temp.add(a.get(i));
            res.add(new ArrayList<Integer>(temp));
            System.out.println(temp);
            createSubsets(res, temp, a, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    public ArrayList<String> generateParenthesis(int A) {
        if (A < 1) {
            return new ArrayList<String>();
        }
        ArrayList<String> coll = generateSubParenthesis(A);
        Collections.sort(coll);
        return coll;
    }

    private ArrayList<String> generateSubParenthesis(int count) {
        ArrayList<String> res = new ArrayList<String>();
        if (count == 1) {
            res.add("()");
            return res;
        }
        ArrayList<String> subRes = generateSubParenthesis(count - 1);
        for (String k : subRes) {
            res.add("(" + k + ")");
        }
        for (String k : subRes) {
            String p = k + "()";
            res.add(p);
            if (!p.equals("()" + k)) {
                res.add("()" + k);
            }
        }
        return res;
    }

    public boolean is1BitDiff(int a, int b) {
        int k = a ^ b;
        return ((k) & (k - 1)) == 0;
    }

    private int flipKthBit(long n, int k) {
        return ((int) n ^ (1 << k));
    }

    public ArrayList<Integer> grayCode2(int a) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        res.add(0);
        res.add(1);
        HashSet<Integer> visited = new HashSet<Integer>(res);

        long k = 1;
        while (k < Math.pow(2, a)) {
            for (int i = 0; i < a; i++) {
                int newK = flipKthBit(k, i);
                if (!visited.contains(newK)) {
                    res.add(newK);
                    visited.add(newK);
                    k = newK;
                    break;
                }
            }
            if (res.size() == Math.pow(2, a)) {
                break;
            }
        }
        return res;
    }

    public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> res = solve(n);
        return res;
    }

    private ArrayList<Integer> solve(int n) {
        if (n == 0) {
            ArrayList<Integer> res = new ArrayList<Integer>();
            res.add(0);
            return res;
        }
        ArrayList<Integer> prev = solve(n - 1);

        for (int i = prev.size() - 1; i >= 0; i--) {
            int num = prev.get(i);
            num |= (1 << (n - 1));
            prev.add(num);
        }
        return prev;
    }

    HashMap<Integer, Long> fact = new HashMap<Integer, Long>();

    public String getPermutation(int A, int B) {
        ArrayList<String> list = new ArrayList<String>();
        long f = 1;
        fact.put(0, (long) 1);
        for (int i = 1; i <= A; i++) {
            list.add(i + "");
            fact.put(i, i * fact.get(i - 1));
        }
        return getSubPermutation(list, (long) B);
    }

    public long nFact(int n) {
        return fact.get(n);
    }

    public String getSubPermutation(ArrayList<String> list, long remRank) {
        StringBuilder str = new StringBuilder();
        if (remRank == 1) {
            for (String s : list) {
                str.append(s);
            }
            return str.toString();
        }
        long prev = 0;
        for (int i = 0; i < list.size(); i++) {
            boolean c1 = false;
            boolean c1A = (prev * i < remRank);
            if (prev == 0 && remRank > 0) {
                c1 = true;
            } else if (prev != 0 && i * 1.0 < (remRank / (prev * 1.0))) {
                c1 = true;
            }
            boolean c2 = ((remRank /(1.0 * nFact(list.size() - 1))) <= (i + 1) * 1.0);
            boolean c2A = (remRank < (i + 1) * nFact(list.size() - 1));
//            System.out.println(c1A == c1);
//            System.out.println(c2A == c2);
            if (c1 && c2) {
                str.append(list.get(i));
                long minRank = (long) i * (nFact(list.size() - 1));
                list.remove(i);
                String rest = getSubPermutation(list, remRank - minRank);
                str.append(rest);
                break;
            }
            prev = nFact(list.size() - 1);
        }
        return str.toString();
    }

}
