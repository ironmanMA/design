package com.ironman.ma.Hackathons.algorythma.q3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by 127.0.0.1.ma on 14/11/17.
 */
public class Solution {
    static int[] procResults;
    static int[] testResults;
    static HashMap<Integer, ArrayList<Integer>> relations;

    static int calculateProcResults(int i) {
        ArrayList<Integer> relatives = relations.get(i);
        if (testResults[i] == 0)
            return 0;
        else {
            if (relatives == null) {
                procResults[i] = testResults[i];
            } else {
                int result = 1;
                for (Integer integer : relatives) {
                    result = result * calculateProcResults(integer);
                }
                procResults[i] = result;
            }
            return procResults[i];
        }

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numTestCases = input.nextInt();
        int numRelations = input.nextInt();
        testResults = new int[numTestCases];
        procResults = new int[numTestCases];
        for (int i = 0; i < numTestCases; i++) {
            testResults[i] = input.nextInt();
            procResults[i] = -1;
        }
        relations = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 0; i < numRelations; i++) {
            int depdnt = input.nextInt() - 1;
            int depdee = input.nextInt() - 1;
            ArrayList<Integer> relatives = new ArrayList<Integer>();
            if (relations.get(depdnt) != null) {
                relatives = relations.get(depdnt);
            }
            relatives.add(depdee);
            relations.put(depdnt, relatives);
        }

        for (int i = 0; i < numTestCases; i++) {
            if (procResults[i] < 0) {
                calculateProcResults(i);
            }
        }

        for (int i = 0; i < numTestCases; i++) {
            if (procResults[i] == 1)
                System.out.println("YES");
            else
                System.out.println("NO");
//            System.out.println(procResults[i]);
        }

        input.close();
    }
}
