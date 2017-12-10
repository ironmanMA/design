package com.ironman.ma.DataStructures.Tree.swap.nodes.algo;

/**
 * Created by 127.0.0.1.ma on 23/09/17.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    private static ArrayList<Nodke> List1 = new ArrayList<Nodke>();
    private static ArrayList<Nodke> List2 = new ArrayList<Nodke>();

    private static Nodke swapAll(Nodke root, int depth) {
        List1.add(root);
        int currDepth = 1;
        while (true) {
            if (currDepth % depth == 0) {
                /*
                    pick non empty list and swap everything increment currDepth
                 */
                ArrayList<Nodke> currList = List1;
                ArrayList<Nodke> otherList = List2;
                if (List1.size() == 0) {
                    otherList = List1;
                    currList = List2;
                }
                while (currList.size() > 0) {
                    Nodke currNode = currList.get(0);
                    Nodke tmp = currNode.left;
                    currNode.left = currNode.right;
                    currNode.right = tmp;
                    if (currNode.left != null) {
                        otherList.add(currNode.left);
                    }
                    if (currNode.right != null) {
                        otherList.add(currNode.right);
                    }
                    currList.remove(0);
                }
                currDepth++;
            } else {
                ArrayList<Nodke> currList = List1;
                ArrayList<Nodke> otherList = List2;
                if (List1.size() == 0) {
                    otherList = List1;
                    currList = List2;
                }
                while (currList.size() > 0) {
                    Nodke currNode = currList.get(0);
                    if (currNode.left != null) {
                        otherList.add(currNode.left);
                    }
                    if (currNode.right != null) {
                        otherList.add(currNode.right);
                    }
                    currList.remove(0);
                }
                currDepth++;
            }
            if (List1.size() == 0 && List2.size() == 0)
                break;
        }
        new Solution().inOrder(root);
        return root;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int numNodes = in.nextInt();
        Nodke root = new Nodke();
        root.data = 1;
        ArrayList<Nodke> levelList = new ArrayList<Nodke>();
        levelList.add(root);
        for (int i = 0; i < numNodes; i++) {
            int left = in.nextInt();
            int right = in.nextInt();
            Nodke currNode = levelList.get(0);
            if (left != -1) {
                Nodke Nodke = new Nodke();
                Nodke.data = left;
                levelList.add(Nodke);
                currNode.left = Nodke;
            }
            if (right != -1) {
                Nodke Nodke = new Nodke();
                Nodke.data = right;
                levelList.add(Nodke);
                currNode.right = Nodke;
            }
            levelList.remove(0);
        }
        int numCases = in.nextInt();
        for (int i = 0; i < numCases; i++) {
            int depth = in.nextInt();
            root = swapAll(root, depth);
            System.out.println("");
        }
        in.close();
    }

    private void inOrder(Nodke root) {
        /*
        left, root, right
         */
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    private static class Nodke {
        int data;
        Nodke left;
        Nodke right;
    }
}
