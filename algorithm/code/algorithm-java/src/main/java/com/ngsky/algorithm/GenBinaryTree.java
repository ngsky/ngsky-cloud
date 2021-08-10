package com.ngsky.algorithm;

public class GenBinaryTree {

    public Node gen(int[] arr){
        Node head = new Node(arr[0]);
        newgen(head,0,arr);
        return head;
    }

    public void newgen(Node n, int idx,int[] arr){
        if(idx >= arr.length || (2*idx + 1) >= arr.length) return;
        if(-1 == arr[2 * idx + 1] || -1 == arr[2 * idx + 2]) return;
        n.left = new Node(arr[2 * idx + 1]);
        n.right =new Node(arr[2 * idx + 2]);
        newgen(n.left, 2 * idx + 1, arr);
        newgen(n.right, 2 * idx + 2, arr);
    }
}
