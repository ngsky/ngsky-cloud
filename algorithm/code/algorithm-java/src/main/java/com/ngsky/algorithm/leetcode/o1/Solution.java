package com.ngsky.algorithm.leetcode.o1;

/**
 * 奇偶链表
 */
public class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(null == head || null == head.next) return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode p = even;
        while(null != odd.next && null != even.next){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = p;
        return head;
    }

    public static void main(String[] args) {
        // [2,1,0,4,9,10,12,99,9,5,6,8]
        int[] t = {1,0,4,9,10,12,99,9,5,6,8};
        ListNode root = new ListNode(2, null);
        ListNode head = root;
        for(int i = 0;i<t.length;i++) {
            ListNode n = new ListNode(t[i]);
            head.next = n;
            head = n;
        }
        Solution so = new Solution();
        ListNode newNode = so.oddEvenList(root);
        System.out.println(newNode);
    }
}
