package com.ngsky.algorithm.bst;

/**
 * 递归版本二叉树遍历
 */
public class BinarySearchTree {
    public static void main(String[] args){
        // 构建一颗树 1,2,3,4,5,6,7
        Node head = new Node(1);
        Node l1 = new Node(2);
        head.left = l1;
        Node l2 = new Node(3);
        head.right = l2;
        Node l3 = new Node(4);
        l1.left = l3;
        Node l4 = new Node(5);
        l1.right = l4;
        Node l5 = new Node(6);
        l2.left = l5;
        Node l6 = new Node(7);
        l2.right = l6;

       // 遍历
        BinarySearchTree bst = new BinarySearchTree();
        System.out.println("先序遍历:");
        bst.firstOrder(head);

        System.out.println("中序遍历:");
        bst.inOrder(head);

        System.out.println("后序遍历:");
        bst.postOrder(head);
    }

    // 先序遍历 root left right
    public void firstOrder(Node head){
        if(null == head) return;
        System.out.println(head.v);
        firstOrder(head.left) ;
        firstOrder(head.right);
    }

    // 中序遍历 left root right
    public void inOrder(Node head){
        if(null == head) return;
        inOrder(head.left) ;
        System.out.println(head.v);
        inOrder(head.right);
    }

    // 后序遍历 left right root
    public void postOrder(Node head){
       if(null == head)  return;
       postOrder(head.left);
       postOrder(head.right);
       System.out.println(head.v);
    }
}
