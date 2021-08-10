package com.ngsky.algorithm.sort;

/**
 * 插入排序
 * 核心原理：首先保证有插入数据的数组有序，新来一个元素，去现阶段有序数组找到合适的位置，插入即可。
 * 假设 0~K 已经有序，新来一个元素，视为K+1,从K+1位置向前看，保证0~K+1有序，插入完所有数据，整改数组有序。
 *
 * 时间复杂度：O(N^2)
 */
public class InsertSort {

    public static void insertSort(int[] arr){
        if(null == arr || arr.length < 2) return;
        for(int i = 1;i<arr.length;i++){
            // 0~0 有序，假设插入1，需要保证0~1有序，插入2，保证0~2有序，以此类推
            // 从 i 位置向前开，一直看到 0 位置
            for(int j = i - 1;j>=0 && arr[j] > arr[j+1];j--){  // 上一个位置大，那么将它移动到当前位置
                swap(arr, j, j+1);
            }
        }
    }

    public static void swap(int[] arr, int i , int j){
        if(null == arr || i >= arr.length || j >= arr.length) return;
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void printArray(int[] arr){
        if(null == arr || arr.length == 0) return;
        for(int i =0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args){
        int[] arr = new int[]{0,1,3,9,0,-1,23,98,99,12,43,34,22,0,-1999};
        insertSort(arr);
        printArray(arr);
    }
}
