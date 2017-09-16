package com.ironman.ma.LinkedList.compare.two.linked.lists;

import com.ironman.ma.LinkedList.Node;

/**
 * Created by 127.0.0.1.ma on 15/09/17.
 */
public class Solution {
    int CompareLists(Node headA, Node headB) {
        // This is a "method-only" submission.
        // You only need to complete this method
        Node currA = headA, currB = headB;
        while (true) {
            if (currA == null & currB != null) {
                return 0;
            } else if (currA != null & currB == null) {
                return 0;
            } else if (currA == null & currB == null) {
                return 1;
            } else {
                if (currA.data == currB.data) {
                    currA = currA.next;
                    currB = currB.next;
                } else {
                    return 0;
                }
            }
        }
    }
}
