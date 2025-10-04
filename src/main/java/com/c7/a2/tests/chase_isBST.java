package com.c7.a2.tests;

public class chase_isBST {  // Chase - Nitin - Amit - -7/24/24

    public static void main(String[] args) {
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(10);
        root.right = new TreeNode(30);
        // 20 val below will make BST false as 20 min allowed on left. 19 val here will make it true
        root.left.right = new TreeNode(25);
        System.out.println(isValidBST(root));

        TestChase tc = new TestChase(); // my solution, pseudocode pretty close
        // tc.runTest();
    }

    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }

    private static boolean isValidBSTHelper(TreeNode node, Integer min, Integer max) {
        // Base case: an empty tree is a valid BST
        if (node == null) {
            return true;
        }

        // Check current node - its value must be greater than min and less than max
        if ((min != null && node.val <= min) || (max != null && node.val >= max)) {
            return false;
        }

        // Recursively validate the left and right subtrees
        return isValidBSTHelper(node.left, min, node.val) &&
                isValidBSTHelper(node.right, node.val, max);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    static class TestChase { // my solution, quite close - Chase - Nitin - Amit -
        void runTest(){
            TreeNode1 root = new TreeNode1(20);
            Integer right = null;
            Integer left = null;
            System.out.println(isBST(root, right, left).toString());
        }

        Boolean isBST(TreeNode1 node, Integer right, Integer left){// recursive call

            if (node == null){
                return true;
            }
            // test curr node itself if BST ot not
            if (right != null && right > node.val) {
                return false; // BST check
            }
            if (right == null){
                right = node.val;
            } else {
                if (node.val > right){
                    right = node.right.val;
                }
            }
            if (left == null){
                left = node.val;
            } else {
                if (node.val > left){
                    right = node.val;
                }
            }
            if (!isBST(node.right, right, left)){ // add left null check
                return false;
            }
            isBST(node.right, right, left);

            return true;
        }

        /*
        minRightVal = null
        minLeftVal = null

        call (currNode 20, minParent null) --> root node call
          setup minRightVal to val if null, otherwise set to min of curr
          setup minLeftVal to val if null, otherwise set to min of curr
          use minRightVal and minRightVal with right and left nodes to check BST or not --> propogate faulure
          call (30, 20) --> fail as 15 is less --> propogate the failure to top

        call (currNode 20, minParent null) --> root node call
          right = 20
          left = 30
          setup minParentVal to val if null, otherwise set to min of curr
          call (30, 20) --> fail as 15 is less --> propogate the failure to top
         */
        class TreeNode1 {
            TreeNode1 left;
            TreeNode1 right;
            Integer val;
            TreeNode1 (Integer val){
                this.val = val;
            }
        }
    }
}
