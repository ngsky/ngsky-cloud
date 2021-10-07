package com.ngsky.algorithm.leetcode.o1;

public class Leetcode_75 {
    public void sortColors(int[] nums) {
        // 2,0,2,1,1,0
        // 两个标志，第一个1，第一个2 不用遍历寻找位置
        int f1 = nums[0] == 1 ? 0 : -1;
        int f2 = nums[0] == 2 ? 0 : -1;
        for(int i = 1;i<nums.length;i++){
            f1 = (f1 == -1 && nums[i] == 1) ? i : f1;
            f2 = (f2 == -1 && nums[i] == 2) ? i : f2;
            if(nums[i] < nums[i-1]) {
                if(nums[i] == 0){ // 0 < 1,2
                    if(nums[i-1] == 1){ // 0 < 1
                        // ...,1,0, 找到第一个1，在f1之前插入即可
                        if(f1 >= 0){
                            // 1,1,1,0
                            // 0,1,1,0
                            swap(f1,i,nums);
                            f1 += 1;
                        }
                    }else {  // 0 < 2
                        // nums[i-1] == 2
                        // ...,2,0
                        // 2,2,2,0
                        // 1,2,0  => 0,1,2
                        // 0,0,1,1,2,0
                        // 0,2,0
                        if(f1 == -1){
                            if(f2 == -1){
                                swap(0,i,nums);
                                f2=1;
                            }else {
                               swap(f2,i,nums);
                               f2+=1;
                            }
                        }else if(f1 >= 0){
                            insert(f1,i,nums); // 在f1前面一个位置插入i位置的值
                            f1 += 1;
                            f2 += 1;
                        }
                    }
                }else if(nums[i] == 1){ // 1<2
                    // 2,1
                    // 0,0,1,1,2,2,1
                    // 找到第一个2，在f2之前插入当前位置值即可
                    swap(f2,i,nums);
                    f1= (f1 == -1 || f1 > f2) ? f2 : f1;
                    f2+=1;
                }
            }
        }
    }

    public static void swap(int i, int j,int[] nums){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    public static void insert(int i, int j,int[] nums){
        int t = nums[j];
        while(j > i) {
            nums[j] = nums[j-1];
            j--;
        }
        nums[i] = t;
    }

    public static void main(String[] args){
        Leetcode_75 lc = new Leetcode_75();
//        int[] nums = {2,0,2,1,1,0,0,0,0,1,2,2,2,2,2,2,2,1,0,0,1};
        int[] nums = {0,2,0};
        lc.sortColors(nums);

        for(int i = 0;i<nums.length;i++){
            System.out.print(nums[i] + ",");
        }
    }
}
