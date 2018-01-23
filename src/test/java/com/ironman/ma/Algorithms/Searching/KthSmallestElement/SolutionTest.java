package com.ironman.ma.Algorithms.Searching.KthSmallestElement;

import junit.framework.TestCase;

public class SolutionTest extends TestCase {

    public void testSolve() {
        int[] list1=new int[]{1,2,4,6,6,6};
        int[] list2=new int[]{2,4,7,8};

        int k=(list1.length+list2.length+1)/2;

        com.ironman.ma.Algorithms.Searching.KthSmallestElement.Solution sol=
                new com.ironman.ma.Algorithms.Searching.KthSmallestElement.Solution(list1,list2);

        if(sol.solve(k)==5){
            assert true;
        }else{
            System.out.println(sol.solve(k));
            assert false;
        }

        k=3;
        if(sol.solve(k)==2){
            assert true;
        }else{
            System.out.println(sol.solve(k));
            assert false;
        }

        k=7;
        if(sol.solve(k)==6){
            assert true;
        }else{
            System.out.println(sol.solve(k));
            assert false;
        }

        sol=new com.ironman.ma.Algorithms.Searching.KthSmallestElement.Solution(new int[]{},list2);
        k=(list2.length+1)/2;
        if(sol.solve(k)==5){
            assert true;
        }else{
            System.out.println(sol.solve(k));
            assert false;
        }

        list1=new int[]{-50, -41, -40, -19, 5, 21, 28};
        list2=new int[]{-50, -21, -10};
        k=(list1.length+list2.length+1)/2;
        sol=new com.ironman.ma.Algorithms.Searching.KthSmallestElement.Solution(list1,list2);
        System.out.println(sol.solve(k));
    }
}