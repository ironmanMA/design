package com.ironman.ma.HashMap.equal.stacks;

import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Created by 127.0.0.1.ma on 08/10/17.
 */
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt();
        int n2 = in.nextInt();
        int n3 = in.nextInt();

        int SumH1 = 0, SumH2 = 0, SumH3 = 0;

        int h1[] = new int[n1];
        for (int h1_i = 0; h1_i < n1; h1_i++) {
            h1[h1_i] = in.nextInt();
            SumH1 += h1[h1_i];
        }
        int h2[] = new int[n2];
        for (int h2_i = 0; h2_i < n2; h2_i++) {
            h2[h2_i] = in.nextInt();
            SumH2 += h2[h2_i];
        }
        int h3[] = new int[n3];
        for (int h3_i = 0; h3_i < n3; h3_i++) {
            h3[h3_i] = in.nextInt();
            SumH3 += h3[h3_i];
        }

        /*
            get smallest stack
         */
        int min = Math.min(Math.min(n1, n2), n3);

        int h_min[] = null, h_min2[] = null, h_min3[] = null;
        int sum_min = 0, sum_min2 = 0, sum_min3 = 0;

        if (min == n1) {
            h_min = h1;
            sum_min = SumH1;

            h_min2 = h2;
            sum_min2 = SumH2;

            h_min3 = h3;
            sum_min3 = SumH3;
        } else if (min == n2) {
            h_min = h2;
            sum_min = SumH2;

            h_min2 = h1;
            sum_min2 = SumH1;

            h_min3 = h3;
            sum_min3 = SumH3;
        } else {
            h_min = h3;
            sum_min = SumH3;

            h_min2 = h2;
            sum_min2 = SumH2;

            h_min3 = h1;
            sum_min3 = SumH1;
        }

        LinkedHashSet<Integer> sumMap = new LinkedHashSet<Integer>();

        sumMap.add(sum_min);

        int sumtill = 0;
        for (int i = 0; i < h_min.length; i++) {
            sumtill += h_min[i];
            sumMap.add(sum_min - sumtill);
        }
        sumMap.add(sum_min - sumtill);
        sumtill = 0;
        LinkedHashSet<Integer> sumMap2 = new LinkedHashSet<Integer>();

        if (sumMap.contains(sum_min2 - sumtill)) {
            sumMap2.add(sum_min2 - sumtill);
        }
        for (int i = 0; i < h_min2.length; i++) {
            sumtill += h_min2[i];
            if (sumMap.contains(sum_min2 - sumtill))
                sumMap2.add(sum_min2 - sumtill);
        }

        if (sumMap.contains(sum_min2 - sumtill))
            sumMap2.add(sum_min2 - sumtill);
        sumtill = 0;

        if (sumMap2.contains(sum_min3 - sumtill)) {
            System.out.println(sum_min3 - sumtill);
            return;
        }

        for (int i = 0; i < h_min3.length; i++) {
            sumtill += h_min3[i];
            if (sumMap2.contains(sum_min3 - sumtill)) {
                System.out.println(sum_min3 - sumtill);
                return;
            }
        }
        if (sumMap2.contains(sum_min3 - sumtill)) {
            System.out.println(sum_min3 - sumtill);
        }
    }
}