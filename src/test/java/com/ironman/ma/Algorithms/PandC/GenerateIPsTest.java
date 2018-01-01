package com.ironman.ma.Algorithms.PandC;

import junit.framework.TestCase;

public class GenerateIPsTest extends TestCase {

    public void testGenerateIPAddrs() {
        for (String ip : new GenerateIPs().generateIPAddrs("25252525")) {
            System.out.println(ip);
        }
    }
}