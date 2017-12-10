package com.ironman.ma.DataStructures.Array.left.rotation;

/**
 * Created by 127.0.0.1.ma on 13/09/17.
 * URL : https://www.hackerrank.com/challenges/array-left-rotation?h_r=next-challenge&h_v=zen
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    static void printArr(long[] a) {
        System.out.print("ARR: ");
        for (long n : a) {
            System.out.print(n + " ");
        }
        System.out.println("");
    }

    static void printArr(int[] a) {
        System.out.print("ARR: ");
        for (int n : a) {
            System.out.print(n + " ");
        }
        System.out.println("");
    }

    static int[] leftRotation(int[] a, int d) {
        /*
            reverse first d elements, in place
            so traverse from 0 to d/2 and swap
         */
        if (d == a.length) {
            return a;
        }

        for (int i = 0; i < d / 2; i++) {
            int tmp = a[i];
            a[i] = a[d - 1 - i];
            a[d - i - 1] = tmp;
        }
//        printArr(a);
        /*
            now reverse d to len
            traverse from d to (n-1-d)/2
         */
        for (int i = 0; i <= (a.length - d - 1) / 2; i++) {
            int tmp = a[i + d];
            a[i + d] = a[a.length - i - 1];
            a[a.length - i - 1] = tmp;
        }
//        printArr(a);
        /*
            now reverse the whole array
         */
        for (int i = 0; i < a.length / 2; i++) {
            int tmp = a[i];
            a[i] = a[a.length - 1 - i];
            a[a.length - i - 1] = tmp;
        }
//        printArr(a);
        return a;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int d = in.nextInt();
        int[] a = new int[n];
        for (int a_i = 0; a_i < n; a_i++) {
            a[a_i] = in.nextInt();
        }
        int[] result = leftRotation(a, d);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != result.length - 1 ? " " : ""));
        }
        System.out.println("");


        in.close();
    }

    public static void main2(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Map<String, Integer> rep = new HashMap<String, Integer>();
        for (int a_i = 0; a_i < n; a_i++) {
            String word = in.next();
            if (rep.containsKey(word)) {
                rep.put(word, rep.get(word) + 1);
            } else {
                rep.put(word, 1);
            }
        }

        int q = in.nextInt();
        for (int a_i = 0; a_i < q; a_i++) {
            String word = in.next();
            if (rep.containsKey(word)) {
                System.out.println(rep.get(word));
            } else {
                System.out.println("0");
            }
        }

        in.close();
    }

}
