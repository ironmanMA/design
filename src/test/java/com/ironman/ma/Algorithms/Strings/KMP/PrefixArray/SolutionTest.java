package com.ironman.ma.Algorithms.Strings.KMP.PrefixArray;

import junit.framework.TestCase;

import java.util.ArrayList;

public class SolutionTest extends TestCase {

    public void testStrStr() {
//        System.out.println(new Solution().strStr("abcdabca",""));
//        System.out.println(new Solution().strStr("aabaabaaa",""));
//        System.out.println(new Solution().strStr("abcaby","abxabcabcaby"));
//        System.out.println(new Solution().strStr("babaaa","bbaabbbbbaabbaabbbbbbabbbabaabbbabbabbbbababbbabbabaaababbbaabaaaba"));
//        System.out.println(new Solution().strStr("baba","bbbbbbbbab"));
//        System.out.println(new Solution().romanToInt("XIVIIII"));
//        System.out.println(new Solution().intToRoman(49));
//        System.out.println(new Solution().intToRoman(3999));
//        System.out.println(new Solution().intToRoman(3998));
//        System.out.println(new Solution().intToRoman(3998).equals("MMMCMXCVIII"));
//        System.out.println(new Solution().intToRoman(45));
//        System.out.println(new Solution().intToRoman(-1));
//        System.out.println(new Solution().convert("PAYPALISHIRING",3));
//        ArrayList<String> k=new ArrayList<String>();
//        A : [ "What", "must", "be", "shall", "be." ]
//        k.add("What");
//        k.add("must");
//        k.add("be");
//        k.add("shall");
//        k.add("be.");
//
//        String s="";
//        s=s+"\t";
//
//        System.out.println(new Solution().fullJustify(k,12));

//        String json="{A:\"B\",C:{D:\"E\",F:{G:\"H\",I:\"J\"}}}";
//        String json="[\"foo\", {\"bar\":[\"baz\",null,1.0,2]}]";
//        String json="{\"id\":100,\"firstName\":\"Jack\",\"lastName\":\"Jones\",\"age\":12}";
//        String json="{\"attributes\":[{\"nm\":\"ACCOUNT\",\"lv\":[{\"v\":{\"Id\":null,\"State\":null},\"vt\":\"java.util.Map\",\"cn\":1}],\"vt\":\"java.util.Map\",\"status\":\"SUCCESS\",\"lmd\":13585},{\"nm\":\"PROFILE\",\"lv\":[{\"v\":{\"Party\":null,\"Ads\":null},\"vt\":\"java.util.Map\",\"cn\":2}],\"vt\":\"java.util.Map\",\"status\":\"SUCCESS\",\"lmd\":41962}]}";
//        ArrayList<String> jsonStr=new Solution().prettyJSON(json);
//        int count=1;
//        for(String s:jsonStr){
//            System.out.println(count+", "+s);
//            count++;
//        }
//        System.out.println(new Solution().addBinary("100","11"));
//        System.out.println(new Solution().addBinary("1111","1"));
//        System.out.println(new Solution().addBinary("1010110111001101101000","1000011011000000111100110"));
//        System.out.println(new Solution().power("20"));
//        System.out.println(new Solution().power("343154864765363242424"));
//        System.out.println(new Solution().power("2"));
//        System.out.println(new Solution().power("128"));
//        System.out.println(new Solution().power("147573952589676412928"));
//        System.out.println(new Solution().cntBits(k));
//        System.out.println(new Solution().divide(-2147483648,-10000000));
//        int[] arr =new int[]{1,5,8};
//        int[] arr =new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4};
//        int[] arr =new int[]{1000, 1000, 1000, 1000, 1001, 1002, 1003, 1003, 1004, 1010};
//        int[] arr =new int[]{0, 0, 0, 1, 1, 2, 2, 3};
//        int[] arr =new int[]{2, 0, 1, 2, 0, 3, 2, 2, 2, 1, 0, 0, 0, 1, 0, 0, 2, 2, 2, 3, 2, 3, 1, 2, 1, 2, 2, 3, 2, 3, 0, 3, 0, 2, 1, 2, 0, 0, 3, 2, 3, 0, 3, 0, 2, 3, 2, 2, 3, 1, 3, 3, 0, 3, 3, 0, 3, 3, 2, 0, 0, 0, 0, 1, 3, 0, 3, 1, 3, 1, 0, 2, 3, 3, 3, 2, 3, 3, 2, 2, 3, 3, 3, 1, 3, 2, 1, 0, 0, 0, 1, 0, 3, 2, 1, 0, 2, 3, 0, 2, 1, 1, 3, 2, 0, 1, 1, 3, 3, 0, 1, 2, 1, 2, 2, 3, 1, 1, 3, 0, 2, 2, 2, 2, 1, 0, 2, 2, 2, 1, 3, 1, 3, 1, 1, 0, 2, 2, 0, 2, 3, 0, 1, 2, 1, 1, 3, 0, 2, 3, 2, 3, 2, 0, 2, 2, 3, 2, 2, 0, 2, 1, 3, 0, 2, 0, 2, 1, 3, 1, 1, 0, 0, 3, 0, 1, 2, 2, 1, 2, 0, 1, 0, 0, 0, 1, 1, 0, 3, 2, 3, 0, 1, 3, 0, 0, 1, 0, 1, 0, 0, 0, 0, 3, 2, 2, 0, 0, 1, 2, 0, 3, 0, 3, 3, 3, 0, 3, 3, 1, 0, 1, 2, 1, 0, 0, 2, 3, 1, 1, 3, 2};
//        int[] arr =new int[]{3, 3, 0, 2, 1, 2, 1, 0, 0, 2, 0, 2, 1, 3, 0, 1, 2, 0, 0, 1, 3, 2, 0, 2, 0, 3, 2, 3, 3, 1, 3, 0, 3, 0, 0, 2, 1, 3, 0, 2, 0, 1, 2, 1, 3, 3, 1, 2, 3, 2, 3, 1, 2, 3, 3, 2, 1, 2, 1, 2, 1, 2, 2, 0, 1, 2, 3, 0, 2, 0, 0, 1, 3, 1, 1, 0, 1, 2, 2, 3, 3, 1, 2, 3, 1, 1, 0, 0, 1, 1, 2, 2, 2, 1, 2, 1, 0, 3, 2, 2, 1, 3, 3, 2, 1, 1, 1, 1, 0, 2, 1, 1, 0, 0, 0, 3, 2, 3, 0, 0, 0, 2, 3, 2, 3, 2, 0, 2, 0, 2, 3, 1, 1, 3, 1, 0, 1, 0, 2, 0, 1, 3, 1, 1, 1, 0, 1, 1, 0, 2, 0, 0, 0, 0, 1, 0, 3, 2, 3, 2, 3, 0, 0, 0, 3, 2, 1, 1, 2, 3, 2, 2, 3, 3, 2, 3, 1, 1, 2, 0, 2, 2, 2, 0, 0, 0, 1, 0, 2, 0, 0, 2, 3, 2, 2, 3, 1, 0, 2, 2, 2, 3, 3, 1, 3, 3, 1, 1, 0, 0, 1, 2, 1, 1, 2, 3, 0, 2, 1, 1, 2, 3, 0, 3, 0, 0, 3, 1, 0, 3, 2, 0, 3, 0, 1, 1, 1, 2, 2, 1, 3, 2, 3, 1, 1, 1, 0, 2, 2, 3, 2, 0, 0, 0, 1, 2, 0, 3, 1, 2, 0, 0, 3, 0, 0, 0, 1, 1, 1, 0, 3, 2, 3, 1, 1, 3, 1, 0, 3, 3, 0, 1, 2, 3, 0, 1, 1, 2, 2, 0, 1, 1, 3, 3, 0, 1, 1, 3, 0, 3, 1, 0, 0, 2, 2, 0, 3, 1, 3, 0, 0, 2, 3, 3, 2, 0, 0, 2, 2, 3, 2, 2, 3, 1, 0, 1, 2, 3, 2, 1, 0, 3, 3, 0, 1, 1, 3, 1, 3, 2, 3, 0, 3, 3, 0, 2, 3, 1, 3, 1, 1, 1, 0, 1, 3, 0, 1, 0, 1, 1, 0, 0, 3, 1, 1, 3, 2, 2, 1, 1, 2, 0, 0, 1, 1, 1, 1, 3, 2, 2, 1, 0, 3, 0, 0, 2, 3, 1, 1, 1, 2, 0, 1, 0, 1, 2, 3, 3, 3, 1, 0, 3, 2, 0, 0, 1, 0, 0, 2, 0, 3, 3, 3, 1, 2, 1, 1, 3, 3, 0, 1, 2, 3, 2, 2, 1, 3, 2, 2, 1, 3, 2, 2, 2, 2, 2, 1, 2, 0, 2, 2, 2, 0, 0, 1, 2, 2, 2, 2, 0, 1, 2, 0, 3, 0, 2, 0, 3, 2, 2, 2, 1, 1, 0, 2, 0, 2, 3, 0, 3, 1, 2, 0, 0, 1, 2, 1, 0, 0, 0, 3, 3, 2, 0, 3, 1, 0, 2, 3, 1, 3, 0, 1, 0, 2, 0, 2, 0, 1, 2, 0, 2, 3, 2, 1, 3, 1, 3, 1, 0, 3, 0, 1, 0, 2, 0, 3, 1, 1, 0, 1, 1, 3, 2, 0, 1, 3, 0, 0, 1, 3, 2, 0, 1, 0, 3, 1, 1, 0, 1, 0, 3, 2, 2, 3, 2, 0, 2, 3, 1, 2, 0, 1, 1, 3, 0, 0, 2, 1, 0, 2, 3, 3, 1, 1, 0, 3, 3, 1, 2, 2, 3, 2, 0, 2, 1, 2, 3, 0, 0, 1, 3, 2, 0, 1, 0, 1, 2, 0, 0, 1, 0, 1, 2, 1, 3, 1, 3, 3, 1, 1, 2, 0, 0, 2, 0, 1, 1, 3, 2, 1, 3, 1, 0, 3, 2, 2, 3, 3, 0, 3, 2, 3, 0, 2, 0, 1, 3, 1, 0, 0, 2, 2, 0, 1, 3, 2, 2, 3, 2, 2, 2, 3, 3, 3, 0, 1, 2, 0, 1, 0, 0, 0, 2, 0, 0, 1, 1, 0, 2, 1, 0, 3, 0, 3, 1, 1, 1, 1, 0, 2, 2, 2, 1, 3, 1, 1, 1, 3, 1, 3, 3, 3, 0, 1, 2, 2, 2, 3, 1, 2, 1, 0, 1, 3, 3, 2, 0, 1, 2, 0, 3, 1, 0, 0, 3, 3, 1, 1, 0, 1, 1, 0, 0, 3, 0, 0, 3, 1, 1, 2, 0, 0, 1, 1, 3, 0, 3, 1, 0, 2, 3, 1, 3, 2, 3, 3, 1, 3, 0, 1, 0, 2, 0, 1, 2, 0, 0, 0, 0, 1, 3, 1, 1, 0, 2, 2, 1, 3, 3, 2, 1, 3, 2, 3, 2, 1, 0, 0, 3, 0, 2, 2, 1, 1, 1, 0, 0, 2, 1, 2, 2, 2, 3, 1, 0, 1, 0, 1, 3, 1, 2, 1, 3, 1, 2, 1, 1, 0, 0, 1, 0, 0, 1, 1, 3, 2, 0, 1, 1, 2, 2, 3, 0, 1, 1, 1, 0, 3, 0, 1, 0, 1, 2, 2, 2, 2, 1, 1, 2, 3, 2, 0, 3, 2, 2, 2, 0, 1, 3, 0, 3, 2, 3, 0, 1};
//        int[] arr =new int[]{2, 0, 1, 2, 0, 3, 2, 2, 2};
//        int[] arr =new int[]{-4, -8, -10, -9, -1, 1, -2, 0, -8, -2};
//        int[] arr =new int[]{2, 1, -9, -7, -8, 2, -8, 2, 3, -8};
//        int[] arr =new int[]{1,1,1,2,2};
//        int[] arr =new int[]{4, 6, 13, 16, 20, 3, 1, 12};
//        int[] arr =new int[]{11, 18, 13, 1, 19, 14, 8, 15, 16, 20, 12, 6, 10, 5, 17, 2, 4};
//        int[] arr =new int[]{2345678 ,23456 ,23459 ,2343 ,5463789 ,2345678 ,23456 ,23459 ,2343 ,5463789};
//        int[] arr =new int[]{10, 21, 22, 100, 101, 200, 300};
        int[] arr =new int[]{1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0};
//        int[] arr =new int[]{0,1,1,1};

        int[] arr2 =new int[]{9,16,19};
        ArrayList<Integer> k=new ArrayList<Integer>();
        ArrayList<Integer> k2=new ArrayList<Integer>();
        for(int a:arr){
            k.add(a);
        }
        for(int a:arr2){
            k2.add(a);
        }
//        new Solution().merge(k2,k);
//        new Solution().removeDuplicates(k);
//        System.out.println(new Solution().removeElement(k,0));
//        System.out.println(new Solution().threeSumClosest(k,-1));
//        System.out.println(new Solution().nTriang(k));
//        System.out.println(new Solution().nTriangNew(k));
//        System.out.println(new Solution().maxone(k,0));
        System.out.println(new Solution().maxone(k,4));


    }
}