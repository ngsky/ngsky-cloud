package com.ngsky.algorithm.leetcode.o1;

public class Leetcode_42 {
    public int trap(int[] height) {

        //[0,1,0,2,1,0,1,3,2,1,2,1]
        // 3-1-1=1*1 = 1
        // 7-3-1=3*2=6 >>  1+1 ==> 4
        // 10-8-1=1*2=2 >> 1 ==> 1

        //[4,2,0,3,2,5]
        // 5-0-1 = 4*4=16 >> 2+3+2 ==> 9
        // 从第一个位置开始，找到后面某个位置的高度大于等于该位置停止，先计算一把这段范围可能装水容量
        // 再从刚刚停止这个位置出发，向后找某个位置使得高度大于等于当前位置
        // 如果一直没有找到，那么以最后一个位置为右边界成为容器范围可装水。
        int len = height.length;
        if(len <= 1) return 0;
        int i = 0;
        int sum = 0;
        while(i<len-1){
            if(height[i] == 0) {
                i++;
                continue;
            }
            int minus = 0;
            boolean flag = false;
            for(int j = i + 1;j<len;j++){
                // 找到高度大于等于当前位置高度的某个截止位置
                if(height[j] >= height[i]){
                    int cur = (height[i] * (j - i - 1)) - minus;
                    sum += cur;
                    i = j;
                    flag = true;
                    break;
                }
                // 小于时候累积应该减去的数
                minus += height[j];
            }
            // 全部没有命中上一条，说明是比自己还小，左高右低大斜坡
            if(!flag){
                minus -= height[len-1];
                sum += (height[len-1] * (len-1-i-1)) - minus;
                break;
            }
        }
        return sum;
    }


    public static void main(String[] args){
        Leetcode_42 lc = new Leetcode_42();
        // [0,1,0,2,1,0,1,3,2,1,2,1]
        //[4,2,0,3,2,5]
        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1} ;
        System.out.println(lc.trap(height));
    }
}
