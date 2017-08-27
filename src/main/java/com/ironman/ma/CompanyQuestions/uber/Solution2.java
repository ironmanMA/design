package com.ironman.ma.CompanyQuestions.uber;

/**
 * Created by 127.0.0.1.ma on 26/08/17.
 */
public class Solution2 {

    static boolean canDo(int x1, int y1, int x2, int y2) {
        if (x1 == x2 && y1 == y2) {
            return true;
        }
        if (x1 > x2)
            return false;
        if (y1 > y2)
            return false;
        return canDo(x1 + y1, y1, x2, y2) || canDo(x1, x1 + y1, x2, y2);
    }

    static String canReach(int x1, int y1, int x2, int y2) {
        if (canDo(x1, y1, x2, y2)) {
            return "Yes";
        }
        return "No";
    }

    public static void main(String[] args) {
        System.out.println(canReach(1, 4, 1, 5));
    }

}
