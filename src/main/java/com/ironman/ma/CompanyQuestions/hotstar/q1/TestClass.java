package com.ironman.ma.CompanyQuestions.hotstar.q1;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by 127.0.0.1.ma on 14/10/17.
 */

/*
    calculate num combinations
    (mod 10^9+7) of string length K, with X(alpha-set), Y(num-set),
        P(num alphas to be used),Q(num numbers to be used)
      Given arrangement starts with an AlphaBet
 */

public class TestClass {

    static long Mod = 1000000007;
    static BigInteger Modu = BigInteger.valueOf(1000000007);

    static BigInteger binomial(final int N, final int K) {
        BigInteger ret = BigInteger.ONE;
        for (int k = 0; k < K; k++) {
            ret = ret.multiply(BigInteger.valueOf(N - k))
                    .divide(BigInteger.valueOf(k + 1));
        }
        return ret;
    }

    public static void main2(String[] args) {
        System.out.println(binomial(5, 2));
        BigInteger NumAlpha = BigInteger.valueOf(25);

        BigInteger XProduct = BigInteger.valueOf(50).modPow(NumAlpha, Modu);
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        for (int i = 0; i < N; i++) {
            int X = s.nextInt();
            int Y = s.nextInt();
            int K = s.nextInt();
            int P = s.nextInt();
            int Q = s.nextInt();

            long Ans = 0;
            BigInteger Product = BigInteger.valueOf(1);
            BigInteger AlphaSetNum = BigInteger.valueOf(X);
            BigInteger IntSetNum = BigInteger.valueOf(Y);
            BigInteger NumAlpha = BigInteger.valueOf(P);
            BigInteger NumInt = BigInteger.valueOf(Q);

            BigInteger XProduct = AlphaSetNum.modPow(NumAlpha, Modu);
            BigInteger YProduct = IntSetNum.modPow(NumInt, Modu);
            BigInteger combos = binomial(K, Math.min(P - 1, Q));

            BigInteger resultPrt = XProduct.mod(Modu).multiply(YProduct.mod(Modu)).mod(Modu);
            BigInteger resultFinal = resultPrt.mod(Modu).multiply(combos.mod(Modu)).mod(Modu);
            System.out.println(resultFinal);

//            for (int j = 0; j < P; j++) {
////                Product = Product.multiply(BigInteger.valueOf(X));
//                Product = Product.multiply(BigInteger.valueOf(X));
//            }
//            for (int j = 0; j < Q; j++) {
//                Product = Product.multiply(BigInteger.valueOf(Y));
//            }
//
//            Product = Product.multiply(binomial(K, P - 1));
//
////            System.out.println(Product);
//            System.out.println(Product.remainder(Modu));

        }
        s.close();
    }
}
