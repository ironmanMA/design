package com.ironman.ma.Hackathons.algorythma.q1;

import java.util.Scanner;

/**
 * Created by 127.0.0.1.ma on 14/11/17.
 */
public class Solution {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numCases = input.nextInt();
        int[] numArray = new int[numCases];
        for (int i = 0; i < numCases; i++) {
            numArray[i] = input.nextInt();
        }

        int numQ = input.nextInt();
        for (int i = 0; i < numQ; i++) {
//            String s=input.nextLine();
//            System.out.println("LINE: "+s);
//            String[] sArr=s.split(" ");
            int start = input.nextInt();
            int end = input.nextInt();
            int find = input.nextInt();
//            System.out.println(start+", "+end+", "+find);
//            int start=Integer.valueOf(sArr[0]);
//            int end=Integer.valueOf(sArr[1]);
//            int find=Integer.valueOf(sArr[2]);
            int count = 0;
            for (int j = start - 1; j < end; j++) {
                if (numArray[j] == find)
                    count++;
            }
            System.out.println(count);
        }
        input.close();
    }
}
