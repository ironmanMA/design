package com.ironman.ma.CompanyQuestions.hotstar.q3;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 * Created by 127.0.0.1.ma on 14/10/17.
 */

/*
    calculate num trailing decimal points
    3 2
    Res: 1
    Exp 3/2=1.5, 5 is decimal of length 1

    5 3
    Res: inf
    Exp 5/3=1.6777777...., .6777777 is decimal of Infinite length

    N
    A B

    A<10^16
    A<10^9
 */

public class TestClass {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        for (int i = 0; i < N; i++) {
            BigDecimal A = BigDecimal.valueOf(Double.valueOf(s.nextInt()));
            BigDecimal B = BigDecimal.valueOf(Double.valueOf(s.nextInt()));
            BigDecimal Dec = A.divide(B, new MathContext(26, RoundingMode.UP));
            String[] k = Dec.toPlainString().split("\\.");
            if (k.length == 1) {
                System.out.println(0);
            } else {
                if (k[1].length() > 9)
                    System.out.println("inf");
                else
                    System.out.println(k[1].length());
            }
        }
        s.close();
    }
}
