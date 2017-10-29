package com.ironman.ma;

import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private static final Logger log = Logger.getLogger(App.class);

    public static void goodLog() {
        log.info("testing goodLog");
    }

    public static void badLog() {
        log.error("testing badLog");
    }

    public static void realBadLog() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException ex) {
            log.error("testing realBadLog " + ex.getMessage(), ex);
        }
    }

    public static void main2(String[] args) throws InterruptedException {
        System.out.println("Hello World!");

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.close();
        log.info("testing main");
        for (int i = 0; i < 2; i++) {
            goodLog();
            Thread.sleep(1000);
            badLog();
            Thread.sleep(1000);
            realBadLog();
            Thread.sleep(1000);
        }
        goodLog();
    }

    public static void leftshift(int i, int j) {
        i <<= j;
        System.out.println(i);
    }

    public static void main3(String args[]) {
        int i = 4, j = 4;
        leftshift(i, j);
        System.out.println(i);
        int k[] = {1, 2};
    }

//    public static void main(String [] args)
//    {
//
//        int d = b.g();
//        App a = new App();
//        a.start();
//    }
//    int g() {
//        return 2147483648;
//    }

    void start2() {
        long[] a1 = {3, 4, 5};
        long[] a2 = fix(a1);
        System.out.print(a1[0] + a1[1] + a1[2] + " ");
        System.out.println(a2[0] + a2[1] + a2[2]);
    }

    long[] fix(long[] a3) {
        a3[1] = 7;
        return a3;
    }

    void start() {
        String s1 = "slip";
        String s2 = fix(s1);
        System.out.println(s1 + " " + s2);
    }

    String fix(String s1) {
        s1 = s1 + "stream";
        System.out.print(s1 + " ");
        return "stream";
    }

}
