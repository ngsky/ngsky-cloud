package com.ngsky.algorithm.zuo.jj;

/**
 * 背包问题
 */
public class Knapsack {
    public static int knapsack(int[] w, int[] v, int bag){
       return process(w, v, 0, 0, bag);
    }

    // 考虑方式：从左往右考虑，假定 0..idx-1 位置都已经确定了，现在要确定idx位置是否选择
    // al: 已经装了多少重量
    // idx: 当前物品索引
    // bag: 背包能装多少重量　
    public static int process(int[] w, int[] v, int idx, int al, int bag){
        // 如果超出重量，返回-1
        if(al > bag) return -1;
        // 如果已经到全部都选择过了，那么当前位置的重量是0
        if(idx == w.length) return 0;
        // 选择不装当前位置物品
        int p1 = process(w, v, idx + 1, al, bag);
        // 选择装当前位置物品, 有可能装不下，返回-1
        int pt = process(w, v, idx + 1, al + w[idx], bag);
        int p2 = 0;
        if(pt != -1) {
            p2 = pt + v[idx];
        }
        // 求出选当前位置和不选当前位置的最大价值
        return Math.max(p1, p2);
    }

    public static void main(String[] args){
       int max = knapsack(new int[]{2,3,1,5,6,9,90,99}, new int[]{2,3,1,5,6,9,90,99}, 100);
       System.out.println(max);
    }
}
