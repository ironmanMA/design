package com.ironman.ma.Hackathons.truvo.q2;

import java.util.Scanner;

/**
 * Created by 127.0.0.1.ma on 29/10/17.
 */
public class TestClass {

    private static Result getMinResult(Result r1, Result r2, Result r3, Result r4) {
        Result minResult = r4;
        if (minResult.sumPicked > r3.sumPicked) {
            minResult = r3;
        }
        if (minResult.sumPicked > r2.sumPicked) {
            minResult = r2;
        }
        if (minResult.sumPicked > r1.sumPicked) {
            minResult = r1;
        }
        return minResult;
    }

    private static Result giveMin(int[] shops) {
        Result result = new Result();
        //Result maxResult = new Result(Long.MAX_VALUE,Integer.MAX_VALUE);
        if (shops.length <= 3) {
            return result;
        }

        Result[] sumTillIndex = new Result[shops.length];
        sumTillIndex[0] = new Result(shops[0], 1);
        sumTillIndex[1] = new Result(shops[1], 1);
        sumTillIndex[2] = new Result(shops[2], 1);
        sumTillIndex[3] = new Result(shops[3], 1);

        for (int i = 4; i < shops.length; i++) {
            Result minResult = getMinResult(sumTillIndex[i - 4], sumTillIndex[i - 3], sumTillIndex[i - 2],
                    sumTillIndex[i - 1]);
            sumTillIndex[i] = new Result(minResult.sumPicked + shops[i], minResult.numPicked + 1);
        }
        return getMinResult(sumTillIndex[shops.length - 4], sumTillIndex[shops.length - 3],
                sumTillIndex[shops.length - 2], sumTillIndex[shops.length - 1]);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numCases = input.nextInt();
        int candyCost = input.nextInt();
        long numCoinsToCarry = input.nextInt();
        int[] shops = new int[numCases - 2];
        for (int i = 0; i < numCases - 2; i++) {
            shops[i] = input.nextInt();
        }
        numCoinsToCarry += input.nextInt();
        Result result = giveMin(shops);
        numCoinsToCarry += result.sumPicked;
        numCoinsToCarry += (shops.length - result.numPicked) * candyCost;
        System.out.println(numCoinsToCarry);
        input.close();
    }

    private static class Result {
        long sumPicked = 0;
        int numPicked = 0;

        Result() {
        }

        Result(long sumPicked, int numPicked) {
            this.sumPicked = sumPicked;
            this.numPicked = numPicked;
        }
    }
}
