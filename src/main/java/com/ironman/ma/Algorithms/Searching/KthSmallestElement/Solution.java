package com.ironman.ma.Algorithms.Searching.KthSmallestElement;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    int[] list1;
    int[] list2;

    public Solution(int[] list1, int[] list2) {
        this.list1 = list1;
        this.list2 = list2;
    }

    public int solve(int k) {
        int ans = -1;

        int[] smallList = list1;
        int[] bigList = list2;
        if (bigList.length < smallList.length) {
            smallList = list2;
            bigList = list1;
        }

        if (smallList.length == 0) {
            if (bigList.length % 2 == 0) {
                return (bigList[(bigList.length - 1) / 2] + bigList[bigList.length / 2]) / 2;
            } else {
                return bigList[bigList.length / 2];
            }
        }
        int smallListStart = 0;
        int smallListEnd = smallList.length;

        while (smallListStart <= smallListEnd) {
            int smallPart = smallListStart + (smallListEnd - smallListStart) / 2;
            int bigPart = k - smallPart;

            int smL = Integer.MIN_VALUE;
            int smR = Integer.MAX_VALUE;

            int bgL = Integer.MIN_VALUE;
            int bgR = Integer.MAX_VALUE;


            if (smallPart != 0) {
                smL = smallList[smallPart - 1];
            }
            if (smallPart != smallList.length) {
                smR = smallList[smallPart];
            }
            if (bigPart != 0) {
                bgL = bigList[bigPart - 1];
            }
            if (bigPart != bigList.length) {
                bgR = bigList[bigPart];
            }

            if (smL <= bgR
                    && bgL <= smR) {

                if (k == (smallList.length + bigList.length + 1) / 2) {
                    //median
                    if ((smallList.length + bigList.length) % 2 != 0) {
                        //odd
                        return Math.max(smL, bgL);
                    } else {
                        return (Math.max(smL, bgL) + Math.min(smR, bgR)) / 2;
                    }
                }

                return Math.max(smL, bgL);
            } else if (smL > bgR) {
                //move left
                smallListEnd = smallPart - 1;
            } else if (bgL > smR) {
                smallListStart = smallPart + 1;
            }
        }
        return ans;
    }

    public double solveArr(ArrayList<Integer> list1, ArrayList<Integer> list2, int k) {

        int ans = -1;
        ArrayList<Integer> smallList = list1;
        ArrayList<Integer> bigList = list2;
        if (bigList.size() < smallList.size()) {
            smallList = list2;
            bigList = list1;
        }

        if (smallList.size() == 1 && bigList.size()==1){
            return (smallList.get(0)+bigList.get(0))/2;
        }

        if (smallList.size() == 0) {
            if (bigList.size() % 2 == 0) {
                return ( bigList.get((bigList.size() - 1) / 2) + bigList.get(bigList.size() / 2)) / 2;
            } else {
                return bigList.get(bigList.size() / 2);
            }
        }

        int smallListStart = 0;
        int smallListEnd = smallList.size();

        while (smallListStart <= smallListEnd) {
            int smallPart = smallListStart + (smallListEnd - smallListStart) / 2;
            int bigPart = k - smallPart;

            int smL = Integer.MIN_VALUE;
            int smR = Integer.MAX_VALUE;

            int bgL = Integer.MIN_VALUE;
            int bgR = Integer.MAX_VALUE;


            if (smallPart != 0) {
                smL = smallList.get(smallPart - 1);
            }
            if (smallPart != smallList.size()) {
                smR = smallList.get(smallPart);
            }
            if (bigPart != 0) {
                bgL = bigList.get(bigPart - 1);
            }
            if (bigPart != bigList.size()) {
                bgR = bigList.get(bigPart);
            }

            if (smL <= bgR
                    && bgL <= smR) {

                if (k == (smallList.size() + bigList.size() + 1) / 2) {
                    //median
                    if ((smallList.size() + bigList.size()) % 2 != 0) {
                        //odd
                        return Math.max(smL, bgL);
                    } else {
                        return (Math.max(smL, bgL) + Math.min(smR, smR)) / 2.0;
                    }
                }

                return Math.max(smL, bgL);
            } else if (smL > bgR) {
                //move left
                smallListEnd = smallPart - 1;
            } else if (bgL > smR) {
                smallListStart = smallPart + 1;
            }
        }
        return ans;
    }
}
