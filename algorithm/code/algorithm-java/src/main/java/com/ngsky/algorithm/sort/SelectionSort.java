package com.ngsky.algorithm.sort;

import java.util.*;

/**
 * 选择排序
 * 核心思想：每次都从数组剩下的元素中挑选最大或者最小的元素和当前位置元素交换。
 * 时间复杂度：O(N^2), 空间复杂度:O(1)
 * 比如：
 * 第一次从 0～N 挑选最小元素放到0号位置
 * 第二次从 1～N 挑选最小元素放到1号位置
 * ...
 */
public class SelectionSort {
   public static void selectionSort(int[] arr) {
      if(null == arr || arr.length < 2) return;
      // 最后一个位置不需要再选择了，因为它就是最小的
      for(int i = 0;i<arr.length-1;i++){
         int minIdx = i;
         for(int j = i+1;j<arr.length;j++){
           minIdx = arr[j] < arr[minIdx] ? j : minIdx;
         }
         // 找到最小位置了，进行数据交换
         swap(arr, minIdx, i);
      }
   }

   public static void swap(int[] arr, int i, int j){
      int tmp = arr[i];
      arr[i] = arr[j];
      arr[j] = tmp;
   }

   // 对数器的写法
   public static int[] randomArr(int maxSize, int maxValue){
      int[] arr = new int[(int)((maxSize + 1) * Math.random())];
      for(int i = 0;i<arr.length;i++){
         arr[i] = (int)((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
      }
      return arr;
   }

   public static int[] copyArr(int[] arr){
      if(null == arr) return null;
      int[] ans = new int[arr.length];
      for(int i = 0;i<arr.length;i++) {
         ans[i] = arr[i];
      }
      return ans;
   }

   public static void comparator(int[] arr){
      Arrays.sort(arr);
   }

   public static boolean isEquals(int[] arr1, int[] arr2){
      if((null == arr1 && null != arr2) || (null != arr1 && null == arr2)) return false;
      if(null == arr1 && null == arr2) return true;
      if(arr1.length != arr2.length) return false;
      for(int i = 0;i<arr1.length;i++){
         if(arr1[i] != arr2[i]) return false;
      }
      return true;
   }

   public static void printArr(int[] arr){
       if(null == arr || arr.length == 0) return;
       for(int i = 0;i<arr.length;i++){
          System.out.println(arr[i]);
       }
   }

   public static void main(String[] args){
      int testTimes = 1000;
      int maxSize = 1000;
      int maxValue = 100000;
      boolean ok = true;
      for(int i = 0;i<testTimes;i++){
         int[] arr1 = randomArr(maxSize, maxValue);
         int[] arr2 = copyArr(arr1);
         selectionSort(arr1);
         comparator(arr2);
         if(!isEquals(arr1, arr2)){
            ok = false;
            printArr(arr1);
            printArr(arr2);
            break;
         }
      }
      System.out.println(ok ? "OK": "Error");
   }
}
