package com.ironman.ma.DataStructures.LinkedList;

/**
 * Created by 127.0.0.1.ma on 20/08/17.
 */
public class REMDUPLNK {
    public static ListNode deleteDuplicates(ListNode a) {
        ListNode iter = a;
        while (iter!=null && iter.next != null) {
            if (iter.val == iter.next.val) {
                iter.next = iter.next.next;
            }
            iter=iter.next;
        }
        return a;
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(1);
        ListNode a3 = new ListNode(2);
        ListNode a4 = new ListNode(3);
        ListNode a5 = new ListNode(3);

        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;

        ListNode ans = deleteDuplicates(a1);
        System.out.println(ans.val);
    }

    /*
     * Definition for singly-linked list.
     */
    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
