package com.ironman.ma.Algorithms.Strings.Max.Length.For.Palidrome;

public class Solution {
    public long lpsStr(String A, int startPos, boolean isOdd){
        long length=0;
        int i=0;
        if(isOdd){
            length=1;
            while((startPos-1-i)>=0
                    && (startPos+1+i)<A.length()
                    && A.charAt(startPos-1-i)==A.charAt(startPos+1+i)){
                length+=2;
                i++;
            }
        }else{
            while((startPos-i)>=0
                    && (startPos+i+1)<A.length()
                    && A.charAt(startPos-i)==A.charAt(startPos+1+i)){
                length+=2;
                i++;
            }
        }
        return length;
    }
    public String longestPalindrome(String A) {
        if(A==null || A.isEmpty()){
            return A;
        }
        int lps=1;
        String lpsMax=A.substring(0,1);

        for(int i =0;i<A.length();i++){
            int local=(int)Math.max(lpsStr(A, i, true),lpsStr(A, i, false));
            if(local>lps){
                lps=local;
                if(local%2!=0){
                    lpsMax=A.substring(i-(local-1)/2, i+1+(local-1)/2);
                }else{
                    lpsMax=A.substring(i+1-(local)/2, i+1+(local)/2);
                }
            }
        }
        return lpsMax;
    }
}

