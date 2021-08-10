package com.ngsky.algorithm.zuo;

/**
 * 求两个等长排序数组的上中位数
 * 要求：时间复杂度：O(log N)
 * 二分思想
 */
public class Zhongweishu {
   public static int find(int[] arr1, int[] arr2) {
     if(null == arr1 || null == arr2 || arr1.length != arr2.length) return -1;
     int N = arr1.length;
     if(N == 1) return arr1[0] < arr2[0] ? arr1[0] : arr2[0];
     int s1 = 0; // arr1 起始位置
     int e1 = N-1; // arr1 终止位置
     int s2 = 0;
     int e2 = N-1;
     while(s1 < e1){
         // 9 [0,1,2,3,4,5,6,7,8] [0,1,2,3,4,5,6,7,8,9]10
         int m1 = (s1 + e1)/2;  // 4 / 4, 如果N位偶数，左边会比右边少一个数
         int m2 = (s2 + e2)/2;
         int offset = ((e1 - s1 + 1) & 1) ^ 1; // 奇数时，offset=0,偶数,offset=1
         if(arr1[m1] < arr2[m2]){
            // m1~N 0~m2
             s1 = m1 + offset;
             e2 = m2;
         }else if(arr1[m1] > arr2[m2]){
            // 0~m1 + m2~N
            e1 = m1;
            s2 = m2 + offset;
         } else {  // ==
             return arr1[m1];
         }
     }
     return Math.min(arr1[s1], arr2[s2]);
   }

   public static void main(String[] args){
       int[] arr1 = new int[]{3,4,6,7,9,10,20,300,900,1999};
       int[] arr2 = new int[]{1,3,4,7,9,10,20,30,900,2999};
       // 1,3,3,4,4,6,7,7,9,9,10,10,20,20,30,300,900,900,1999,2999   == 20
       System.out.println(find(arr1, arr2));
   }
}
