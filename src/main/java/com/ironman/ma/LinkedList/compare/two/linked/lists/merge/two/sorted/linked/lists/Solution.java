package com.ironman.ma.LinkedList.compare.two.linked.lists.merge.two.sorted.linked.lists;

import com.ironman.ma.LinkedList.Node;

/**
 * Created by 127.0.0.1.ma on 15/09/17.
 */
public class Solution {

    public static void main(String[] args) {
        Node a1 = new Node(1);
        Node b1 = new Node(7);
        Node c1 = new Node(11);
        b1.next = c1;
        a1.next = b1;


        Node a2 = new Node(2);
        Node b2 = new Node(3);
        Node c2 = new Node(4);
        b2.next = c2;
        a2.next = b2;

        Node finalList = new Solution().mergeLists(a1, a2);
        Node.printList(finalList);
    }

    Node mergeLists(Node headA, Node headB) {
        // This is a "method-only" submission.
        // You only need to complete this method
        if (headA == null)
            return headB;
        if (headB == null)
            return headA;
        Node newHead = headA,
                nodeHigh = headA, nodeLow = headB;

        if (headA.data > headB.data) {
            newHead = headB;
            nodeHigh = headB;
            nodeLow = headA;
        }
        while (!(nodeHigh == null && nodeLow == null)) {
            if (nodeHigh != null) {
                if (nodeLow != null) {
                    if (nodeHigh.next == null) {
                        nodeHigh.next = nodeLow;
                        return newHead;
                    } else {
                        if (nodeHigh.next.data > nodeLow.data) {
                            //insert nodeLow here
                            Node tmpHigh = nodeHigh.next;
                            Node tmpLow = nodeLow.next;
                            nodeHigh.next = nodeLow;
                            nodeLow.next = tmpHigh;
                            nodeLow = tmpLow;
                        }
                        nodeHigh = nodeHigh.next;
                    }
                } else {
                    return newHead;
                }
            }
        }
        return newHead;
    }
}
