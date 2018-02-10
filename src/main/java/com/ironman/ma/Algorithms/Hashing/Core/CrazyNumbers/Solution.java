package com.ironman.ma.Algorithms.Hashing.Core.CrazyNumbers;

import java.util.HashMap;
import java.util.HashSet;

public class Solution {
    HashSet<Long> vis = new HashSet<Long>();

    public int colorful(int A) {
        vis = new HashSet<Long>();
        if (A < 10) {
            return 1;
        }
        int[] arr = new int[(int) Math.log10(A) + 1];
        int index = arr.length-1;
        while (A > 0) {
            int num = A % 10;
            arr[index] = num;
            // System.out.println(num);
            if (vis.contains((long) num) || num == 0) {
                return 0;
            } else {
                vis.add((long) num);
            }
            A = A / 10;
            index--;
        }

        for (int setSize = 2; setSize <= arr.length; setSize++) {
            long prod = 1;
            int ind = 0;
            while (ind < setSize) {
                prod *= arr[ind];
                ind++;
            }
            if (vis.contains((long) prod)) {
                return 0;
            } else {
                vis.add((long) prod);
            }
            for (int i = setSize; i < arr.length - setSize; i++) {
                prod = prod / arr[i - setSize];
                prod = prod * arr[i];
                if (vis.contains((long) prod)) {
                    return 0;
                } else {
                    vis.add((long) prod);
                }
            }
        }

        return 1;
    }
    public int[] lszero(int[] arr) {
        HashMap<Long,Integer> vis=new HashMap<Long,Integer>();
        int maxDist=Integer.MIN_VALUE;
        int start=0,end=0;
        long sum=0;

        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
        }
        if(sum==0){
            return arr;
        }
        int prev=0;
        for(int i=arr.length-1;i>=0;i--){
            if(!vis.containsKey(sum-prev)){
                vis.put(sum-prev,i);
            }
            prev+=arr[i];
        }
        sum=0;
//        System.out.println(vis);
        for(int i=0;i<arr.length;i++){
            if(vis.containsKey(sum)){
                int d=vis.get(sum)-i+1;
                if(d>maxDist){
                    maxDist=d;
                    start=i;
                    end=vis.get(sum);
                }
            }
            sum+=arr[i];
        }
        int[] ans=new int[maxDist];
        int k=0;
        for(int i=start;i<=end;i++){
            ans[k]=arr[i];
//            System.out.print(ans[k]);
            k++;
        }
        int[] dk=new int[];
        return ans;
    }
}
