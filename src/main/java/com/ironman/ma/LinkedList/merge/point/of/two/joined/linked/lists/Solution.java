package com.ironman.ma.LinkedList.merge.point.of.two.joined.linked.lists;

import com.ironman.ma.LinkedList.Node;

/**
 * Created by 127.0.0.1.ma on 16/09/17.
 */
public class Solution {
    public static void main(String[] args) {
        Node a1 = new Node(1);
        Node b1 = new Node(1);
        Node c1 = new Node(1);
        a1.next = b1;
        b1.next = c1;

        Node a2 = new Node(1);
        Node b2 = new Node(1);
        a2.next = b2;

        Node d = new Node(2);
        c1.next = d;
        b2.next = d;

        System.out.println(new Solution().FindMergeNode(a1, a2));

    }

    int getLinkedListLength(Node node) {
        int length = 0;
        while (true) {
            if (node == null)
                return length;
            else {
                node = node.next;
                length++;
            }
        }
    }

    int FindMergeNode(Node headA, Node headB) {
        // Complete this function
        // Do not write the main method.
        int lenA = getLinkedListLength(headA), lenB = getLinkedListLength(headB);
        Node longer = headA, shorter = headB;
        int shortLen = lenB;
        if (lenB > lenA) {
            longer = headB;
            shorter = headA;
            shortLen = lenA;
        }
        for (int i = 0; i < Math.abs(lenA - lenB); i++) {
            longer = longer.next;
        }
        for (int i = 0; i < shortLen; i++) {
            if (shorter.next == longer.next) {
                return shorter.next.data;
            } else {
                shorter = shorter.next;
                longer = longer.next;
            }
        }
        return shorter.next.data;
    }
}
