package com.ngsky.algorithm.zuo;

import java.util.HashMap;

public class Pro_03_06_02 {
    static class Node{
        int v;
        Node left;
        Node right;
        public Node(int v){
            this.v = v;
        }
    }

    public static void main(String[] args){
        Pro_03_06_02 p = new Pro_03_06_02();

        Node root = new Node(-3);
        Node l1 = new Node(3);
        root.left = l1;
        Node l2 = new Node(-9);
        root.right = l2;
        Node l3 = new Node(1);
        l1.left = l3;
        Node l4 = new Node(0);
        l1.right = l4;
        Node l5 = new Node(2);
        l2.left = l5;
        Node l6 = new Node(1);
        l2.right = l6;
        Node l7 = new Node(1);
        l4.left =l7;
        Node l8 = new Node(6);
        l4.right = l8;

        System.out.println(p.getMaxLength(root, 6));
    }

    public int getMaxLength(Node head, int sum){
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0,0);
        return preOrder(head, sum, 0, 1, 0, sumMap);
    }

    /**
     * @param head
     * @param sum
     * @param preSum head节点 到 当前节点的父节点所走路径节点只和
     * @param level 当前节点所在层级
     * @param maxLen 满足条件的最长路径
     * @param sumMap
     * @return
     */
    public int preOrder(Node head, int sum, int preSum, int level, int maxLen, HashMap<Integer, Integer> sumMap){
        if(null == head) return maxLen;
        int curSum = preSum + head.v;
        if(!sumMap.containsKey(curSum)){
            sumMap.put(curSum, level);
        }
        if(sumMap.containsKey(curSum - sum)){
            maxLen = Math.max(level-sumMap.get(curSum-sum), maxLen);
        }
        maxLen = preOrder(head.left, sum , curSum,level+1, maxLen, sumMap);
        maxLen = preOrder(head.right, sum,curSum, level+1, maxLen, sumMap);
        if(level == sumMap.get(curSum)){
            sumMap.remove(curSum);
        }
        return maxLen;
    }
}
