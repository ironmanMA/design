package com.ironman.ma.Algorithms.PandC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class StringPermutations {
    static HashMap<String, ArrayList<String>> memo = new HashMap<String, ArrayList<String>>();

    public static ArrayList<String> getCombPerms(String s) {
        ArrayList<String> res = new ArrayList<String>();
        if (s == null) {
            return null;
        }
        if (s.isEmpty()) {
            return res;
        }

        if (s.length() == 1) {
            res.add(s);
            return res;
        }

        HashSet<String> resHash = new HashSet<String>();
        getPermutations(s);
        for (String key : memo.keySet()) {
            ArrayList<String> subRes = memo.get(key);
            for (String resStr : subRes) {
                if (!resHash.contains(resStr)) {
                    resHash.add(resStr);
                    res.add(resStr);
                }
            }
        }

//        for(int i=0;i<s.length();i++){
//            res.add(""+s.charAt(i));
//        }

        return res;
    }

    public static ArrayList<String> getPermutations(String s) {
        ArrayList<String> res = new ArrayList<String>();

        if (s == null || s.isEmpty()) {
            return res;
        }
        if (s.length() == 1) {
            res.add(s);
            memo.put(s, res);
            return res;
        }
//        if(s.length()==2){
//            res.add(s);
//            res.add(s.charAt(1)+""+s.charAt(0));
//            memo.put(s,res);
//            return res;
//        }

        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        HashSet<String> visited = new HashSet<String>();
        for (int i = 0; i < s.length(); i++) {
            StringBuilder subString = new StringBuilder("");
            int indexOfChar = s.indexOf(s.charAt(i));
            subString.append(s.substring(0, indexOfChar));
            subString.append(s.substring(indexOfChar + 1));

            System.out.println(s + ": String w/o char:" + s.charAt(i) + ", is:" + subString.toString());

            for (String sub : getPermutations(subString.toString())) {
                if (!visited.contains(sub + s.charAt(i))) {
                    visited.add(sub + s.charAt(i));
                    res.add(sub + s.charAt(i));
                }
                if (!visited.contains(s.charAt(i) + sub)) {
                    visited.add(s.charAt(i) + sub);
                    res.add(s.charAt(i) + sub);
                }
            }
            memo.put(s, res);
        }


        return res;
    }
}
