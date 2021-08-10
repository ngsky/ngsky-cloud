package com.ngsky.algorithm.zuo;

import com.ngsky.algorithm.Node;

/**
 *  找到二叉树中最大的搜索二叉树
 */
public class Pro_03_06_01 {
    public static void main(String[] args){
        Node head = new Node(6);
        Node l1 = new Node(1);
        head.left = l1;
        Node l2 = new Node(12);
        head.right = l2;
        Node l3 = new Node(0);
        l1.left = l3;
        Node l4 = new Node(3);
        l1.right = l4;
        Node l5 = new Node(10);
        l2.left = l5;
        Node l6 = new Node(13);
        l2.right = l6;
        Node l7 = new Node(4);
        l5.left = l7;
        Node l8 = new Node(14);
        l5.right = l8;
        Node l9 = new Node(20);
         l6.left = l9;
        Node l10 = new Node(16);
        l6.right = l10;
        Node l12 = new Node(2);
        l7.left = l12;
        Node l13 = new Node(5);
        l7.right = l13;
        Node l14 = new Node(11);
        l8.left = l14;
        Node l15 = new Node(15);
        l8.right = l15;

        Node bstTree =getLargestTree(head);
        System.out.println(bstTree.v);
    }

    public static Node getLargestTree(Node head){
        int[] record = new int[3];
        return postList(head, record);
    }

    // record[0]: size, record[1]:minV, record[2]:maxV
    public static Node postList(Node head, int[] record){
        if(null == head){
            record[0] = 0;
            record[1] = Integer.MAX_VALUE;
            record[2] = Integer.MIN_VALUE;
            return null;
        }
        int v = head.v;
        Node left = head.left;
        Node right = head.right;
        Node lbst = postList(left, record);
        int lsize = record[0];
        int lmin = record[1];
        int lmax = record[2];

        Node rbst = postList(right, record);
        int rsize = record[0];
        int rmin = record[1];
        int rmax = record[2];

        record[1] = Math.min(lmin, v);
        record[2] = Math.max(rmax, v);
        if(left == lbst && right == rbst && lmax < v && rmin > v){
            record[0] = lsize + rsize + 1;
            return head;
        }
        record[0] = Math.max(lsize, rsize);
        return lsize > rsize ? lbst : rbst;
    }
}
