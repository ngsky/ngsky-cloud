package com.ngsky.algorithm.zuo;

/**
 * 给定一个数组，数组中的值表示绳子从左往右的一些点的坐标值，再给定一个K，表示一段新的绳子，求长度为k的绳子能够盖住多少个坐标点
 * 1 3 5 6 8 9 给定 k = 5
 * k = 5 可以盖住 1 3 5 6 这四个点，因为 6 - 1 = 5 绳子刚刚可以盖住
 *
 * 利用滑动窗口方式来解决
 */
public class CordCoverMaxPoint {

    // 滑动窗口方式计算
    // 时间复杂度：O（N）, 因为左右边界都不进行回退
    public static int maxPoint(int[] cords, int k ){
        int len = cords.length;
        if(len == 0) return 0;
        int l = 0, r = 0;
        int max = 0;
        while(l < len) {
            while(r < len && cords[r] - cords[l] <= k){  // 右边界向右滑动，扩大窗口，滑动到不能滑为止
                r++;
            }
            max = Math.max(max, r - (l++));  // 右边界不能向右滑动时，左边界向右滑动，缩小窗口
        }
        return max;
    }

    // 从左向右遍历，遇到每一个点都计算一遍：以该点结尾的绳子中，长度k能够盖住的坐标点的个数
    // 每个点都遍历是一个 O(N)的过程，然后计算以一个点结尾的盖住最大长度可以采用二分法计算，是一个O(logN)的过程
    // 因此：时间复杂度：O（N*logN）


}
