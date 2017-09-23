package com.ironman.ma.Tree;

import java.util.ArrayList;

/**
 * Created by 127.0.0.1.ma on 21/09/17.
 */
public class Node {
    public int data;
    public int frequency;
    public char charData;
    public Node left;
    public Node right;

    public Node(int data) {
        this.data = data;
    }

    public Node() {
    }

    static int height(Node root) {
        // Write your code here.
        if (root != null) {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        /*
        Node root = new Node(1);
        Node root1 = new Node(2);
        Node root2 = new Node(3);
        Node root3 = new Node(4);
        Node root4 = new Node(5);
        Node root5 = new Node(6);
        root.right = root1;
        root1.right = root4;
        root4.left = root2;
        root4.right = root5;
        root2.right = root3;

        new Node().topView(root);
        System.out.println(" ");
        new Node().levelOrder(root);
        System.out.println(" ");
        System.out.println("ABCDEF".substring(0, 1));
        */
        Node root = new Node(1);
        root.charData = '\0';
        Node root1 = new Node(2);
        root1.charData = 'A';
        Node root2 = new Node(3);
        root2.charData = '\0';
        Node root3 = new Node(4);
        root3.charData = 'B';
        Node root4 = new Node(4);
        root4.charData = 'C';

        root2.left = root3;
        root2.right = root4;

        root.left = root2;
        root.right = root1;

        new Node().decode("1001011", root);

    }

    static Node Insert(Node root, int value) {
        if (root == null) {
//            Node newRoot = new Node(value);
            Node newRoot = new Node();
            newRoot.data = value;
            return newRoot;
        } else {
            if (value < root.data) {
                root.left = Insert(root.left, value);
            } else {
                root.right = Insert(root.right, value);
            }
            return root;
        }
    }

    static Node lca(Node root, int v1, int v2) {
        /*
        only works if its gauranteed if v1,v2 are present in the tree
         */
        if (root != null) {
            if (root.data == v1 || root.data == v2) {
                return root;
            } else {
                Node lcaLeft = lca(root.left, v1, v2);
                Node lcaRight = lca(root.right, v1, v2);
                if (lcaLeft != null && lcaRight != null) {
                    return root;
                } else if (lcaLeft != null) {
                    return lcaLeft;
                } else if (lcaRight != null) {
                    return lcaRight;
                } else {
                    return null;
                }
            }
        }
        return null;

    }

    void preOrder(Node root) {
        /*
        root,left, right
         */
        if (root != null) {
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    void postOrder(Node root) {
        /*
        left, right, root
         */
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }
    }

    void inOrder(Node root) {
        /*
        left, root, right
         */
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    void levelOrder(Node root) {
        ArrayList<Node> levelArray = new ArrayList<Node>();
        if (root != null) {
            levelArray.add(root);
        }
        while (levelArray.size() > 0) {
            Node currNode = levelArray.get(0);
            System.out.print(currNode.data + " ");
            if (currNode.left != null) {
                levelArray.add(currNode.left);
            }
            if (currNode.right != null) {
                levelArray.add(currNode.right);
            }
            levelArray.remove(0);
        }

    }

    void topView(Node root) {
        /*
        level order traversal, lE,rE
         */
        ArrayList<Node> levelArray = new ArrayList<Node>();
        int leftExtreme = 0, rightExtreme = 0;
        if (root != null) {
            levelArray.add(root);
            Node channel = new Node(0);
//            channel.data = 0;
            levelArray.add(channel);
            System.out.print(root.data + " ");
            while (levelArray.size() > 0) {
                Node currNode = levelArray.get(0);
                Node currNodeChannel = levelArray.get(1);
                if (currNodeChannel.data < leftExtreme || currNodeChannel.data > rightExtreme) {
                    System.out.print(currNode.data + " ");
                    leftExtreme = Math.min(leftExtreme, currNodeChannel.data);
                    rightExtreme = Math.max(rightExtreme, currNodeChannel.data);
                }
                /*
                add child nodes at the end
                 */
                if (currNode.left != null) {
                    levelArray.add(currNode.left);
                    Node channelLeft = new Node(currNodeChannel.data - 1);
//                    channelLeft.data = currNodeChannel.data - 1;
                    levelArray.add(channelLeft);
                }

                if (currNode.right != null) {
                    levelArray.add(currNode.right);
                    Node channelRight = new Node(currNodeChannel.data + 1);
//                    channelRight.data = currNodeChannel.data + 1;
                    levelArray.add(channelRight);
                }

                levelArray.remove(0);
                levelArray.remove(0);
            }

        }
    }

    void decode(String S, Node root) {
        String decodedString = "";
        Node currNode = root;
//        String[] array = S.split("");
        for (int index = 0; index < S.length(); index++) {
            /*
                check if curr String Exits
             */
            if (S.charAt(index) == '1') {
                /*
                    go right
                 */
                if (currNode.right != null) {
                    currNode = currNode.right;
                } else {
                    decodedString += currNode.charData;
                    currNode = root;
                    index = index - 1;
                }
            } else {
                /*
                    go left
                 */
                if (currNode.left != null) {
                    currNode = currNode.left;
                } else {
                    decodedString += currNode.charData;
                    currNode = root;
                    index = index - 1;
                }
            }
        }
        decodedString += currNode.charData;
        System.out.println(decodedString);
    }
}
