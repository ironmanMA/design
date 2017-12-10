package com.ironman.ma.DataStructures.Heap.jesse.and.cookies;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by 127.0.0.1.ma on 06/11/17.
 * <p>
 * Jesse loves cookies. He wants the sweetness of all his cookies to be greater than value . To do this, Jesse repeatedly mixes two cookies with the least sweetness. He creates a special combined cookie with:
 * <p>
 * sweetness  Least sweet cookie   2nd least sweet cookie).
 * <p>
 * He repeats this procedure until all the cookies in his collection have a sweetness .
 * You are given Jesse's cookies. Print the number of operations required to give the cookies a sweetness . Print  if this isn't possible.
 */
public class Solution {

    public static void reHeapifyDown(ArrayList<Integer> heapArr, int index) {
        int numAtDeleteIndex = heapArr.get(index);
        int numAtLastIndex = heapArr.get(heapArr.size() - 1);
        heapArr.set(index, numAtLastIndex);
        heapArr.remove(heapArr.size() - 1);
        if (numAtDeleteIndex == numAtLastIndex) {
            return;
        }

        int SpecInt = Integer.MAX_VALUE;

        int parentIndex = index, numAtParentIndex = heapArr.get(parentIndex);
        int leftChildIndex = 2 * parentIndex + 1;
        int numAtLeftChildIndex = 0;
        if (leftChildIndex > heapArr.size() - 1)
            numAtLeftChildIndex = SpecInt;
        else
            numAtLeftChildIndex = heapArr.get(leftChildIndex);

        int rightChildIndex = leftChildIndex + 1;
        int numAtRightChildIndex = 0;
        if (rightChildIndex > heapArr.size() - 1)
            numAtRightChildIndex = SpecInt;
        else
            numAtRightChildIndex = heapArr.get(rightChildIndex);

        if (numAtLeftChildIndex == SpecInt && numAtRightChildIndex == SpecInt)
            return;

        while (true) {
            if (numAtParentIndex < numAtLeftChildIndex && numAtParentIndex < numAtRightChildIndex) {
                break;
            }
            int minChildIndex = leftChildIndex, numMinChildIndex = numAtLeftChildIndex;
            if (numAtLeftChildIndex > numAtRightChildIndex) {
                minChildIndex = rightChildIndex;
                numMinChildIndex = numAtRightChildIndex;
            }
            //swap
            heapArr.set(parentIndex, numMinChildIndex);
            heapArr.set(minChildIndex, numAtParentIndex);

            //reassign
            parentIndex = minChildIndex;
            numAtParentIndex = numMinChildIndex;
            leftChildIndex = 2 * parentIndex + 1;
            numAtLeftChildIndex = 0;
            if (leftChildIndex > heapArr.size() - 1)
                numAtLeftChildIndex = SpecInt;
            else
                numAtLeftChildIndex = heapArr.get(leftChildIndex);

            rightChildIndex = leftChildIndex + 1;
            numAtRightChildIndex = 0;
            if (rightChildIndex > heapArr.size() - 1)
                numAtRightChildIndex = SpecInt;
            else
                numAtRightChildIndex = heapArr.get(rightChildIndex);

            if (numAtLeftChildIndex == SpecInt && numAtRightChildIndex == SpecInt)
                return;
        }

    }

    public static void reHeapifyUp(ArrayList<Integer> heapArr, int index) {
        int numAtIndex = heapArr.get(index);
        int parentIndex = (index - 1) / 2;
        int numAtParentIndex = heapArr.get(parentIndex);
        if (index > 1 && index % 2 == 0) {
            parentIndex = (index - 2) / 2;
            numAtParentIndex = heapArr.get(parentIndex);
        }

        if (numAtIndex == numAtParentIndex)
            return;

        while (true) {
            if (numAtParentIndex > numAtIndex) {
                //swap and re-assign
                heapArr.set(index, numAtParentIndex);
                heapArr.set(parentIndex, numAtIndex);
                if (parentIndex == 0)
                    break;
                int oldParentIndex = parentIndex;
                index = oldParentIndex;
                numAtIndex = heapArr.get(parentIndex);
                parentIndex = (oldParentIndex - 1) / 2;
                if (oldParentIndex % 2 == 0) {
                    parentIndex = (oldParentIndex - 2) / 2;
                }
                numAtParentIndex = heapArr.get(parentIndex);
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numCases = input.nextInt();
        int threshold = input.nextInt();

        ArrayList<Integer> heapArr = new ArrayList<Integer>();

        for (int i = 0; i < numCases; i++) {
            int toAdd = input.nextInt();
            heapArr.add(toAdd);
            reHeapifyUp(heapArr, heapArr.size() - 1);
        }

        int numTimes = 0;
        while (heapArr.get(0) < threshold) {
            int min_most = heapArr.get(0);
            reHeapifyDown(heapArr, 0);
            int min_next = heapArr.get(0);
            reHeapifyDown(heapArr, 0);
            heapArr.add(min_most + 2 * min_next);
            reHeapifyUp(heapArr, heapArr.size() - 1);
            numTimes++;
        }
        System.out.println(numTimes);

        input.close();
    }
}
