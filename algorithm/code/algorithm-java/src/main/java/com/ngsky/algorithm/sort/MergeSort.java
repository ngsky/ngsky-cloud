package com.ngsky.algorithm.sort;

/**
 * 归并排序递归版本
 */
public class MergeSort {
    public static void main(String[] args){
        MergeSort ms = new MergeSort();

        int[] arr = {2,1,3,43,23,44,56,2,4,5,6,8,7,9,6,0};
        ms.process(arr, 0, arr.length-1);
        // 打印结果
        for(int i = 0;i< arr.length;i++){
            System.out.print(arr[i] + " ");
        }
    }

    // 1.假设这个方法可以将 L->R 范围内的数据排序
    public void process(int[] arr, int L, int R){
        if(L >= R) return;
        int M = (R + L) /2;
        // 让左边有序
        process(arr, L, M);
        // 让右边有序
        process(arr, M+1, R);
       // 将两个有序的序列合并为整体有序
        merge(arr, L, M, R);
    }
    public void merge(int[] arr, int L, int M, int R){
        int[] help = new int[R - L + 1];
        int idx = 0, p1 = L, p2 = M + 1;
        // 两个序列都在边界范围内
        while(p1 <= M && p2 <= R){
            help[idx++] = arr[p1] <= arr[p2]?arr[p1++]:arr[p2++];
        }
        // 左边未超出边界
        while(p1 <= M){
            help[idx++] = arr[p1++];
        }
        // 右边未超出边界
        while(p2<=R){
            help[idx++] = arr[p2++];
        }
        // 将辅助数据的数据刷回到原始数组中
        for(int i = 0;i<help.length;i++){
            arr[L+i] = help[i];
        }
    }
}
