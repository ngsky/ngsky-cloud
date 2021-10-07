package com.ngsky.algorithm.leetcode.o1;

public class Leetcode_2025 {
    public int waysToPartition(int[] nums, int k) {
        int left = 0, right = 0;
        int max = 0, sum = 0;
        int len = nums.length;
        for(int i = 0;i<len;i++){
            sum += nums[i];
        }
        // a->k,b->k,c->k
        for(int i = 0;i<len;i++){
            int curr = 0;
            int tp = sum + k - nums[i];
            // a,b,c/d,e,f,g
            for(int pivot = 0;pivot<len-1;pivot++){
                if(i<=pivot) {

                    if(left+k-nums[i]==right) curr++;
                } else {
                    if(right+k-nums[i]==left) curr++;
                }
            }
            max = Math.max(max, curr);
        }
        return max;
    }

    public static void main(String[] args){
        Leetcode_2025 lc = new Leetcode_2025();

        int[] nums = {2,-1,2};
        int k = 3;
        System.out.println(lc.waysToPartition(nums, k));

    }
}
