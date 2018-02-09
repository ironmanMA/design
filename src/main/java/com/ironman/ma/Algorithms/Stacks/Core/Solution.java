package com.ironman.ma.Algorithms.Stacks.Core;

import java.util.*;

public class Solution {
    public String simplifyPath(String A) {
        if(A==null){
            return null;
        }
        String[] tokens=A.split("/");
        Stack<String> goodStack=new Stack<String>();

        for(String s: tokens){
            if(s.equals(".") ){
                continue;
            }else if(s.equals("..")){
                if(goodStack.size()>0){
                    goodStack.pop();
                }
            }else{
                if(s.length()>0){
                    goodStack.push(s);
                }
            }
        }
        StringBuilder newStr=new StringBuilder();
        while(goodStack.size()>0){
            StringBuilder str=new StringBuilder("/");
            str.append(goodStack.pop());
            newStr.append(str.reverse());
        }
        if(newStr.length()==0){
            newStr.append("/");
        }
        return newStr.reverse().toString();
    }
    public int braces(String A) {
        char[] charArr=A.toCharArray();
        Stack<Character> operatorTokens=new Stack<Character> ();
        Stack<String> operobracketTokens=new Stack<String> ();
        for(char c:charArr){
            if(c=='*' ||c=='/' ||c=='+' ||c=='-' ){
                operatorTokens.push(c);
            }else if(c==')'){
                //operate removals
                int count=0;
                StringBuilder str=new StringBuilder(")");
                if(operobracketTokens.size()>0){
                    while(operobracketTokens.size()>0
                            && !operobracketTokens.peek().equals("(")){
                        count++;
                        str.append(operobracketTokens.pop());
                        if(!operobracketTokens.peek().equals("(")){
                            if(operatorTokens.size()==0){
                                return 1;
                            }
                            str.append(operatorTokens.pop());
                        }
                    }
                    str.append(operobracketTokens.pop());
                }
                if(count<=1){
                    return 1;
                }
                operobracketTokens.push(str.reverse().toString());
            }else {
                operobracketTokens.push(c+"");
            }
        }
        return 0;
    }

    public int largestRectangleArea(ArrayList<Integer> A) {
        ArrayList<Integer> leftMinIndex=new ArrayList<Integer> ();
        ArrayList<Integer> rightMinIndex=new ArrayList<Integer> ();
        Stack<Integer> minIndex=new Stack<Integer> ();
        for(int i=0;i<A.size();i++){
            while(minIndex.size()>0 && A.get(minIndex.peek())>=A.get(i)){
                minIndex.pop();
            }

            if(minIndex.size()==0){
                leftMinIndex.add(-1);
            }else{
                leftMinIndex.add(minIndex.peek());
            }
            minIndex.push(i);
        }
        minIndex=new Stack<Integer> ();
        for(int i=A.size()-1;i>=0;i--){

            while(minIndex.size()>0 && A.get(minIndex.peek())>=A.get(i)){
                minIndex.pop();
            }

            if(minIndex.size()==0){
                rightMinIndex.add(A.size());
            }else{
                rightMinIndex.add(minIndex.peek());
            }
            minIndex.push(i);
        }
        int max=Integer.MIN_VALUE;
        for(int i=0;i<A.size();i++){
            int width=(i-leftMinIndex.get(i)-1)+
                    (rightMinIndex.get(A.size()-i-1)-i-1)+1;
            if(width*A.get(i)>max){
                max=width*A.get(i);
            }
        }
        return max;
    }
    public ArrayList<Integer> slidingMaximum(final List<Integer> A, int w) {
        ArrayList<Integer> maxes=new ArrayList<Integer>();
        Deque<Integer> deque = new ArrayDeque<Integer>();
        int index=0;
        //first w elements
        while(index<w && index<A.size()){
            while(deque.size()>0 && A.get(deque.peekLast())<A.get(index)){
                deque.removeLast();
            }
            deque.addLast(index);
            index++;
        }

        maxes.add(A.get(deque.peekFirst()));

            for(int i=w;i<A.size();i++){
            int leavingIndex=i-w;
            if(deque.peekFirst()==leavingIndex){
                deque.removeFirst();
            }
            int incomingIndex=i;
            while(deque.size()>0 && A.get(deque.peekLast())<A.get(incomingIndex)){
                deque.removeLast();
            }
            deque.addLast(incomingIndex);
            maxes.add(A.get(deque.peekFirst()));
        }
        return maxes;
    }
}
