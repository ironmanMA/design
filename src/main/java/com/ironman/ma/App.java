package com.ironman.ma;

import org.apache.log4j.Logger;

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

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");
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
}
