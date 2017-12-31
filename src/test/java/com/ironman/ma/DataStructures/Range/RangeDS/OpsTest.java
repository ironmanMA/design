package com.ironman.ma.DataStructures.Range.RangeDS;

import junit.framework.TestCase;

import java.util.ArrayList;

public class OpsTest extends TestCase {

    public void testInsertRange() {
        /*Interval a=new Interval(1,4);
        Interval b=new Interval(7,10);

        Interval ins=new Interval(2,6);*/

        /*Interval a=new Interval(0,1);
        Interval b=new Interval(4,5);

        Interval ins=new Interval(2,10);*/

        Interval a = new Interval(1, 2);
        Interval b = new Interval(3, 5);
        Interval c = new Interval(6, 7);
        Interval d = new Interval(8, 10);
        Interval e = new Interval(12, 14);

        Interval ins = new Interval(5, 9);

        ArrayList<Interval> list = new ArrayList<Interval>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);


        ArrayList<Interval> ans = Ops.insertRange(list, ins);

        for (Interval res : ans) {
            System.out.println(res.start + ", " + res.end);
        }

    }
}