package com.ngsky.algorithm.zuo;

/**
 * 求两个排序数组中第k小的数
 */
public class ArrayKthNum {
    public static int kthNum(int[] arr1, int[] arr2, int k){
        if(null == arr1 || null == arr2 || k > arr1.length + arr2.length) return -1;
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
        int ll = longs.length, sl = shorts.length;
        if(k <= sl){
           // 从longs 和 shorts 中都取前k个数，找到他们的上中位数即可
            return findZ(shorts, longs, 0, k-1, 0,k-1);
        }
        if(k > ll){
            // 缩小范围: [k-ll-1 ~ shorts.len-1]  [k-sl-1 ~ longs.len - 1]
             if(shorts[k-ll-1] >= longs[ll-1]){ //
                return shorts[k-ll-1];
             }
             if(longs[k-sl-1] >= shorts[sl-1]){
                 return longs[k-sl-1];
             }
            return findZ(shorts, longs, k-ll, sl-1, k-sl, ll-1);
        }
        // sl < k <= ll
        // longs[k-sl-1 ~ k-1]  shorts[0~sl-1]
        if(longs[k-sl-1] > shorts[sl -1]){
            return longs[k-sl-1];
        }
        return findZ(shorts, longs, 0, sl-1,k-sl, k-1);
    }

    public static int findZ(int[] arr1, int[] arr2, int s1, int e1, int s2, int e2){
       while(s1 < e1) {
           int m1 = (s1 + e1)>>1;
           int m2 = (s2 + e2)>>1;
           int offset = ((e1 - s1 + 1) & 1) ^1;
           if(arr1[m1] < arr2[m2]){
                s1 = m1 + offset;
                e2 = m2;
           }else if(arr1[m1] > arr2[m2]){
                s2 = m2 + offset;
                e1 = m1;
           }else {
                return arr1[m1];
           }
       }
       return Math.min(arr1[s1], arr2[s2]);
    }

    public static void main(String[] args){
//        int[] arr1 = new int[] {1,3,5,6,7,8};
//        int[] arr2 = new int[] {3,5,7,9,9,10};  // 1,3,3,5,5,6,7,7,8,9,9,10

//        int[] arr1 = new int[]{3,4,6,7,9,10,20,32,33,300,900,1999};
//        int[] arr2 = new int[]{1,3,4,7,9,10,20,30,900,2999}; // 22
        int[] arr1 = new int[]{1,3};
        int[] arr2 = new int[]{2};
        int k = 1;
       System.out.println(kthNum(arr1,arr2,k));
    }
}
