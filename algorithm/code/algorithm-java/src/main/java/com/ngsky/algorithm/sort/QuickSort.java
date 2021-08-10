package com.ngsky.algorithm.sort;

/**
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args){
        int[] arr = {6,1,12,0,3,10,13,-1,-1,-1,-1,4,14,20,16,-1,-1,-1,-1,-1,-1,-1,-1,2,5,11,15};
        quickSort(arr, 0, arr.length-1);
        for(int i = 0;i<arr.length;i++){
            System.out.print(arr[i] + ",");
        }
    }

    public static void quickSort(int[] arr, int L, int R){
        if(null == arr || arr.length < 2) return ;
        if(L < R) {
            int idx = partition(arr, L, R); // 找到基准值位置
            quickSort(arr, L, idx - 1); // 左边排序
            quickSort(arr, idx + 1, R); // 右边排序
        }
    }

    public static int partition(int[] arr, int L, int R){
       int pivot = arr[R] ; // 取右边界作为基准
       int j = R;
       while(L < R){
           while(L < R && arr[L] <= pivot) L++; // 找到左边元素大于基准值的位置
           while(L < R && arr[R] >= pivot) R--; // 找到右边元素小于基准值的位置
           if(L < R){  // 将左边大于基准的元素和右边小于基准的元素交换：满足左边元素小于基准，右边元素大于基准
               int t = arr[L];
               arr[L] = arr[R];
               arr[R] = t;
           }
       }
       arr[j] = arr[L]; // 如果发生碰撞（L=R），将基准值和碰撞点元素交换
       arr[L] = pivot;
       return L;
    }
}
