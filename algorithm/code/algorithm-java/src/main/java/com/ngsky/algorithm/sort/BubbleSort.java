package com.ngsky.algorithm.sort;

public class BubbleSort {
    public static void main(String[] args){
        int[] arr = {6,1,12,0,3,10,13,-1,-1,-1,-1,4,14,20,16,-1,-1,-1,-1,-1,-1,-1,-1,2,5,11,15};
        bubbleSort(arr);
        for(int i = 0;i<arr.length;i++){
            System.out.print(arr[i] + ",");
        }
    }
    public static void bubbleSort(int[] arr){
        if(null == arr || arr.length < 2) return;
        for(int i = arr.length-1;i>0;i--){
           for(int j = 0;j<i;j++) {
               if(arr[j] > arr[j+1]){
                  int t = arr[j];
                  arr[j] = arr[j+1];
                  arr[j+1] = t;
               }
           }
        }
    }
}
