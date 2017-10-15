package com.ironman.ma.Hackathons.skillenza.cp_leadcapture.Virus.Infection;

/**
 * Created by 127.0.0.1.ma on 15/10/17.
 * Virus Infection
 * <p>
 * The files in a folder are stored in an _nXm_ matrix, where n gives the number of rows and m gives the number of columns.
 * The numbering system starts from (1,1).
 * <p>
 * There is a powerful virus in one of the files and the location of the file is given by _(r,c)_.
 * <p>
 * The virus spreads to adjacent blocks in one second. From each infected block, it takes another second to spread to its adjacent blocks. And so on.
 * <p>
 * For example, if the virus is at (1,1), it takes a second to spread to the blocks (1,2), (2,1) and (2,2). After two seconds, the infected blocks are
 * (1,1), (1,2), (2,1), (2,2), (1,3), (2,3), (3,3), (3,2), (3,1).
 * <p>
 * And so on.
 * <p>
 * So, given the values of n, m and (r,c), find the number of seconds it will take to spread through the entire folder.
 * <p>
 * Input Format
 * <p>
 * The input contains:
 * <p>
 * The first line contains t test cases.
 * <p>
 * Each test case contains two lines:
 * <p>
 * The first line contains n and m separated by a space.
 * Next line of the test case contains (r,c) which gives the position of the virus infected file in the folder.
 * Output Format
 * <p>
 * The output contains t lines each of which contains the time needed for the virus to spread to the entire folder in minutes and seconds.
 * Note that if the time taken is less than a minute, the output should be x seconds. If the time is 1 second, the output should be 1 second.
 * If the time is 1 minute, then the time should be output as 1 minute 0 seconds. See the test cases for clarity.
 * <p>
 * Constraints
 * <p>
 * 1<=t<=10000
 * <p>
 * 1<=n,m<=10000
 * <p>
 * 1<=r<=n
 * <p>
 * 1<=c<=m
 * <p>
 * Sample Input
 * <p>
 * 3
 * 6 5
 * (2,2)
 * 100 50
 * (39,5)
 * 44 130
 * (1,1)
 * Sample Output
 * <p>
 * 4 seconds
 * 1 minute 1 second
 * 2 minutes 9 seconds
 * Explanation
 * <p>
 * For the first test case, there are 6 rows and 5 columns.
 * The virus is at position (2,2) it will take 1 second to spread to (1,1), (1,2), (1,3), (2,1), (2,3), (3,1), (3,2), (3,3).
 * From there it will take 1 more second to spread to adjacent files. To spread to all the files, it will take total of 4 seconds.
 */

import java.util.Scanner;

public class solution {
    static String toTime(int time) {
        int min = time / 60;
        String minApp = "minute";
        int sec = time % 60;
        String secApp = "second";

        String delim = " ";

        if (sec == 0 || sec > 1) {
            secApp = "seconds";
        }
        if (min > 1) {
            minApp = "minutes";
        }

        if (min != 0) {
            return min + delim + minApp + delim + sec + delim + secApp;
        }
        return sec + delim + secApp;
    }

    public static void main(String[] args) {
//        System.out.println(toTime(60));
//        System.out.println(toTime(0));
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        for (int i = 0; i < cases; i++) {
            int rows = input.nextInt();
            int cols = input.nextInt();
            String virusLoc = input.next();
            virusLoc = virusLoc.trim().substring(1, virusLoc.length() - 1);
            String[] strArr = virusLoc.split(",");
            int virusLocRow = Integer.parseInt(strArr[0]),
                    virusLocCol = Integer.parseInt(strArr[1]);
            int len_V_00 = Math.max(Math.abs(virusLocRow - 1), Math.abs(virusLocCol - 1)),
                    len_V_0C = Math.max(Math.abs(virusLocRow - 1), Math.abs(virusLocCol - cols)),
                    len_V_R0 = Math.max(Math.abs(virusLocRow - rows), Math.abs(virusLocCol - 1)),
                    len_V_RC = Math.max(Math.abs(virusLocRow - rows), Math.abs(virusLocCol - cols));
            int timeToSpread = Math.max(Math.max(len_V_00, len_V_0C), Math.max(len_V_R0, len_V_RC));
//            System.out.println(rows + ", " + cols + ", Row:" + virusLocRow + ", Col:" + virusLocCol + ", " +
//                    "TimeSpread:" + (timeToSpread));
            System.out.println(toTime(timeToSpread));
        }
        input.close();
    }
}
