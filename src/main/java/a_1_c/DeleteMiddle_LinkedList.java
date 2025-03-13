package a_1_c;

public class DeleteMiddle_LinkedList {
    public static class Node<D> {
        private D data;
        private Node next;
        public D getData() {
            return this.data;
        }
        public void setData(D data) {
            this.data = data;
        }
        public Node getNext() {
            return this.next;
        }
        public void setNext(Node next) {
            this.next = next;
        }
    }
    public static Node deleteMiddle_w_slow_fast(Node head) {
        if (head == null || head.getNext() == null) {
            return head; // one node, nothing to delete
        }
        Node slow = head;
        Node fast = head; // fast move at double speed, so when it reached end, slow is at middle
        Node previous = null;
        while(fast != null && fast.getNext() != null) {
            fast = fast.getNext().getNext();
            previous = slow;
            slow = slow.getNext();
        }
        // Delete middle node
        previous.setNext(slow.getNext());
        return head;
    }

    public static void printLinkedList(Node head) {
        while(head != null) {
            System.out.print(head.getData() + ", ");
            head = head.getNext();
        }
        System.out.println("END");
    }

    public static Node<Integer> buildNode(int data) {
        Node node = new Node();
        node.setData(data);
        node.setNext(null);
        return node;
    }

    public static void main(String[] args) {

        Node<Integer> head = buildNode(8);
        Node<Integer> nodeA = buildNode(3);
        Node<Integer> nodeB = buildNode(18);
        Node<Integer> nodeC = buildNode(12);
        Node<Integer> nodeD = buildNode(1);

        head.setNext(nodeA);
        nodeA.setNext(nodeB);
        nodeB.setNext(nodeC);
        nodeC.setNext(nodeD);

        printLinkedList(head);
        deleteMiddle_w_slow_fast(head);
        printLinkedList(head);
        deleteMiddle_w_slow_fast(head);
        printLinkedList(head);
    }
}
