package com.ironman.ma.Hackathons.redlock.q3;

/**
 * Created by 127.0.0.1.ma on 07/12/17.
 */
public class Solution {

    static int[][] zombieLand;

    static void markVisited(int result, int row, int col) {
        if (zombieLand[row][col] == 1 && row < zombieLand.length && col < zombieLand.length) {
            markVisited(result, row, col + 1);
            markVisited(result, row + 1, col);
            System.out.println(row + ", " + col + " result:" + result);
            zombieLand[row][col] = 0;
        }
        return;
    }

    /*
     * Complete the function below.
     */
    static int zombieCluster(String[] zombies) {
        int result = 0;
        zombieLand = new int[zombies.length + 2][zombies.length + 2];
        for (int ai = 0; ai < zombies.length; ai++) {
            System.out.println(zombies[ai]);
            String[] chars = zombies[ai].split("");
            for (int aj = 0; aj < chars.length; aj++) {
                if (chars[aj].equals("1")) {
                    zombieLand[ai + 1][aj + 1] = 1;
                }
            }
        }
        for (int bi = 0; bi < zombies.length + 2; bi++) {
            for (int bj = 0; bj < zombies.length + 2; bj++) {
                System.out.print(zombieLand[bi][bj] + " ");
            }
            System.out.println("");
        }
        for (int bi = 1; bi <= zombies.length; bi++) {
            for (int bj = 1; bj <= zombies.length; bj++) {
                if (zombieLand[bi][bj] == 1) {
                    result++;
                    markVisited(result, bi, bj);
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
//        Scanner input=new Scanner(System.in);
//        int numCases=input.nextInt();
//        input.close();
        String[] zombies2 = {
                "1100",
                "1110",
                "0110",
                "0001"
        };
        String[] zombies3 = {
                "1101",
                "1110",
                "0110",
                "1001"
        };
        String[] zombies = {
                "10000",
                "01000",
                "00100",
                "00010",
                "00001"
        };
        System.out.println(zombieCluster(zombies3));
    }
}
