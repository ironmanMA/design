package com.ironman.ma.Algorithms.Strings.Min.Length.For.Palidrome;

public class Solution {
    public long palindromeLength(String A, int startPos, boolean isOdd){
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
            while((startPos-1-i)>=0
                    && (startPos+i)<A.length()
                    && A.charAt(startPos-1-i)==A.charAt(startPos+i)){
                length+=2;
                i++;
            }
        }
        return length;
    }
    public long palindromeLength(String A, int startPos){
        long sub= Math.max(1, palindromeLength(A,startPos,true));
        return Math.max(sub, palindromeLength(A,startPos,false));
    }

    public long solve(String A) {
        if(A==null || A.length()<=1){
            return 0;
        }
        long longestPandromeLength=1;
        for(int i=0;i<A.length()-1;){
            long local=palindromeLength(A, i);
            if(i-(local)/2==0 && local>longestPandromeLength){
                longestPandromeLength=local;
            }
            if(local==1){
                i+=local;
            }else{
                if(local%2==0){
                    i+=(local+1)/2;
                }else{
                    i+=(local-1)/2;
                }
            }
        }
        return A.length()-longestPandromeLength;
    }
}

