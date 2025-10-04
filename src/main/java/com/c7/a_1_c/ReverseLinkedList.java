package com.c7.a_1_c;

public class ReverseLinkedList {

    ReverseLinkedList next;
    int val;

    public ReverseLinkedList(int val) { this.val = val; next = null;
    }

    public void add(int val) {
        ReverseLinkedList tmp = this;
        while (tmp.next != null) { tmp = tmp.next; }
        tmp.next = new ReverseLinkedList(val);
    }

    public static ReverseLinkedList reverseLinkedList(ReverseLinkedList c) {
        ReverseLinkedList p = null;
        ReverseLinkedList n;
        while (c != null) {
            n = c.next;
            c.next = p;     // reversing the link
            p = c;         // moving currentList and previousList by 1 Node
            c = n;
        }
        return p;
    }

    public static ReverseLinkedList deleteVal(int val, ReverseLinkedList h) {
        ReverseLinkedList p = null;
        ReverseLinkedList c = h;
        while (c != null) {
            if (c.val == val) {
                if (c == h) {
                    h = c.next; // deleting initial vals if they match as they change the head of list
                } else {
                    p.next = c.next;
                }
            }
            p = c;
            c = c.next;
        }
        return h;
    }

    void print(ReverseLinkedList l) {
        while (l != null) {
            System.out.println(l.val);
            l = l.next;
        }
    }

    public static void main(String[] args) {
        ReverseLinkedList l = new ReverseLinkedList(5); l.add(7); l.add(5); l.add(9); l.add(5); l.print(l);
        ReverseLinkedList.reverseLinkedList(l); l.print(l);
    }
}

