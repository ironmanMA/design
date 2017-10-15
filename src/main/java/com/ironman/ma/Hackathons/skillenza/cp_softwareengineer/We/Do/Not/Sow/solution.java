package com.ironman.ma.Hackathons.skillenza.cp_softwareengineer.We.Do.Not.Sow;
/**
 * Created by 127.0.0.1.ma on 15/10/17.
 * <p>
 * Find out the minimum number of dockyards needed in Ironman’s bay to make room for all ships that are going to arrive.
 * <p>
 * Note: Each dockyard can accommodate only one ship at a time.
 * <p>
 * The time for the arrival and departure of ships will be in 24 hour format.All ships will arrive and depart on the same day starting from 0000hrs to 2359hrs.
 * Also note that all timings are on the same day as well!
 * <p>
 * Sample Input Format
 * <p>
 * The first line of input contains an integer T which indicates the number of test cases.
 * The first line of each test case contains an integer N which indicates the number of ships that are going to arrive.
 * Then N lines follow containing the arrival and the departure time of the ships in hhmm format.
 * Then the next test case follows with the input as described above.
 * Sample Output Format
 * <p>
 * Output the minimum number of docks that are to be built in order to accomodate all the ships for each test case.
 */

import java.util.Scanner;

public class solution {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int cases = in.nextInt();
        for (int i = 0; i < cases; i++) {
            int[] times = new int[1440];
            int schedules = in.nextInt(),
                    Max = 0,
                    iter,
                    count = 0;
            for (iter = 0; iter < schedules; iter++) {
                int timeStart = in.nextInt();
                int timeEnd = in.nextInt();
                times[(timeStart / 100) * 60 + (timeStart % 100)] += 1;
                times[(timeEnd / 100) * 60 + (timeEnd % 100)] -= 1;
            }
            if (schedules > 0) {
                for (iter = 0; iter < times.length; iter++) {
                    count += times[iter];
                    Max = Math.max(Max, count);
                }
            }
            System.out.println(Max);
        }
        in.close();
    }
}
