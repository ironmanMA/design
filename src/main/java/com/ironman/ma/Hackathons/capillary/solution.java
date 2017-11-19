package com.ironman.ma.Hackathons.capillary;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by 127.0.0.1.ma on 19/08/17.
 */
public class solution {
    static HashMap<Integer, Integer> a = new HashMap<Integer, Integer>();

    static {
        a.put(11, 34);
        a.put(12, 23);
        a.put(13, 23);
        a.put(14, 43);
        a.put(15, 41);
        a.put(16, 59);
        a.put(17, 59);
        a.put(18, 49);
        a.put(19, 39);
        a.put(20, 47);
        a.put(21, 56);
        a.put(22, 64);
        a.put(23, 94);
        a.put(24, 75);
        a.put(25, 63);
        a.put(26, 73);
        a.put(27, 99);
        a.put(28, 80);
        a.put(29, 119);
        a.put(30, 108);
        a.put(31, 128);
        a.put(32, 98);
        a.put(33, 136);
        a.put(34, 133);
        a.put(35, 135);
        a.put(36, 161);
        a.put(37, 141);
        a.put(38, 92);
        a.put(39, 177);
        a.put(40, 180);
        a.put(41, 129);
        a.put(42, 176);
        a.put(43, 167);
        a.put(44, 204);
        a.put(45, 194);
        a.put(46, 200);
        a.put(47, 214);
        a.put(48, 220);
        a.put(49, 211);
        a.put(50, 197);
        a.put(51, 180);
        a.put(52, 261);
        a.put(53, 262);
        a.put(54, 243);
        a.put(55, 261);
        a.put(56, 316);
        a.put(57, 250);
        a.put(58, 271);
        a.put(59, 305);
        a.put(60, 269);
        a.put(61, 289);
        a.put(62, 286);
        a.put(63, 312);
        a.put(64, 304);
        a.put(65, 331);
        a.put(66, 328);
        a.put(67, 347);
        a.put(68, 317);
        a.put(69, 330);
        a.put(70, 439);
        a.put(71, 398);
        a.put(72, 409);
        a.put(73, 280);
        a.put(74, 355);
        a.put(75, 407);
        a.put(76, 417);
        a.put(77, 400);
        a.put(78, 398);
        a.put(79, 412);
        a.put(80, 423);
        a.put(81, 459);
        a.put(82, 449);
        a.put(83, 456);
        a.put(84, 450);
        a.put(85, 378);
        a.put(86, 467);
        a.put(87, 468);
        a.put(88, 503);
        a.put(89, 515);
        a.put(90, 557);
        a.put(91, 565);
        a.put(92, 511);
        a.put(93, 481);
        a.put(94, 512);
        a.put(95, 553);
        a.put(96, 612);
        a.put(97, 622);
        a.put(98, 604);
        a.put(99, 620);
        a.put(100, 618);
    }

//    private static int numZeros(String input) {
//        int numZeros = 0;
//        String[] digits = input.split("");
//        for (String s : digits) {
//            if (s.equals("0")) {
//                numZeros += 1;
//            }
//        }
//        return numZeros;
//    }
//
//    private static int sumOfDigits(String input) {
//        int sum = 0;
//        String[] digits = input.split("");
//        for (String s : digits) {
//            sum += Integer.valueOf(s);
//        }
//        return sum;
//    }
//
//    private static String productFromInputTillOne(int N) {
//        BigInteger prod = new BigInteger("1");
//        for (int i = 1; i < N + 1; i++) {
//            prod = prod.multiply(BigInteger.valueOf(i));
//        }
//        return prod.toString();
//    }

    public static void main(String[] args) {
//        String prod = productFromInputTillOne(x);
//        int sum = sumOfDigits(prod),
//                numZero = numZeros(prod),
//                score = (sum - numZero);

        Scanner input = new Scanner(System.in);
        int numTests = input.nextInt();

        int max_value = -1, min_value = 100000000,
                max_val_candidate_index = -1,
                min_val_candidate_index = -1;

//        while (input.hasNext()) {
//            System.out.print(input.nextLine());
//        }
        for (int x = 1; x < numTests + 1; x++) {
            int testCase = input.nextInt();
            int score = a.get(testCase);

            /*
                calculate winner
             */
            /*
                check for max
             */
            if (score > max_value) {
                max_value = score;
                max_val_candidate_index = x;
            }
            /*
                check for min
             */
            if (score < min_value) {
                min_value = score;
                min_val_candidate_index = x;
            }

//            System.out.println(x + " " + score + ", " + testCase);
        }
        System.out.println(max_val_candidate_index + " " + max_value);
        System.out.println(min_val_candidate_index + " " + min_value);
        input.close();
    }

}
