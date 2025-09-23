package com.others.a_1_b;

import java.util.HashSet;

public class DetectLoop_Cyclic_LinkedList_w_HashSet {
    class Node {
        int data;
        Node next;
        public Node(int data) {
            this.data = data;
        }
    }
    class CustomLinkedList {
        Node head;
        public boolean hasCycle_w_HashSet() {
            HashSet<Node> nodes = new HashSet<>();
            Node current = head;
            while (current != null) {
                if (nodes.contains(current)) {
                    return true;
                } else {
                    nodes.add(current);
                }
                current = current.next;
            }
            return false;
        }
    }
    public static void main(String[] args) {
        DetectLoop_Cyclic_LinkedList_w_HashSet cyclicLinkedList = new DetectLoop_Cyclic_LinkedList_w_HashSet();
        cyclicLinkedList.createListCheckCycle();
    }
    public void createListCheckCycle(){
        CustomLinkedList noCycleLinkedList = new CustomLinkedList();
        Node firstNode = new Node(3);
        Node secondNode = new Node(4);
        Node thirdNode = new Node(5);
        Node fourthNode = new Node(6);

        noCycleLinkedList.head = firstNode;
        firstNode.next = secondNode;
        secondNode.next = thirdNode;
        thirdNode.next = fourthNode;

        System.out.println(noCycleLinkedList.hasCycle_w_HashSet());

        CustomLinkedList cycleLinkedList = new CustomLinkedList();
        cycleLinkedList.head = firstNode;
        thirdNode.next = secondNode;

        System.out.println(cycleLinkedList.hasCycle_w_HashSet());
    }
}
