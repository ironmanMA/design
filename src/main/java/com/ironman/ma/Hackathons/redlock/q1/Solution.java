package com.ironman.ma.Hackathons.redlock.q1;

import java.util.*;

/**
 * Created by 127.0.0.1.ma on 07/12/17.
 */
public class Solution {
    static HashMap<Integer, List<Integer>> memberHash = new HashMap<Integer, List<Integer>>();
    static HashSet<String> solutionHash = new HashSet<String>();
    static HashSet<String> solutionHash2 = new HashSet<String>();


    static int numberOfPairs(int[] members, long a) {
        for (int i = 0; i < members.length; i++) {
            int num = members[i];
            List<Integer> numList = new ArrayList<Integer>();
            if (memberHash.get(num) != null) {
                numList = memberHash.get(num);
            }
            numList.add(i);
            memberHash.put(num, numList);
            members[i] = num;
        }

        for (int i = 0; i < members.length; i++) {
            if (memberHash.get((int) (a - members[i])) != null) {
                for (int index : memberHash.get((int) (a - members[i]))) {
                    if (index != i) {
                        String key1 = index + ";" + i;
                        String key2 = i + ";" + index;

                        String key3 = members[index] + ";" + members[i];
                        String key4 = members[i] + ";" + members[index];

                        if (!solutionHash.contains(key1) && !solutionHash.contains(key2)) {
                            if (!solutionHash2.contains(key3) && !solutionHash2.contains(key4)) {
                                solutionHash.add(key1);
                                solutionHash2.add(key3);
                            }
                        }
                    }
                }
            }
        }
        return solutionHash.size();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numCases = input.nextInt();
        int[] members = new int[numCases];
        for (int i = 0; i < numCases; i++) {
            int num = input.nextInt();
//            List<Integer> numList = new ArrayList<Integer>();
//            if (memberHash.get(num) != null) {
//                numList = memberHash.get(num);
//            }
//            numList.add(i);
//            memberHash.put(num, numList);
            members[i] = num;
        }
        long k = Long.parseLong(input.next().trim());
        System.out.println(numberOfPairs(members, k));
        input.close();
    }
}
