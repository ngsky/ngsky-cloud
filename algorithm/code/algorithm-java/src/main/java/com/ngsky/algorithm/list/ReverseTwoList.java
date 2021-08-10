package com.ngsky.algorithm.list;

public class ReverseTwoList {
    static class Node {
        public int value;
        public Node prex;
        public Node next;
        public Node(int value){
            this.value = value;
        }
    }

    // 双向链表反转：转化逻辑和单向链表转化逻辑一致，
    // 1<>2<>3<>4
    // 1 || 2<>3<>4
    // 2<>1 || 3<>4
    // 3<>2<>1 || 4
    // 4<>3<>2<>1 || null
   public static Node reverseList(Node head) {
       Node prex = null;
       Node next = null;
       while(head != null){
           // 切断当前节点与后面节点之间的关系
           next = head.next;
           if(next != null){
               next.prex = null;
           }

           // 新节点和新链表关联上
           head.next = prex;
           head.prex = null;
           if(null != prex){
               prex.prex = head;
           }

           // 重置新链表
           prex = head;

           // 遍历下一个节点
           head = next;
       }
       return prex;
   }

    public static void main(String[] args){
        Node head = new Node(1);
        Node n2 = new Node(2);
        head.next = n2;n2.prex = head;
        Node n3 = new Node(3);
        n2.next = n3; n3.prex = n2;
        Node n4 = new Node(4);
        n3.next = n4; n4.prex = n3;
        Node n5 = new Node(5);
        n4.next = n5; n5.prex = n4;
        Node n6 = new Node(6);
        n5.next = n6;n6.prex = n5;

       Node newHead = reverseList(head) ;
       System.out.println(newHead.value);
    }
}
