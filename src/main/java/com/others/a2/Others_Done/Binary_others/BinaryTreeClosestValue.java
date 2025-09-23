package com.others.a2.Others_Done.Binary_others;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class BinaryTreeClosestValue {
    private double minDiff = Double.MAX_VALUE;  // Initializing with max possible double value
    private int result = 0;  // Resultant node value
    private int closestValue(TreeNode node, double target) {
        if (node == null) return 0;
        // If current node's value is closer update the minDiff and result
        if (Math.abs(node.val - target) < minDiff) {
            minDiff = Math.abs(node.val - target);
            result = node.val;
        }
        // Decide which side to traverse based on the comparison of current node value and target
        if (target < node.val) {
            closestValue(node.left, target);   // Move to the left subtree
        } else if (target > node.val) {
            closestValue(node.right, target);  // Move to the right subtree
        }
        return result; // If target == node.val, it's the closest possible value, so no further traversal needed
    }

    public static void main(String[] args) {
        // You can create a binary tree by creating nodes and assigning left and right children.
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        BinaryTreeClosestValue sol = new BinaryTreeClosestValue();
        System.out.println(sol.closestValue(root, 5)); // Output: 4
    }
}

