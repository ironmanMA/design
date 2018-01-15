//package com.ironman.ma.DataStructures.Tree.Distance.Between.Nodes;
//
//import com.ironman.ma.DataStructures.Tree.TreeNode;
//import junit.framework.TestCase;
//
//public class SolutionTest extends TestCase {
//
//    public void testGetNodeDistance() {
//        TreeNode root = new TreeNode(1);
//
//        TreeNode rl = new TreeNode(2);
//        root.left = rl;
//        TreeNode rr = new TreeNode(3);
//        root.right = rr;
//
//        TreeNode rll = new TreeNode(6);
//        rl.left = rll;
//        TreeNode rlr = new TreeNode(4);
//        rl.right = rlr;
//
//        TreeNode rrl = new TreeNode(8);
//        rr.left = rrl;
//        TreeNode rrr = new TreeNode(5);
//        rr.right = rrr;
//
////        TreeNode rlll=new TreeNode(1);
////        TreeNode rllr=new TreeNode(1);
////
////        TreeNode rlrl=new TreeNode(1);
////        TreeNode rlrr=new TreeNode(1);
//
//        int res = Solution.getNodeDistance(root, 8, 1);
//        System.out.println(res);
//        if (res == 2) {
//            assert true;
//        } else {
//            assert false;
//        }
//
//    }
//}