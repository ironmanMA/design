package com.ironman.ma.DataStructures.LinkedList.print.reverse;

import com.ironman.ma.DataStructures.LinkedList.Node;

/**
 * Created by 127.0.0.1.ma on 15/09/17.
 */
public class Solution {


    public static void main(String[] args) {
        Node a = new Node(3);
        Node b = new Node(213);
        Node c = new Node(43);
        Node d = new Node(83);
        new Solution().ReversePrint(null);
//        new Solution().ReversePrint(a);
        a.next = b;
//        new Solution().ReversePrint(a);
        b.next = c;
        c.next = d;
        new Solution().ReversePrint(a);
    }

    Node Reverse(Node head) {
        // This is a "method-only" submission.
        // You only need to complete this method.
//        System.out.println("start ");
        if (head == null) {
            return null;
        }
        Node past = null;
        Node curr = head;
        Node future = curr.next;
        while (future != null) {
            curr.next = past;
            past = curr;
            curr = future;
            future = curr.next;
        }
        if (curr != null) {
            curr.next = past;
        }
        return curr;

    }

    void ReversePrint(Node head) {
        // This is a "method-only" submission.
        // You only need to complete this method.
        Node curr = Reverse(head);
        while (curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }

    }
}
