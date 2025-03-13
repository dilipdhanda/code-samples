package a_0_curr.tests;

class apple1_BinaryTreeSum { // Apple_1 - Ansul Tyagy 07/18/24

    public TreeNode createSumTree(TreeNode root) { // helper fn to set up base trees and start the process
        if (root == null) {
            return null;
        }
        TreeNode newRoot = new TreeNode(0); // Create a new root node for the new tree
        calculateSum(root, newRoot); // Populate the new tree and calculate the sum
        return newRoot;
    }

    private int calculateSum(TreeNode original, TreeNode newNode) { // main logic with recursive calls
        if (original == null) {
            return 0;
        }
        int leftSum = 0;
        int rightSum = 0;
        if (original.left != null){
            newNode.left = new TreeNode(0);  // create left node in new Sum Tree
            leftSum = calculateSum(original.left, newNode.left);    // recursive call
        }
        if (original.right != null){
            newNode.right = new TreeNode(0); // create right node in new Sum Tree
            rightSum = calculateSum(original.right, newNode.right); // recursive call
        }
        int currentSum = original.val + leftSum + rightSum;
        newNode.val = currentSum; // Update the new node's value
        return currentSum;
    }

    public class TreeNode {        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) {
        // Create an instance of BinaryTreeSum
        apple1_BinaryTreeSum binaryTreeSum = new apple1_BinaryTreeSum();
        TreeNode root = binaryTreeSum.createTree();

        // Create the new sum tree
        TreeNode sumTreeRoot = binaryTreeSum.createSumTree(root);
        binaryTreeSum.printTree(root, null); // null shows 1st arg is top root node
        binaryTreeSum.printTree(sumTreeRoot, null);

//        BinaryTreePrinter p = new BinaryTreePrinter();
//        p.printTree(root);
//        p.printTree(sumTreeRoot);
    }

    private void printTree(TreeNode node, TreeNode parent) { // main logic with recursive calls
        if (node == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("node: "+node.val);
        if (node.left != null) {
            sb.append(" - left "+node.left.val);
        }
        if (node.right != null) {
            sb.append(" - right "+node.right.val);
        }
        System.out.println(sb);
        printTree(node.left, node);
        printTree(node.right, node);
        if (parent == null){ // means root node
            System.out.println("- Done - ");
        }
    }

    public TreeNode createTree() {
        // Create the original tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(7);
        return root;
    }
    // Apple - Ansul Tyagi - Java rustic
}

