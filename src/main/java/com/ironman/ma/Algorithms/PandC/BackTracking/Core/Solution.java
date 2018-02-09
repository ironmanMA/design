package com.ironman.ma.Algorithms.PandC.BackTracking.Core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Solution {
    public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> A, int B) {
        HashSet<Integer> uniq = new HashSet<Integer>(A);
        A=new ArrayList<Integer>(uniq);
        Collections.sort(A);
        return combinationSubSum(A,0,B);
    }
    public ArrayList<ArrayList<Integer>> combinationSubSum(ArrayList<Integer> list,int start,
                                                           int targetSum) {
        ArrayList<ArrayList<Integer>> res=new ArrayList<ArrayList<Integer>>();
        if(list.size()==0 || targetSum<list.get(start)){
            return res;
        }

        for(int i=start;i<list.size();i++){
            int num=list.get(i);
            if(targetSum==num){
                ArrayList<Integer> subList=new ArrayList<Integer>();
                subList.add(0,list.get(i));
                res.add(subList);
            }else if(targetSum>num){
                ArrayList<ArrayList<Integer>> subResNC=combinationSubSum(list,i,targetSum-num);
                for(ArrayList<Integer> subList:subResNC){
                    subList.add(0,list.get(i));
                    res.add(subList);
                }
            }
        }
        return res;
    }

    public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> a) {

        Collections.sort(a);
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> temp = new ArrayList<Integer>();

        res.add(temp);
        createSubsets(res, temp, a, 0);

        return res;

    }

    public void createSubsets(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> temp, ArrayList<Integer> a, int index)
    {
        for(int i = index;i < a.size(); i++)
        {
            if( i > index && a.get(i).equals(a.get(i - 1)))
            {
                continue;
            }
            temp.add(a.get(i));
            res.add(new ArrayList<Integer>(temp));
            createSubsets(res, temp, a, i+1);
            temp.remove(temp.size() - 1);
        }
    }

}
