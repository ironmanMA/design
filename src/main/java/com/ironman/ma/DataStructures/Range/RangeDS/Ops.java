package com.ironman.ma.DataStructures.Range.RangeDS;

import java.util.ArrayList;

public class Ops {
    public static ArrayList<Interval> insertRange(ArrayList<Interval> intervalsList, Interval insert) {
        if (intervalsList == null || intervalsList.size() == 0) {
            intervalsList = new ArrayList<Interval>();
            intervalsList.add(insert);
            return intervalsList;
        }

        Interval latest = insert;
        Interval curr = insert;
        System.out.println("incoming: s:" + latest.start + ", e:" + latest.end);

        for (int i = 0; i < intervalsList.size(); i++) {
            curr = intervalsList.get(i);
            System.out.println("FOR: l.s:" + latest.start + ", l.e:" + latest.end + ", c.s:" + curr.start + ", c.e:" + curr.end);
            if (latest.end < curr.start) {
                System.out.println("add 1: l.s:" + latest.start + ", l.e:" + latest.end + ", c.s:" + curr.start + ", c.e:" + curr.end);
                //add latest and return;
                intervalsList.add(i, latest);
                return intervalsList;
            } else if (latest.start < curr.start) {
                latest = new Interval(latest.start, Math.max(latest.end, curr.end));
                intervalsList.add(i, latest);
                intervalsList.remove(i + 1);
                System.out.println("add 2: l.s:" + latest.start + ", l.e:" + latest.end + ", c.s:" + curr.start + ", c.e:" + curr.end);
                while (i + 1 < intervalsList.size() && latest.end >= intervalsList.get(i + 1).start) {
                    Interval future = intervalsList.get(i + 1);
                    latest = new Interval(latest.start, Math.max(latest.end, future.end));
                    intervalsList.add(i, latest);
                    intervalsList.remove(i + 1);
                    intervalsList.remove(i + 2);
                    System.out.println("add 2.5: l.s:" + latest.start + ", l.e:" + latest.end + ", f.s:" + future.start + ", f.e:" + future.end);
                }
                return intervalsList;
            } else if (latest.start >= curr.start && curr.end >= latest.start && latest.end > curr.end) {
                latest = new Interval(curr.start, Math.max(latest.end, curr.end));
                intervalsList.add(i, latest);
                intervalsList.remove(i + 1);
                System.out.println("add 3: l.s:" + latest.start + ", l.e:" + latest.end + ", c.s:" + curr.start + ", c.e:" + curr.end + ", ret");
                while (i + 1 < intervalsList.size() && latest.end >= intervalsList.get(i + 1).start) {
                    Interval future = intervalsList.get(i + 1);
                    latest = new Interval(latest.start, Math.max(latest.end, future.end));
                    intervalsList.add(i, latest);
                    intervalsList.remove(i + 1);
                    intervalsList.remove(i + 1);
                    System.out.println("add 3.5: l.s:" + latest.start + ", l.e:" + latest.end + ", f.s:" + future.start + ", f.e:" + future.end + ", i:" + i);
                }
                return intervalsList;
            }
        }
        if (insert.start > curr.end) {
            intervalsList.add(insert);
        }
        //merge this and then send
        return intervalsList;
    }
}
