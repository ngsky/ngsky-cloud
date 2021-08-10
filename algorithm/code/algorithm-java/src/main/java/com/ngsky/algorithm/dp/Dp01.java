package com.ngsky.algorithm.dp;

import java.util.Arrays;

/**
 * 凑硬币
 */
public class Dp01 {
    // 1,2,5,10,20.50,100   980
    // 980 - 100 = 880
    // 880 - 100 = 780 ... 80
    // 80 - 100 x  80 - 50 = 30
    // 30 - 100 x,30-50x,30-20=10
    // 10 - 10 ok

    // 980/100 = 9, 80
    // 80/50 = 1,30
    // 30/20 = 1, 10
    // 10/10 = 1, 0
    // 8/10 = 0, 8
    private int coinChange(int[] coins, int amount){
        Arrays.sort(coins);
        int sum = 0,left = amount;
        for(int i = 0;i<coins.length;i++){
            sum += (coins[i] / left);
            left = coins[i] % left;
        }
        if(left != 0) return -1;
        return sum;
    }

    public static void main(String[] args) {
        Dp01 dp = new Dp01();
        System.out.println(dp.coinChange(new int[]{1,2,5}, 11));
    }
}
