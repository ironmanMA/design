package com.ironman.ma.Algorithms.Strings.Max.Length.For.Palidrome;

import junit.framework.TestCase;

public class SolutionTest extends TestCase {

    public void testLongestPalindrome() {
//        System.out.println(new Solution().longestPalindrome("caaccaccaac"));
//
//        System.out.println(new Solution().longestPalindrome("abb"));
//        System.out.println(new Solution().longestPalindrome("aaabb"));
//        System.out.println(new Solution().longestPalindrome("aaaaa"));
//        System.out.println(new Solution().longestPalindrome("aaabaa"));
//        System.out.println(new Solution().longestPalindrome("caccbcbaabacabaccacaaccaccaac"));
//        System.out.println(new Solution().longestPalindrome("caaccaccaaccbb"));
//        System.out.println(new Solution().longestPalindrome("caccbcbaabacabaccacaaccaccaaccbbcbcbbaacabccbcccbbacbbacbccaccaacaccbbcc"));
//        System.out.println(new Solution().longestPalindrome("bbacaccaabcbaccbcacacabcbcbbbcbbbccabcbccacbacbbaacbbbcbbaabbbcabcabccaacbcccaabaccacabaccabbcacbc"));
//        System.out.println(new Solution2().compareVersion("13.0","13.0.8"));
//        System.out.println(new Solution2().compareVersion("4444444444444444","4444444444"));
//        System.out.println(new Solution2().compareVersion("13","13.0.0"));
//        System.out.println(new Solution2().compareVersion("13","9.0.0"));
//        System.out.println(new Solution2().compareVersion("000001","10"));
//        System.out.println(new Solution2().compareVersion("4444371174137455","5.168"));
//        System.out.println(new Solution2().compareVersion("4","4.168"));
        System.out.println(new Solution2().compareVersion("9","6.61154768574.1982"));
    }
    public class Solution2 {

        public int compareVersion(String A, String B) {
            String[] Aarr=A.split("\\.");
            String[] Barr=B.split("\\.");
            int maxL=Math.max(Barr.length,Aarr.length);
            int res=0;
            for(int i=0;i<maxL;i++){
                String[] Anum=new String[]{"0"};
                String[] Bnum=new String[]{"0"};
                if(i<Aarr.length){
                    int ind=0;
                    while(ind<Aarr[i].length()){
                        if(Aarr[i].charAt(ind)!='0'){
                            break;
                        }else{
                            ind++;
                        }
                    }
                    Anum=Aarr[i].substring(ind).split("");
                    if(Aarr[i].substring(ind).length()==0){
                        Anum=new String[]{"0"};
                    }
                }
                if(i<Barr.length){
                    int ind=0;
                    while(ind<Barr[i].length()){
                        if(Barr[i].charAt(ind)!='0'){
                            break;
                        }else{
                            ind++;
                        }
                    }
                    Bnum=Barr[i].substring(ind).split("");
                    if(Barr[i].substring(ind).length()==0){
                        Bnum=new String[]{"0"};
                    }
                }


                for(int j=0;j<Math.max(Bnum.length,Anum.length) && i!=0;j++){
                    int Aint=-1;int Bint=-1;
                    if(j<Anum.length){
                        Aint=Integer.parseInt(Anum[j]);
                    }
                    if(j<Bnum.length){
                        Bint=Integer.parseInt(Bnum[j]);
                    }
                    if(Aint>Bint){
                        return 1;
                    }else if(Aint<Bint){
                        return -1;
                    }
                }

                for(int j=0;j<Math.max(Bnum.length,Anum.length) && i==0;j++){
                    if(Anum.length>Bnum.length){
                        return 1;
                    }else if(Anum.length<Bnum.length){
                        return -1;
                    }else{
                        int Aint=-1;int Bint=-1;
                        if(j<Anum.length){
                            Aint=Integer.parseInt(Anum[j]);
                        }
                        if(j<Bnum.length){
                            Bint=Integer.parseInt(Bnum[j]);
                        }
                        if(Aint>Bint){
                            return 1;
                        }else if(Aint<Bint){
                            return -1;
                        }
                    }
                }
            }
            return res;
        }
    }

}