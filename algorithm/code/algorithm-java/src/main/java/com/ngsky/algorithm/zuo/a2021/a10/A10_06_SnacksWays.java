package com.ngsky.algorithm.zuo.a2021.a10;

public class A10_06_SnacksWays {
    // 1.第一种方法：从左往右的尝试模型，递归暴力法
    public static int way1(int[] snacks, int v) {
        return process1(snacks, 0, v);
    }

    // 针对每个零食，可以选择要或者不要
    // idx: 零食索引
    // rest: 背包剩余体积
    public static int process1(int[] snacks, int idx, int rest) {
        if (rest < 0) {  // 剩余背包空间都为负数了，那说明这条支路是没法走通的
            return -1;
        }
        if (idx == snacks.length) {  // 在 rest >= 0 的同时，已经没有可以选择的零食了，说明这条支路可以走桶，是一种选择方案
            return 1;
        }
        int a1 = process1(snacks, idx + 1, rest);  // 不选择当前零食
        int a2 = process1(snacks, idx + 1, rest - snacks[idx]); // 选择当前零食
        return a1 + (a2 == -1 ? 0 : a2);  // 两条支路方案之和即为答案
    }

    /**
     * 将从左往右的尝试模型，暴力递归的方法改写成动态规划方案
     *
     * @param args
     */
    public static int way2(int[] snacks, int v) {
        int N = snacks.length;
        int[][] dp = new int[N + 1][v + 1];  // dp[i][j]: 第i袋零食，背包剩余体积为j时候有多少中放置方案
        // 当零食全部选择完了，那么无论剩余体积是多少，都有一种方案, 改写 process1 中return 1 语句
        for (int j = 0; j <= v; j++) {
            dp[N][j] = 1;
        }
        // dp 递推过程
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= v; j++) {
                // 不要和要两条支路方案数之和, 改写process1 中两条支路递归方案
                dp[i][j] = dp[i + 1][j] + ((j - snacks[i] >= 0) ? dp[i + 1][j - snacks[i]] : 0);
            }
        }
        return dp[0][v];
    }

    /**
     * 另一种考虑方式：也是从左往右的尝试模型
     * 考虑体积从0装到v，每袋零食的方案
     * @param snacks
     * @param v
     * @return
     */
    public static int way3(int[] snacks, int v){
        int N = snacks.length;
        int[][] dp = new int[N][v+1]; // dp[i][j]: 第i袋零食，背包体积为j的时候，有多少种方案
        for(int i = 0;i<N;i++){
            dp[i][0] = 1; // 背包体积为0的时候，对于任何号零食来说，都具体一种方案，那就是不放进去
        }
        if(snacks[0] <= v){
            dp[0][snacks[0]] = 1; // 如果0号零食体积小于背包体积，那么可以直接放进去，也是一种方案
        }
        for(int i = 1;i<N;i++){
            for(int j = 1;j<=v;j++){
                // 同样考虑，当前位置号零食不放或者放入背包
                dp[i][j] = dp[i-1][j] + ((j-snacks[i] >= 0) ? dp[i-1][j-snacks[i]] : 0);
            }
        }
        int ans = 0;
        for(int j = 0;j<=v;j++){
            ans += dp[N-1][j];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] snacks = {3, 5, 2, 1, 5, 4, 3, 2, 3, 5, 6, 7, 9, 10,9,0,1};
        int v = 13;
        System.out.println(way1(snacks, v));
        System.out.println(way2(snacks, v));
        System.out.println(way3(snacks, v));
    }
}
