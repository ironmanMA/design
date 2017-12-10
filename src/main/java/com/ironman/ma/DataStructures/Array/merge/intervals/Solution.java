package com.ironman.ma.DataStructures.Array.merge.intervals;

import java.util.ArrayList;

/**
 * Created by 127.0.0.1.ma on 26/08/17.
 */
public class Solution {

    public static void main(String[] args) {
        //        [1,3],[6,9] insert and merge [2,5]
        //        [1,3],[6,9] insert and merge [2,15]
    }

    private boolean isStartBetween(Interval existInterval, Interval insertInterval) {
        return insertInterval.start >= existInterval.start && insertInterval.start <= existInterval.end;
    }

    private boolean isEndBetween(Interval existInterval, Interval insertInterval) {
        return insertInterval.end <= existInterval.end;
    }

    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        if (newInterval.start > newInterval.end) {
            newInterval = new Interval(newInterval.end, newInterval.start);
        }

//        find penetration, insert till found
        ArrayList<Interval> finalIntervalArrayList = new ArrayList<Interval>();
        if (intervals.get(0).start > newInterval.end) {
            finalIntervalArrayList.add(newInterval);
            finalIntervalArrayList.addAll(intervals);
            return finalIntervalArrayList;
        }
        if (intervals.get(0).start > newInterval.start) {
//            seek merge
            int intervalStart = newInterval.start, intervalEnd = -1;
            int i = 0;
            for (i = 0; i < intervals.size(); i++) {
                if (isEndBetween(intervals.get(i), newInterval)) {
                    intervalEnd = intervals.get(i).end;
                    break;
                } else if (intervals.get(i).start > newInterval.end) {
                    intervalEnd = newInterval.end;
                    break;
                }
            }
            if (i == intervals.size() &&
                    intervals.get(i - 1).end < newInterval.end) {
                intervalEnd = newInterval.end;
            }
            for (int j = i - 1; j < intervals.size(); j++)
                finalIntervalArrayList.add(intervals.get(j));

            Interval interval = new Interval(intervalStart, intervalEnd);
            finalIntervalArrayList.add(interval);
            finalIntervalArrayList.addAll(intervals);
            return finalIntervalArrayList;
        }

        int i = 0;
        for (i = 0; i < intervals.size(); i++) {
            if (isStartBetween(intervals.get(i), newInterval)) {
                // find merge end
                Interval interval;
                int j = i, intervalStart = intervals.get(i).start, intervalEnd = -1;
                for (j = i; j < intervals.size(); j++) {
                    if (isEndBetween(intervals.get(j), newInterval)) {
                        intervalEnd = intervals.get(j).end;
                        break;
                    } else if (intervals.get(j).start > newInterval.end) {
                        intervalEnd = newInterval.end;
                        break;
                    }
                }
                if (j == intervals.size() &&
                        intervals.get(j - 1).end < newInterval.end) {
                    intervalEnd = newInterval.end;
                }
                interval = new Interval(intervalStart, intervalEnd);
                finalIntervalArrayList.add(interval);
            } else {
                //insert in front
                finalIntervalArrayList.add(intervals.get(i));
            }
        }
        if (i == intervals.size() &&
                intervals.get(i - 1).end < newInterval.start) {
            finalIntervalArrayList.add(newInterval);
        }

        return finalIntervalArrayList;
    }

    //     * Definition for an interval.
    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
