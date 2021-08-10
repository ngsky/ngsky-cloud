package com.ngsky.algorithm.frame;

/**
 * 自底向上 循环迭代
 */
public class Fib2 {
    private static int[] dp;

    private static int fib(int n) {
        dp = new int[n + 1];
        dp[1] = dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 30;
        long st = System.currentTimeMillis();
        System.out.println(fib(n));
        long et = System.currentTimeMillis();
        System.out.println("time:" + (et - st));
    }
}
