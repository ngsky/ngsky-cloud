package com.ngsky.algorithm.leetcode.o1;
import java.util.*;

public class Leetcode_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        if(len < 3) return new ArrayList<>();
        sort(nums, 0, len-1);
        // [-4,-1,-1,0,1,2]
        List<List<Integer>> ans = new ArrayList<>();
        Map<String,Boolean> visit = new HashMap<>();
        for(int i = 0;i<len;i++){
            int l = i+1, r = len-1;
            while(l<r){
                if(nums[l]+nums[r] < (-1 * nums[i])) {
                    l++;  // 左移动
                } else if(nums[l]+nums[r]>(-1*nums[i])){
                    r--;
                } else {
                    if(!visit.containsKey(nums[i] + "_" + nums[l] + "_" + nums[r])) {
                        List<Integer> item = new ArrayList<>();
                        visit.put(nums[i] + "_" + nums[l] + "_" + nums[r], true);
                        item.add(nums[i]);
                        item.add(nums[l]);
                        item.add(nums[r]);
                        ans.add(item);
                    }
                    l++;r--;
                }
            }
        }
        return ans;
    }

    public static void sort(int[] nums, int l ,int r) {
        if(nums == null || nums.length < 2) return;
        if(l < r){
            int povit = partition(nums, l, r);
            sort(nums, l,povit-1);
            sort(nums, povit+1, r);
        }
    }

    public static int partition(int[] nums, int l,int r){
        int povit = nums[r];
        int j = r;
        while(l < r){
            // 寻找左边大于povit的位置 l
            while(l < r && nums[l] <= povit) l++;
            // 寻找右边小雨povit的位置 r
            while(l < r && nums[r] >= povit) r--;

            // 交换 l 和 r位置数
            if(l<r){
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
            }
        }
        // l == r, 交换povit
        nums[j] = nums[l];
        nums[l] = povit;
        return l;
    }

    public static void main(String[] args){
        Leetcode_15 lc = new Leetcode_15();
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> ans = lc.threeSum(nums);
        for(int i = 0;i<ans.size();i++){
            System.out.println(ans.get(i));
        }
    }
}
