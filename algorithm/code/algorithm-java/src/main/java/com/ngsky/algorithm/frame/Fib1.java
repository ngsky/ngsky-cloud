package com.ngsky.algorithm.frame;

/**
 * 自顶向下 递归
 */
public class Fib1 {
    private static int[] dp;

    private static int dp(int n) {
        if (n == 2 || n == 1) return 1;
        if (dp[n] != 0) return dp[n];
        dp[n] = dp(n - 1) + dp(n - 2);
        return dp[n];
    }

    public static void main(String[] args) {
        long st = System.currentTimeMillis();
        int n = 30; // 832040 2
        dp = new int[n + 1];
        System.out.println(dp(n));
        long et = System.currentTimeMillis();
        System.out.println("time:" + (et - st));
    }
}
