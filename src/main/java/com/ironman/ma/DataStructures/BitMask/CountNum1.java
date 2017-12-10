package com.ironman.ma.DataStructures.BitMask;

/**
 * Created by 127.0.0.1.ma on 20/08/17.
 */
public class CountNum1 {
    public static int numSetBits(long a) {
        if (a==1){
            return 1;
        }
        if (a==0){
            return 0;
        }
        if (a%2 !=0){
            return 1+numSetBits(a/2);
        }else {
            return numSetBits(a/2);
        }
    }

    public static void main(String[] args) {
        System.out.println(numSetBits(11));
    }
}
