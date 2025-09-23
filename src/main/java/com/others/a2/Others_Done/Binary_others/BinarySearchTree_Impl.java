package com.others.a2.Others_Done.Binary_others;

public class BinarySearchTree_Impl {
    public static void main(String[] args) {
        BinarySearchTree_Impl bst = new BinarySearchTree_Impl();
        bst.testBST();
    }

    public void testBST(){
        //          5
        //       2        4
        //    9    10  7
        Node rootNode = new Node();
        rootNode.data = 5;
        Node nodeTwo = new Node();
        nodeTwo.data = 2;
        Node nodeFour = new Node();
        nodeFour.data = 4;
        Node nodeNine = new Node();
        nodeNine.data = 9;
        Node nodeTen = new Node();
        nodeTen.data = 10;
        Node nodeSeven = new Node();
        nodeSeven.data = 7;
        Node twoDuplicated = new Node();
        twoDuplicated.data = 2;
        // Simple Unsorted - Binary Tree, not BST
//        rootNode.left = nodeTwo;
//        rootNode.right = nodeFour;
//        nodeTwo.left = nodeNine;
//        nodeTwo.right = nodeTen;
//        nodeFour.left = nodeSeven;

        BinarySearchTree tree = new BinarySearchTree();
        tree.root = rootNode;
        // BST - for BST, comment out above Simple Unsorted - Binary Tree
        tree.insert(nodeTwo.data);
        tree.insert(nodeFour.data);
        tree.insert(nodeNine.data);
        tree.insert(nodeTwo.data);
        tree.insert(nodeSeven.data);
        tree.insert(twoDuplicated.data);

        tree.preOrderTraversal();
        tree.inOrderTraversal();
        tree.postOrderTraversal();
        System.out.println(tree.contains_w_recursion(5) + " " + tree.contains_w_recursion(46));
    }
    class BinarySearchTree {
        Node root;
        public boolean contains_w_recursion(int value) {
            return contains_w_recursion(this.root, value);
        }
        private boolean contains_w_recursion(Node root, int value) {
            if (root == null) {
                return false;
            }
            if (value < root.data) {
                return contains_w_recursion(root.left, value);
            }
            if (value > root.data) {
                return contains_w_recursion(root.right, value);
            }
            return true; // has to be equal, as above < and > are not true
        }
        public void preOrderTraversal() {
            System.out.println("preOrderTraversal - roots before leaves ");
            preOrderTraversal(this.root);
            System.out.println();
        }
        private void preOrderTraversal(Node root) { // roots before leaves
            if (root != null) {
                System.out.print(root.data + " ");
                preOrderTraversal(root.left);
                preOrderTraversal(root.right);
            }
        }
        public void inOrderTraversal() {
            System.out.println("inOrderTraversal - left tree, then root ");
            inOrderTraversal(this.root);
            System.out.println();
        }
        private void inOrderTraversal(Node root) { // left tree, then root
            if (root != null) {
                inOrderTraversal(root.left);
                System.out.print(root.data + " ");
                inOrderTraversal(root.right);
            }
        }
        public void postOrderTraversal() {
            System.out.println("postOrderTraversal - leaves before roots ");
            postOrderTraversal(this.root);
            System.out.println();
        }
        private void postOrderTraversal(Node root) { // leaves before roots
            if (root != null) {
                postOrderTraversal(root.left);
                postOrderTraversal(root.right);
                System.out.print(root.data + " ");
            }
        }
        public void insert(int value) {
            insert(this.root, value);
        }

        private Node insert(Node root, int value) {
            if (root == null) {
                root = new Node();
                root.data = value;
            } else if (value < root.data) {
                // insert on left
                root.left = insert(root.left, value);
            } else if (value > root.data) {
                // insert on right
                root.right = insert(root.right, value);
            }
            return root;
        }
    }
    class Node {
        Node left;
        Node right;
        int data;
    }
}
