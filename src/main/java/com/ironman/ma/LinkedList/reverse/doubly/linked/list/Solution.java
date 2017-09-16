package com.ironman.ma.LinkedList.reverse.doubly.linked.list;

import com.ironman.ma.LinkedList.Node;

/**
 * Created by 127.0.0.1.ma on 16/09/17.
 */
public class Solution {
    public static void main(String[] args) {
        Node a = new Node(2);
        Node b = new Node(4);
        Node c = new Node(6);
        a.next = b;
        b.prev = a;
        b.next = c;
        c.prev = b;
        Node head = new Solution().Reverse(a);
        Node.printList(head);
    }

    Node Reverse(Node head) {
        Node currNode = head;
        Node futureNode = currNode.next;
        Node prevNode = currNode.prev;
        while (futureNode != null) {
            currNode.next = prevNode;
            currNode.prev = futureNode;
            prevNode = currNode;
            currNode = futureNode;
            futureNode = futureNode.next;
        }
        currNode.next = prevNode;
        currNode.prev = null;
        return currNode;
    }
}
