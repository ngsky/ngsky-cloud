package com.ngsky.algorithm.zuo.jj;

import java.util.*;

/**
 * [3,2,5,4,8,10,9,0] 3
 * 8 构成6个
 * [0,1] -> [0] -> [] -> [2] 5
 * [2,3] 5
 */
public class SlidingWindowMaxArray {

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = getWindowMax(arr, w);
            int[] ans2 = rightWay(arr, w);
            if (!isEqual(ans1, ans2)) {
                System.out.println("0ops!");
            }
        }
        System.out.println("test finish");
    }

    // w:窗口大小
    public static int[] getWindowMax(int[] arr, int w) {
        if (null == arr || w < 1 || arr.length < w) return null;
        // 构造双端队列，大->小
        LinkedList<Integer> queue = new LinkedList<>();
        // 结果容器
        int[] ans = new int[arr.length - w + 1];
        int idx = 0;
        for (int R = 0; R < arr.length; R++) {
            // 新来一个数，如果该数比队列尾部大，则弹出
            while (!queue.isEmpty() && arr[R] >= arr[queue.peekLast()]) {
                queue.pollLast();
            }
            // 直到不用弹出为止，也就是新加的数比尾部数小，则直接加入数组下标
            queue.addLast(R);
            // 为了保证窗口大小为W，应该从头部弹出,验证头部是否过期
            if (queue.peekFirst() == R - w) {
                queue.pollFirst();
            }
            // 记录结果：必须保证窗口大小为w时才记录
            if (R >= w - 1) {
                ans[idx++] = arr[queue.peekFirst()];
            }
        }
        return ans;
    }

    public static int[] rightWay(int[] arr, int w) {
        if (null == arr || w < 1 || arr.length < w) return null;
        int[] ans = new int[arr.length - w + 1];
        int index = 0;
        int L = 0;
        int R = w - 1;
        while (R < arr.length) {
            int max = arr[L];
            for (int i = L + 1; i <= R; i++) {
                max = Math.max(max, arr[i]);
            }
            ans[index++] = max;
            L++;
            R++;
        }
        return ans;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static boolean isEqual(int[] arr1, int[] arr2){
        if((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) return false;
        if(arr1 == null && arr2 == null) return true;
        if(arr1.length != arr2.length) return false;
        for(int i = 0;i<arr1.length;i++){
            if(arr1[i] != arr2[i]) return false;
        }
        return true;
    }
}
