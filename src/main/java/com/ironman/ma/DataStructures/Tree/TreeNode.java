package com.ironman.ma.DataStructures.Tree;

import java.util.ArrayList;

/**
 * Created by 127.0.0.1.ma on 21/09/17.
 */
public class TreeNode {
    public int data;
    public int val;
    public int ht;
    public int frequency;
    public char charData;
    static int found = 0;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int data) {
        this.data = data;
    }

//    @Override
//    public String toString() {
//        inOrder(this);
//        return "TreeNode{}";
//    }

    public TreeNode() {
    }

    static int height(TreeNode root) {
        // Write your code here.
        if (root != null) {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        /*
        TreeNode root = new TreeNode(1);
        TreeNode root1 = new TreeNode(2);
        TreeNode root2 = new TreeNode(3);
        TreeNode root3 = new TreeNode(4);
        TreeNode root4 = new TreeNode(5);
        TreeNode root5 = new TreeNode(6);
        root.right = root1;
        root1.right = root4;
        root4.left = root2;
        root4.right = root5;
        root2.right = root3;

        new TreeNode().topView(root);
        System.out.println(" ");
        new TreeNode().levelOrder(root);
        System.out.println(" ");
        System.out.println("ABCDEF".substring(0, 1));
        */
        TreeNode root = new TreeNode(1);
        root.charData = '\0';
        TreeNode root1 = new TreeNode(2);
        root1.charData = 'A';
        TreeNode root2 = new TreeNode(3);
        root2.charData = '\0';
        TreeNode root3 = new TreeNode(4);
        root3.charData = 'B';
        TreeNode root4 = new TreeNode(4);
        root4.charData = 'C';

        root2.left = root3;
        root2.right = root4;

        root.left = root2;
        root.right = root1;

        new TreeNode().decode("1001011", root);

    }

    static TreeNode Insert(TreeNode root, int value) {
        if (root == null) {
//            TreeNode newRoot = new TreeNode(value);
            TreeNode newRoot = new TreeNode();
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

    static TreeNode lca(TreeNode root, int v1, int v2) {
        /*
        only works if its guaranteed if v1,v2 are present in the tree
         */
        if (root != null) {
            if (root.data == v1 || root.data == v2) {
                return root;
            } else {
                TreeNode lcaLeft = lca(root.left, v1, v2);
                TreeNode lcaRight = lca(root.right, v1, v2);
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

    public TreeNode findLCA(TreeNode root, TreeNode a, TreeNode b) {

        if (root == null || a == null || b == null) {
            return null;
        }

        if (root.data == a.data || root.data == b.data) {
            found++;
        }

        if (found == 2)
            return root;

        TreeNode lst = findLCA(root.left, a, b);
        if (lst == null) {
            System.out.println("LST null");
        } else {
            System.out.println("LST " + lst.data);
        }
        if (found == 2) {
            if (root.data == a.data || root.data == b.data) {
                return root;
            }
            return lst;
        }
        TreeNode rst = findLCA(root.right, a, b);
        if (rst == null) {
            System.out.println("RST null");
        } else {
            System.out.println("RST " + rst.data);
        }
        if (found == 2) {
            if ((root.data == a.data || root.data == b.data) || lst != null) {
                return root;
            }
            return rst;
        }

        if (root.data == a.data || root.data == b.data) {
            return root;
        }

        if (lst != null) {
            return lst;
        }

        if (rst != null) {
            return rst;
        }

        return null;

    }

    void preOrder(TreeNode root) {
        /*
        root,left, right
         */
        if (root != null) {
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    void postOrder(TreeNode root) {
        /*
        left, right, root
         */
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }
    }

    void inOrder(TreeNode root) {
        /*
        left, root, right
         */
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    void levelOrder(TreeNode root) {
        ArrayList<TreeNode> levelArray = new ArrayList<TreeNode>();
        if (root != null) {
            levelArray.add(root);
        }
        while (levelArray.size() > 0) {
            TreeNode currTreeNode = levelArray.get(0);
            System.out.print(currTreeNode.data + " ");
            if (currTreeNode.left != null) {
                levelArray.add(currTreeNode.left);
            }
            if (currTreeNode.right != null) {
                levelArray.add(currTreeNode.right);
            }
            levelArray.remove(0);
        }

    }

    void topView(TreeNode root) {
        /*
        level order traversal, lE,rE
         */
        ArrayList<TreeNode> levelArray = new ArrayList<TreeNode>();
        int leftExtreme = 0, rightExtreme = 0;
        if (root != null) {
            levelArray.add(root);
            TreeNode channel = new TreeNode(0);
//            channel.data = 0;
            levelArray.add(channel);
            System.out.print(root.data + " ");
            while (levelArray.size() > 0) {
                TreeNode currTreeNode = levelArray.get(0);
                TreeNode currTreeNodeChannel = levelArray.get(1);
                if (currTreeNodeChannel.data < leftExtreme || currTreeNodeChannel.data > rightExtreme) {
                    System.out.print(currTreeNode.data + " ");
                    leftExtreme = Math.min(leftExtreme, currTreeNodeChannel.data);
                    rightExtreme = Math.max(rightExtreme, currTreeNodeChannel.data);
                }
                /*
                add child nodes at the end
                 */
                if (currTreeNode.left != null) {
                    levelArray.add(currTreeNode.left);
                    TreeNode channelLeft = new TreeNode(currTreeNodeChannel.data - 1);
//                    channelLeft.data = currTreeNodeChannel.data - 1;
                    levelArray.add(channelLeft);
                }

                if (currTreeNode.right != null) {
                    levelArray.add(currTreeNode.right);
                    TreeNode channelRight = new TreeNode(currTreeNodeChannel.data + 1);
//                    channelRight.data = currTreeNodeChannel.data + 1;
                    levelArray.add(channelRight);
                }

                levelArray.remove(0);
                levelArray.remove(0);
            }

        }
    }

    void decode(String S, TreeNode root) {
        String decodedString = "";
        TreeNode currTreeNode = root;
//        String[] array = S.split("");
        for (int index = 0; index < S.length(); index++) {
            /*
                check if curr String Exits
             */
            if (S.charAt(index) == '1') {
                /*
                    go right
                 */
                if (currTreeNode.right != null) {
                    currTreeNode = currTreeNode.right;
                } else {
                    decodedString += currTreeNode.charData;
                    currTreeNode = root;
                    index = index - 1;
                }
            } else {
                /*
                    go left
                 */
                if (currTreeNode.left != null) {
                    currTreeNode = currTreeNode.left;
                } else {
                    decodedString += currTreeNode.charData;
                    currTreeNode = root;
                    index = index - 1;
                }
            }
        }
        decodedString += currTreeNode.charData;
        System.out.println(decodedString);
    }

    boolean checkSubTree(TreeNode treeNode, int min, int max) {
        if (treeNode == null)
            return true;
        if (treeNode.data <= min || treeNode.data >= max)
            return false;
        else {
            if (checkSubTree(treeNode.left, min, treeNode.data))
                return checkSubTree(treeNode.right, treeNode.data, max);
            else
                return false;
        }
    }

    boolean checkBST(TreeNode root) {
        if (root != null) {
            if (checkSubTree(root.left, Integer.MIN_VALUE, root.data))
                return checkSubTree(root.right, root.data, Integer.MAX_VALUE);
            else
                return false;
        }
        return false;
    }

}
