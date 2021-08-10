package com.ngsky.algorithm.zuo;

import com.ngsky.algorithm.Node;
import com.ngsky.algorithm.GenBinaryTree;

/**
 * 找到二叉树中符合搜索二叉树条件的最大拓扑结构
 *
 * 以6为头节点
 * 1 < 6 1 [1]
 * 12 > 6, 1,12 [2]
 *
 * -- 1
 * 0: 0< 1, 12,0 [3]
 * 3: 3>1, 12,0,3 [4]
 *
 * -- 12
 * 10 10 < 12 && 10 > 6: 0,3,10 [5]
 * 13 13 > 12  0,3,10,13 [6]
 *
 * -- 0
 * return
 *
 * -- 3
 * return
 * 10,13
 *
 * -- 10
 * 4: 4 < 10<12 !> 6 x
 * 14: 14 > 10 !< 12 x  [13] [6]
 *
 * -- 13
 * 20: 20 !< 13 x
 * 16: 16 > 13 > 12 > 6 [16] [7]
 *
 * -- 16
 * return
 * ans = 7;
 */
public class Pro_03_07_01 {

    public static void main(String[] args){
        int[] arr = {6,1,12,0,3,10,13,-1,-1,-1,-1,4,14,20,16,-1,-1,-1,-1,-1,-1,-1,-1,2,5,11,15};
        Node head = new GenBinaryTree().gen(arr);
        System.out.println(bstTopoSize1(head));
    }

    public static int bstTopoSize1(Node head){
       if(null == head) return 0;
       int max = maxTopo(head,head);
       max = Math.max(bstTopoSize1(head.left), max);
       max = Math.max(bstTopoSize1(head.right), max);
       return max;
    }

    // 如果节点n是搜索二叉树节点一员，返回以h节点开头的搜索二叉树节点数量
    public static int maxTopo(Node h, Node n){
        if(null != h && null != n && isBstNode(h,n,n.v)){
            return maxTopo(h, n.left) + maxTopo(h,n.right) + 1;
        }
        return 0;
    }

    // 判断节点n是否是搜索二叉树中的一员
    public static boolean isBstNode(Node h,Node n,int v){
        if(null == h) return false;
        if(h == n) return true;
        return isBstNode(h.v > v ? h.left : h.right, n, v);
    }
}
