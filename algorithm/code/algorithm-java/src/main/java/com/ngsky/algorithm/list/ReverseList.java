package com.ngsky.algorithm.list;

public class ReverseList {
    static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    // 单向链表反转：首先需要两个变量来辅助反转，时间复杂度O(N),空间复杂度O(1)
    // 1.从原链表头部开始遍历，拿到头部对象，然后使用辅助节点next 来表示当前头节点后面的链表，这样就可以切断头部  next = head.next
    // 2.使用prex表示新的链表，拿到原链表新节点后，该节点的下一个节点指向新链表  head.next = prex
    // 3.更新新链表: prex = head
    // 4.遍历下一个节点：head = next
    // 1->2->3->4->5->6
    // 1 || 2->3->4->5->6
    // 2->1 || 3->4->5->6
    // 3->2->1 || 4->5->6
    // 4->3->2->1 || 5->6
    // 5->4->3->2->1 || 6
    // 6->5->4->3->2->1 || null
    public static Node reverseList(Node head) {
        Node prex = null;
        Node next = null;
        while (head != null) {
            next = head.next; // 截断头节点
            head.next = prex; // 取出头节点来，指向prex节点
            prex = head;
            head = next;
        }
        return prex;
    }

    public static void main(String[] args){
        Node head = new Node(1);
        Node n2 = new Node(2);
        head.next = n2;
        Node n3 = new Node(3);
        n2.next = n3;
        Node n4 = new Node(4);
        n3.next = n4;
        Node n5 = new Node(5);
        n4.next = n5;
        Node n6 = new Node(6);
        n5.next = n6;

        Node newHead = reverseList(head);
        System.out.println(newHead.value);
    }
}
