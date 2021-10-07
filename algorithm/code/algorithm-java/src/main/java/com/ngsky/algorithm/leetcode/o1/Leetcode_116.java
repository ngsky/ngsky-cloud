package com.ngsky.algorithm.leetcode.o1;

public class Leetcode_116 {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    public Node connect(Node root) {
        if(null == root || root.left == null)return root;
        // 1.利用next存储当前节点父节点
        nextForParent(root);

        // 2.遍历设置next值，注意处理层级问题
        Node left = root.left;
        while(left!=null){
            popLevel(left, true);
            left = left.left;
        }
        return root;
    }

    public static void nextForParent(Node head){
        if(head != null){
            if(head.left != null) {
                head.left.next = head;
                head.right.next = head;
                nextForParent(head.left);
                nextForParent(head.right);
            }
        }
    }

    public static void popLevel(Node head, boolean left){
        if(head != null){
            if(left){
                head.next = head.next.right;
                popLevel(head.next, false);
            }else if(head.next.next != null) {
                head.next = head.next.next.left;
                popLevel(head.next, true);
            } else{
                head.next = null;
            }
        }
    }

    public static void main(String[] args){
        // 1,2,3,4,5,6,7
        Node root = new Node(1);
        Node n1 = new Node(2);
        Node n2 = new Node(3);
        root.left = n1;
        root.right = n2;

        Node n3 = new Node(4);
        Node n4 = new Node(5);
        n1.left = n3;
        n1.right = n4;
        Node n5 = new Node(6);
        Node n6 = new Node(7);
        n2.left = n5;
        n2.right = n6;

        Leetcode_116 lc = new Leetcode_116();
        Node n = lc.connect(root);
        System.out.println(n);
    }
}
