package com.ngsky.algorithm.zuo;

public class Zhongweishu3 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        if(len1 == 0 && len2 == 0){
           return -1;
        }
        if(len1 == 0){  // 1234  12345
            return (len2 & 1) == 0 ? (nums2[(len2-1)>>1] + nums2[len2>>1])/2.0 : nums2[len2>>1];
        }
        if(len2 == 0){
            return  (len1 & 1) == 0 ? (nums1[(len1-1)>>1] + nums1[len1>>1])/2.0 : nums1[len1>>1];
        }

        int k = (len1 + len2)  >> 1;  // 1,2,3
        int[] longs = len1 >= len2 ? nums1 : nums2;
        int[] shorts = len1 < len2 ? nums1 : nums2;
        if(((len1 + len2) & 1) == 0 ){
            return (subfind(shorts, longs, k) + subfind(shorts, longs, k+1) ) / 2.0;
        }
        return subfind(shorts, longs, k+1);
    }

    public static double subfind(int[] shorts, int[] longs, int k){
        int ll = longs.length, sl = shorts.length;
        // 1,10 =>11/2=5 // 5,6 11/2=5,   5,5 = 10/2 = 5 - 1 = 4
        if(k <= sl){
            return findZ(shorts, longs, 0, sl-1, 0, ll - 1);
        }
        // 因为k是中位数，绝对不会出现 k > ll,
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
        // 1,2,3,4,5 /5   1,2,3,4,5,5,6,7,9 /9  14  k = 14/2=7-1 = 6
        if(longs[k - sl - 1] >= shorts[sl-1]){
            return longs[k-sl-1];
        }
        return findZ(shorts, longs, 0, sl-1, k-sl, k-1);
    }

    public static int findZ(int[] arr1, int[] arr2, int s1, int e1, int s2, int e2){
        while(s1 < e1){
            int m1 = (s1 + e1) >> 1;
            int m2 = (s2 + e2) >> 1;
            int offset = ((e1 - s1 + 1) & 1) ^ 1;
            if(arr1[m1] > arr2[m2]){
               s2 = m2 + offset;
               e1 = m1;
            }else if(arr1[m1] < arr2[m2]){
               s1 = m1 + offset;
               e2 = m2;
            }else {
                return arr1[m1];
            }
        }
        // s1 == e1   s2 == e2
        return Math.min(arr1[s1], arr2[s2]);
    }
    public static void main(String[] args){
        int[] nums1 = new int[]{1};
        int[] nums2 = new int[]{2,3};

        Zhongweishu3 zh = new Zhongweishu3();
        System.out.println(zh.findMedianSortedArrays(nums1, nums2));
    }
}
