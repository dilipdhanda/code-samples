package com.others.a_2;/*
 * Click `Run` to execute the snippet below!
 * https://app.coderpad.io/NY4JJAEG
 */

class MergeTwoLinkedLists_Walmart {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; next = null;}
    }

    public static void main(String[] args) {

        ListNode cn = null ; // Current Node
        ListNode i1 = new ListNode(2); cn = i1; // input 1
        cn.next = new ListNode(5); cn = cn.next; // added 5
        cn.next = new ListNode(8); // added 8
        // 3 7 10
        ListNode i2 = new ListNode(3); cn = i2; // input 1
        cn.next = new ListNode(7); cn = cn.next; // added 5
        cn.next = new ListNode(10); cn = cn.next; // added 8

        cn = i1; while(cn != null ) { System.out.println(cn.val); cn = cn.next; }
        cn = i2; while(cn != null ) { System.out.println(cn.val); cn = cn.next; }
        ListNode f = MergeTwoLinkedLists_Walmart.mergerList(i1,i2);
        cn = f; while(cn != null ) { System.out.println(cn.val); cn = cn.next; }
    }

    public static ListNode mergerList(ListNode a, ListNode b){ // already in sorted order

        ListNode c1 = a;
        ListNode c2 = b;
        ListNode f = null;
        ListNode fn = null ; // first node

        while (c1 != null && c2 != null) {
            if (c1.val < c2.val) {
                if (f == null) {
                    f = new ListNode(c1.val);
                    fn = f;
                } else {
                    f.next = new ListNode(c1.val);
                    f = f.next;
                }
                c1 = c1.next;
            } else {
                if (f == null) {
                    f = new ListNode(c2.val);
                    fn = f;
                } else {
                    f.next = new ListNode(c2.val);
                    f = f.next;
                }
                c2 = c2.next;
            }
        }

        // add remaining items in c1 or c1 if they are still not null
        while (c1 != null) {
            f.next = c1;
            f = f.next;
            c1 = c1.next;
        }
        while (c2 != null) {
            f.next = c2;
            f = f.next;
            c2 = c2.next;
        }

        return fn;
    }

}