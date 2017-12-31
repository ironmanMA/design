package com.ironman.ma.DataStructures.Tree;

import junit.framework.TestCase;

public class TreeNodeTest extends TestCase {

    public void testFindLCA() {
        TreeNode k = new TreeNode();

        TreeNode root = new TreeNode();
        root.data = 6;
        TreeNode rl = new TreeNode(4);
        TreeNode rr = new TreeNode(8);

        TreeNode rll = new TreeNode(3);
        TreeNode rlr = new TreeNode(5);

        TreeNode rrr = new TreeNode(9);

        TreeNode rlll = new TreeNode(2);

        rll.left = rlll;

        rl.left = rll;
        rl.right = rlr;
        rr.right = rrr;
        root.left = rl;
        root.right = rr;

        TreeNode lca = k.findLCA(root, new TreeNode(3), new TreeNode(8));
        if (lca.data == 6) {
            assert true;
        } else {
            assert false;
        }

    }
}