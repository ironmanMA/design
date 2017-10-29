package com.ironman.ma.Heap.qheap1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by 127.0.0.1.ma on 29/10/17.
 */
public class Solution {

    public static void reHeapifyDown(ArrayList<Integer> heapArr, int index) {
        int numAtDeleteIndex = heapArr.get(index);
        int numAtLastIndex = heapArr.get(heapArr.size() - 1);
        if (numAtDeleteIndex == numAtLastIndex) {
            heapArr.remove(heapArr.size() - 1);
            return;
        }
        heapArr.set(index, numAtLastIndex);
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

        ArrayList<Integer> heapArr = new ArrayList<Integer>();

        for (int i = 0; i < numCases; i++) {
            int queryType = input.nextInt();
            switch (queryType) {
                case 1:
                    int toAdd = input.nextInt();
                    heapArr.add(toAdd);
                    reHeapifyUp(heapArr, heapArr.size() - 1);
                    break;
                case 2:
                    int toRem = input.nextInt();
                    for (int j = 0; j < heapArr.size(); j++) {
                        if (toRem == heapArr.get(j)) {
                            reHeapifyDown(heapArr, j);
                            break;
                        }
                    }
                    break;
                case 3:
                    // print min
                    System.out.println(heapArr.get(0));
                    break;
            }
        }
        input.close();
    }
}
