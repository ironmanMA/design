package com.ironman.ma.Hackathons.skillenza.cp_softwareengineer.game.of.bits;

/**
 * Created by 127.0.0.1.ma on 15/10/17.
 * <p>
 * Game of Bits
 * <p>
 * Yale and Xavier are playing a game with numbers. Each round of the game starts with a number given to them by Zack, Yale’s little brother.
 * The number n is expressed as a binary integer with p bits
 * <p>
 * For every round, Xavier gets the first move.
 * <p>
 * The game came consists of moves performed by Yale and Xavier alternately.
 * <p>
 * The mth move of the game involves performing these operations on the number:
 * <p>
 * Toggling the mth bit (numbering of bits starts from left) of the number.
 * <p>
 * Toggling the left adjacent bit of m (if such a bit exists) if it is equal to the nth bit before toggling in step 1; otherwise keep it as is.
 * <p>
 * Toggling the right adjacent bit of m (if such a bit exists) if it is equal to the mth bit before toggling in step 1; otherwise keep it as is.
 * <p>
 * This modification of the number goes on until all p moves are made. If the modified number (as a result of all the operations) is
 * equal (or a distance one away) from the original number, then the person who made the last move wins the round; otherwise the other one wins the round.
 */

import java.util.HashMap;
import java.util.Scanner;

public class solution {

    static HashMap<String, String> modif = new HashMap<String, String>();

    static {
        modif.put("000", "111");
        modif.put("001", "111");
        modif.put("010", "000");
        modif.put("011", "000");
        modif.put("100", "111");
        modif.put("101", "111");
        modif.put("110", "000");
        modif.put("111", "000");
    }

    public static int toDecimal(String s) {
        return Integer.parseInt(s, 2);
    }

    public static String toBinary(int n) {
        if (n == 0) {
            return "0";
        }
        String binary = "";
        while (n > 0) {
            int rem = n % 2;
            binary = rem + binary;
            n = n / 2;
        }
        return binary;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int cases = in.nextInt();
        for (int i = 0; i < cases; i++) {
            int k = in.nextInt();
            String binary = toBinary(k);
//            System.out.println(binary);
            String winz = "Y";
            String winzNot = "X";
            if (binary.length() % 2 == 1) {
                winz = "X";
                winzNot = "Y";
            }
            binary = "0" + binary + "00";
            char[] chArr = binary.toCharArray();

//            for (int j = 1; j < binary.length() - 2; j++) {
//                String subst = binary.substring(j - 1, j + 2);
////                System.out.println(subst+", modif:"+modif.get(subst));
//                binary = binary.substring(0, j - 1) + modif.get(subst) + binary.substring(j + 2, binary.length());
//            }
//            System.out.println(binary);


            for (int j = 1; j < chArr.length - 2; j++) {
                String subst = new StringBuilder()
                        .append(chArr[j - 1])
                        .append(chArr[j])
                        .append(chArr[j + 1])
                        .toString();
//                String subst = String.valueOf(chArr[j - 1] + chArr[j] + chArr[j + 1]);
                char[] chArrMin = modif.get(subst).toCharArray();
                chArr[j - 1] = chArrMin[0];
                chArr[j] = chArrMin[1];
                chArr[j + 1] = chArrMin[2];
//                binary = binary.substring(0,j-1) + modif.get(subst) + binary.substring(j + 2, binary.length());
//                System.out.println(binary);
            }

            String newBin = String.valueOf(chArr);
//            System.out.println(newBin);

            int res = toDecimal(newBin.substring(1, newBin.length() - 2));
            if (Math.abs(res - k) > 1) {
                System.out.println(winzNot);
            } else {
                System.out.println(winz);
            }

        }
        in.close();
    }

}
