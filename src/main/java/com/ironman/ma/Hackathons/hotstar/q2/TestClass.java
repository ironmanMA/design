package com.ironman.ma.Hackathons.hotstar.q2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by 127.0.0.1.ma on 14/10/17.
 */

/*
form largest number from
6
544 546 54 545 548 60
 */
public class TestClass {


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
//        6
//        int div = 1000000;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            int num = s.nextInt();
            int numDig = String.valueOf(num).length();
            String newS = String.valueOf(num);
//            char chaa = newS.charAt(newS.length() - 1);
            char chaa = newS.charAt(0);

            for (int j = 0; j <= 6 - numDig; j++) {
//                newS += "9";
                newS += newS.charAt(j);
            }

            int numNew = Integer.parseInt(newS);
            if (map.get(numNew) != null) {
                int k = 1;
                for (k = 1; k < 100000; k++) {
//                    System.out.println("MAP: "+map.get(numNew-k));
                    if (map.get(numNew - k) == null) {
                        map.put(numNew - k, num);
                        list.add(numNew - k);
                        break;
                    }
                }
//                System.out.println(numNew-k + ", " + num);
            } else {
                map.put(numNew, num);
                list.add(numNew);
//                System.out.println(numNew + ", " + num);
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        String res = "";
        for (int i : list) {
            res += map.get(i);
        }
        System.out.println(res);
        s.close();
    }
}
