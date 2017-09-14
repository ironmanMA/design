package com.ironman.ma.Array.dynamic.array;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by 127.0.0.1.ma on 11/09/17.
 */
public class Solution {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        ArrayList<ArrayList<Long>> arr = new ArrayList<ArrayList<Long>>();
        for (int i = 0; i < n; i++) {
            arr.add(new ArrayList<Long>());
        }
        int queries = input.nextInt();
        long lastAnswer = 0;
        for (int i = 0; i < queries; i++) {
            int query_type = input.nextInt();
            long x = input.nextLong();
            long y = input.nextLong();
            long index = ((x ^ lastAnswer) % n);
//            System.out.println(i + ", index:" + index + ", qt:" + query_type);
            ArrayList<Long> seq = arr.get((int) index);
            if (query_type == 1) {
                seq.add(y);
            } else {
                lastAnswer = seq.get((int) (y % seq.size()));
                System.out.println(lastAnswer);
            }
        }
        input.close();
    }
}
