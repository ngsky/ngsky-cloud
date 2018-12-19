package com.ngsky.algorithm.greedy;

/**
 * <dl>
 * <dt>Leetcode55</dt>
 * <dd>Description:
 * Jump Game:
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Determine if you are able to reach the last index.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 * <p>
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 * jump length is 0, which makes it impossible to reach the last index.
 * </dd>
 * <dd>CreateDate: 12/16/2018 10:51 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
public class Leetcode55 {
    public boolean canJump(int[] nums) {
        if (nums == null) return false;
        int len = nums.length;
        if (len <= 0) return false;
        int[] index = new int[len];
        for (int i = 0; i < len; i++) {
            index[i] = i + nums[i];
        }
        int jump = 0;
        int maxIndex = index[0];
        while (jump < len && jump <= maxIndex) {
            maxIndex = maxIndex < index[jump] ? index[jump] : maxIndex;
            jump++;
        }
        return jump == len;
    }

    public static void main(String[] args) {
        Leetcode55 let = new Leetcode55();
//        int[] nums = {0};
//        int[] nums = {12};
//        int[] nums = {2,3,1,1,4};
//        int[] nums = {3,2,1,0,4};
        int[] nums = {1,2,0,0,3,1,2};

        System.out.println(let.canJump(nums));
    }
}
