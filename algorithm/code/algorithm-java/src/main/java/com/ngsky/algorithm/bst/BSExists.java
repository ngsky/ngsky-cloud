package com.ngsky.algorithm.bst;

/**
 * 检查有序数组中是否存在某个数
 * 二分法
 */
public class BSExists {
    // 检查有序数组arr中是否存在num
    public static boolean exists(int[] arr, int num){
       if(null == arr || arr.length == 0) return false;
       int L = 0;
       int R = arr.length - 1;
       while(L < R){
           int mid = (L + R) / 2;
           if(arr[mid] == num) {
               return true;
           }else if(arr[mid] < num){ // 右边寻找
               L = mid + 1;
           }else { // 左边寻找
               R = mid - 1;
           }
       }
       // L == R
        return arr[L] == num;
    }
    public static void main(String[] args){
        int[] arr = new int[]{-1,1, 1,2,3,7,8,32,33,89,99};
        int num = 8;
        System.out.println(exists(arr, num));
    }
}
