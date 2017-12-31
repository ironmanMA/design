package com.ironman.ma.Algorithms.DynamicProgramming;

import junit.framework.TestCase;

public class EditDistanceTest extends TestCase {

    public void testEditDistance() {
        System.out.println(EditDistance.editDistance("ATGCATGGCCAATTGCCAAT", "ATCGATCGATCG"));
    }
}