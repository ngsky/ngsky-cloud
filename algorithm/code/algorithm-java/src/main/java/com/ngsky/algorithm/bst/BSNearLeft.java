package com.ngsky.algorithm.bst;

/**
 * 找到有序数组中 大于等于 num的最左位置, 其实就是第一个比num大或者等的数的下标
 */
public class BSNearLeft {
    public static int nearLeft(int[] arr, int num){
        if(null == arr || arr.length == 0) return -1;
        int L = 0;
        int R = arr.length -1;
        int idx = -1;
        while(L <= R){
            int mid = (L + R) >> 1;
            if(arr[mid] >= num){
               idx = mid;
               R = mid - 1;
            }else {
               L = mid + 1;
            }
        }
        return idx;
    }

    public static void main(String[] args){
        int[] arr = new int[]{-1,1, 1,2,3,6,8,32,33,89,99};
        int num = 9;
//        System.out.println(nearLeft(arr, num));
//        System.out.println(Long.MAX_VALUE);

        System.out.println(Integer.MAX_VALUE);
        System.out.println(1<<30); // 1*2=2,1*2*2=4 => 2^30
    }
}
