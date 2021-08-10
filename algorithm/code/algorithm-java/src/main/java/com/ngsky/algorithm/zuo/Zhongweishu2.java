package com.ngsky.algorithm.zuo;

/**
 * 求两个等长排序数组的上中位数
 */
public class Zhongweishu2 {
    public static int findZ(int[] arr1, int[] arr2){
        if(null == arr1 || null == arr2
        || arr1.length != arr2.length) return -1;
        int N = arr1.length;
        int s1 = 0, s2 = 0, e1 = N-1, e2 = N-1;
        while(s1 < e1){
            int m1 = (s1 + e1) >> 1;
            int m2 = (s2 + e2) >> 1;
            // 奇数加1，偶数加0
            int offset = ((e1 - s1 + 1) & 1) ^ 1;
            if(arr1[m1] < arr2[m2]){
                s1 = m1 + offset; // arr1 的起始位置
                e2 = m2;
            }else if(arr1[m1] > arr2[m2]){
                e1 = m1;
                s2 = m2 + offset;
            }else {
               return arr1[m1];
            }
        }
        // 此时s1 == e1 说明只定位到一个数，最后求出两个位置的最小值
        return Math.min(arr1[s1], arr2[s2]);
    }

    public static void main(String[] args){
        int[] arr1 = new int[] {1,3,5,6,7,8};
        int[] arr2 = new int[] {3,5,7,9,9,10};
//        int[] arr1 = new int[]{3,4,6,7,9,10,20,300,900,1999};
//        int[] arr2 = new int[]{1,3,4,7,9,10,20,30,900,2999};
        System.out.println(findZ(arr1, arr2));  // 1,3,3,5,5,6,7,7,8,9,9,10 => 6
    }
}
