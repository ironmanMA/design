package com.ironman.ma.Algorithms.Searching.PainterPartition;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
//        int[] kN = new int[]{1,10};
//        2,5 50

//        int[] kN = new int[]{1000000,1000000};
//        1, 1000000 97003

//        int[] kN = new int[]{640, 435, 647, 352, 8, 90, 960, 329, 859};
//        3,10 17220

//        int[] kN = new int[]{12, 34, 67, 90};
//        2 113

//        int[] kN = new int[]{97, 26, 12, 67, 10, 33, 79, 49, 79, 21, 67, 72, 93, 36, 85, 45, 28, 91, 94, 57, 1, 53, 8, 44, 68, 90, 24};
//        26 97

//        int[] kN = new int[]{73, 58, 30, 72, 44, 78, 23, 9};
//        5 110

//        int[] kN = new int[]{-50, -41, -40, -19, 5, 21, 28};
//        int[] kN2 = new int[]{-50, -21, -10};

//        int[] kN = new int[]{1, 2, 4, 5};
//        int[] kN2 = new int[]{2,3};

       int[] kN = new int[]{0,1,2,3};
        int[] kN2 = new int[]{0,1};
        ArrayList<Integer> kNAr = new ArrayList<Integer>();
        for (int i : kN) {
            kNAr.add(i);
        }
        ArrayList<Integer> kNAr2 = new ArrayList<Integer>();
        for (int i : kN2) {
            kNAr2.add(i);
        }
//        System.out.println(new Solution().paint(1,1000000,kNAr));
//        System.out.println(new Solution().books(kNAr,26));
        System.out.println(new Solution().findMedianSortedArrays(kNAr,kNAr2));
    }

    public int findIndexLessThanK(List<Integer> a, int val){
        int low=0;
        int high=a.size()-1;
        if(val<a.get(0)){
            return -1;
        }
        if(a.get(high)<=val){
            return a.size()-1;
        }
        while(low<=high){
            int mid=low+(high-low)/2;
            int midVal=a.get(mid);
            if(midVal<=val){
                if(val<a.get(mid+1)){
                    return mid;
                }
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return -1;
    }

    public double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {
        if(a==null || a.size()==0){
            if(b.size()%2==0){
                return (b.get(b.size()/2)+b.get((b.size()/2)-1))/2.0;
            }
            return b.get(b.size()/2);
        }
        if(b==null || b.size()==0){
            if(a.size()%2==0){
                return (a.get(a.size()/2)+a.get((a.size()/2)-1))/2.0;
            }
            return a.get(a.size()/2);
        }

        int low=0;
        int high=a.size()-1;

        while(low<=high){
            int mid=low +(high-low)/2;
            int midVal=a.get(mid);

            int aLeft=mid+1;
            int aRight=a.size()-aLeft;

            int j=findIndexLessThanK(b,midVal);

            int bLeft=j+1;
            int bRight=b.size()-bLeft;

            if((aLeft+bLeft)==(aRight+bRight) ||
                    (aLeft+bLeft)==(aRight+bRight)+1){
                if((a.size()+b.size())%2==0){
                    return (a.get(mid)+b.get(j+1))/2.0;
                }else{
                    return midVal;
                }
            }else if((aLeft+bLeft) < (aRight+bRight)){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }

        low=0;
        high=b.size()-1;

        while(low<=high){
            int mid=low +(high-low)/2;
            int midVal=b.get(mid);

            int bLeft=mid+1;
            int bRight=b.size()-bLeft;

            int j=findIndexLessThanK(a,midVal);

            int aLeft=j+1;
            int aRight=a.size()-aLeft;

            if((aLeft+bLeft)==(aRight+bRight) ||
                    (aLeft+bLeft)+1==(aRight+bRight)+1){
                if((a.size()+b.size())%2==0){
                    return (a.get(aRight-1)+b.get(mid))/2.0;
                }else{
                    return midVal;
                }
            }else if((aLeft+bLeft) < (aRight+bRight)){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }

        return -1;
    }

    public int countChild(ArrayList<Integer> books, long pagePerChild){
        long localSum=0;
        int childCount=0;
        for(Integer pageC:books){
            if(pageC>pagePerChild){
                return Integer.MAX_VALUE;
            }
            if(localSum+pageC<=pagePerChild){
                localSum+=pageC;
            }else{
                localSum=pageC;
                childCount++;
            }
        }
        return childCount+1;
    }
    public int books(ArrayList<Integer> books, int children) {

        if(children>books.size()){
            return -1;
        }

        long min=Long.MAX_VALUE;
        long max=Long.MIN_VALUE;
        long sumT=0;
        for(Integer c:books){
            sumT+=c;
            if(c<min){
                min=c;
            }
            if(c>max){
                max=c;
            }
        }

        if(children==books.size()){
            return (int)max;
        }

        long low=min;
        long high=sumT;
        while(low<=high){
            long pagePerChild=low+(high-low)/2;
            int numChild=countChild(books,pagePerChild);
            if(numChild<=children){
                high=pagePerChild-1;
            }else {
                low=pagePerChild+1;
            }
        }
        return (int)low;
    }

    public boolean isPossible(ArrayList<Integer> boards, int painters,long timeTakenPerPerson){
        //prsumably single painting this.
        long localTime=0;
        int localPainters=0;
        int boardstaken=-1;
        for(int i=0;i<boards.size();i++){
            if(boards.get(i)>timeTakenPerPerson){
                return false;
            }
            if(localTime+boards.get(i)<=timeTakenPerPerson){
                localTime+=boards.get(i);
            }else{
                localPainters++;
                localTime=boards.get(i);
            }
        }
        if(localPainters<painters){
            return true;
        }
        return false;
    }

    public int paint(int painters, int timePerUnitBoard, ArrayList<Integer> boards) {

        if(painters>boards.size()){
            painters=boards.size();
        }

        long sum_max=0;
        long min=Integer.MAX_VALUE;
        for(Integer board:boards){
            if(board<min){
                min=board;
            }
            sum_max+=board;
        }
        long start=min;
        long end=  sum_max;
        int modBase=10000003;
        long ans=Integer.MAX_VALUE;


        if(painters==1){
            long longRes=((sum_max%modBase)*(timePerUnitBoard%modBase))%modBase;
            return (int)longRes;
        }

        while(start<=end){
            long mid=start+(end-start)/2;
            if (isPossible(boards, painters, mid)){
                if(mid<ans){
                    ans=mid;
                }
                end=mid-1;
            }else{
                start=mid+1;
            }
        }

        //find max for them min total time.
        return (int)((start%modBase)*(timePerUnitBoard%modBase))%modBase;
    }
}
