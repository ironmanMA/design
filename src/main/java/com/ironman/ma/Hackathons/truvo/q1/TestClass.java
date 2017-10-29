package com.ironman.ma.Hackathons.truvo.q1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by 127.0.0.1.ma on 29/10/17.
 */
public class TestClass {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numCases = input.nextInt();
        long totalSum = 0;
        Integer[] arr = new Integer[numCases];
        for (int i = 0; i < numCases; i++) {
            arr[i] = input.nextInt();
            totalSum += arr[i];
        }
        Arrays.sort(arr, Collections.reverseOrder());

        long sumForALice = 0, sumRem = totalSum, numPicked = 0;
        for (int i = 0; i < arr.length; i++) {
            if (sumForALice > sumRem) {
                break;
            } else {
                sumForALice += arr[i];
                sumRem -= arr[i];
                numPicked++;
            }
        }
        System.out.println(numPicked);
        input.close();
    }
}
