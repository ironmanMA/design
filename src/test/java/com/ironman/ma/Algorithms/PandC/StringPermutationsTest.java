package com.ironman.ma.Algorithms.PandC;

import junit.framework.TestCase;

public class StringPermutationsTest extends TestCase {

    public void testGetPermutations() {
        //car, cra, acr, arc, rac, rca
//        arc, car, rac, cra, acr, rca
        for (String s : StringPermutations.getPermutations("car")) {
            System.out.println(s);
        }
    }

    public void testGetCombPerms() {
        //car, cra, acr, arc, rac, rca
//        arc, car, rac, cra, acr, rca
        for (String s : StringPermutations.getCombPerms("car")) {
            System.out.println(s);
        }
    }

}