package com.ironman.ma.DynamicProgramming;

/**
 * Created by 127.0.0.1.ma on 20/08/17.
 */
public class EditDistance {
    static int numEdits(String s1, String s2) {
        int edits = 0;
        String[] s1_arr=s1.split(""),s2_arr =s2.split("");
        int len_s1_arr=s1_arr.length,len_s2_arr=s2_arr.length;
//        for (int i=0;)


        return edits;
    }

    public static void main(String[] args) {
        System.out.println(numEdits("geeks","gesesks"));
    }
}
