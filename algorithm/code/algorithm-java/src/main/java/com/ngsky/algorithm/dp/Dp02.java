package com.ngsky.algorithm.dp;

/**
 * 凑硬币
 */
public class Dp02 {
    public static void main(String[] args) {
        Dp02 dp = new Dp02();
        int[] coins = {1,25};
        int res = dp.dp(coins, 11);
        System.out.println(res);
    }

    private int dp(int[] coins, int n) {
        if(n == 0) return 0;
        if(n < 0) return -1;
        int res = Integer.MAX_VALUE;
        for(int i=0;i<coins.length;i++){
            int sub = dp(coins, n - coins[i]);
            res = res < sub ? res : sub + 1;
        }
        return res != Integer.MAX_VALUE ? res : -1;
    }
}
