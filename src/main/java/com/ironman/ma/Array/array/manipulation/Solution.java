package com.ironman.ma.Array.array.manipulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by 127.0.0.1.ma on 14/09/17.
 * URL : https://www.hackerrank.com/challenges/crush/
 */
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        long[] arr = new long[n];
        long max = Long.MIN_VALUE;
        Map<Integer, Long> collisionMap = new HashMap<Integer, Long>();
        for (int a0 = 0; a0 < m; a0++) {
            int a = in.nextInt();
            int b = in.nextInt();
            long k = in.nextLong();
            if (a == b) {
                max = Math.max(max, k);
            } else {
                arr[a - 1] += k;
                if (b == n) {
                    b = b - 1;
                }
                arr[b] -= k;
            }
//            if (arr[a] < 0) {
////                push to map
//                if (collisionMap.containsKey(a)) {
//                    collisionMap.put(a, collisionMap.get(a) + k);
//                } else {
//                    collisionMap.put(a, k);
//                }
//            }else if (arr[a]==0){
//                continue;
//            }else {
//                if (collisionMap.containsKey(a)) {
//                    collisionMap.put(a, collisionMap.get(a) + k);
//                } else {
//                    collisionMap.put(a, k);
//                }
//            }
//            arr[a] += k;
            //                push to map
//            if (collisionMap.containsKey(b)) {
//                collisionMap.put(b, collisionMap.get(b) + k);
//            } else {
//                collisionMap.put(b, k);
//            }
//            if (b == n) {
//                b = b - 1;
//            }
//            arr[b] -= k;
        }
        in.close();
        long layerWeight = 0;

        for (int i = 0; i < n; i++) {
            layerWeight = layerWeight + arr[i];
            long localMax = layerWeight;
            if (collisionMap.containsKey(i)) {
                localMax += collisionMap.get(i);
            }
            max = Math.max(localMax, max);
        }

        System.out.println(max);

    }
}
