package com.ngsky.algorithm.sort;

/**
 * 冒泡排序
 * 核心思想：从小到大排序：两两比较，大的放后面，每一轮找一个最大的值冒泡到最右位置，直到结束。
 * 从左到右比较两个元素的大小，哪个元素大就将它冒泡到索引大的位置，直到最右测位置，每次冒泡找到一个最大的元素放到数组最右侧
 *
 * 时间复杂度：O(N^2) 空间复杂度：O(1)
 */
public class BubbleSort2 {
    public static void bubbleSort(int[] arr){
        if(null == arr || arr.length < 2) return;
        for(int i = arr.length - 1;i>0;i--){
           for(int j = 0;j<i;j++) {  // 因为每次都是当前元素和下一个元素进行比较，这里 j 一定要小于i，不然会越界
              if(arr[j] > arr[j+1]) {  // 谁大谁放后面，这就是冒泡，大的泡冒到上面去
                  swap(arr, j, j+1);
              }
           }
        }
    }

    public static void swap(int[] arr, int i ,int j){
        if(null == arr || i >= arr.length || j >= arr.length) return;
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void printArray(int[] arr){
        if(null == arr) return;
        for(int i = 0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args){
        int[] arr = new int[]{0,1,3,9,0,-1,23,98,99,12,43,34,22,0,-1999};
        bubbleSort(arr);
        printArray(arr);
    }
}
