package com.ironman.ma.LinkedList;

/**
 * Created by 127.0.0.1.ma on 15/09/17.
 */
public class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
    }

    public static void printList(Node node) {
        if (node != null) {
            System.out.print(node.data + ", ");
            printList(node.next);
        } else {
            System.out.println("");
        }
    }
}
