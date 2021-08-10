package com.ngsky.algorithm.zuo.jj;

public class RobotWalk {
    // 暴力递归1
    // N: 1～N 个位置
    // M: 初始位置
    // K: 要求走几步
    // P: 目标位置
    // idx: 当前来到的位置
    // cur: 已经走了几步了
    public static int walk(int N, int M, int K, int P, int idx, int cur) {
        if (cur == K && idx == P) {
            return 1;
        }
        int ans = 0;
        if (cur < K) { // 还可以走
            if (idx == 1) {
                ans += walk(N, M, K, P, 2, cur + 1);
            } else if (idx == N) {
                ans += walk(N, M, K, P, N - 1, cur + 1);
            } else {
                ans += walk(N, M, K, P, idx - 1, cur + 1);
                ans += walk(N, M, K, P, idx + 1, cur + 1);
            }
        }
        return ans;
    }

    // 暴力递归2
    // N: 1~N 位置
    // idx: 当前走到的位置
    // rest: 剩余步数
    // P: 目标位置
    public static int walk1(int N, int idx, int rest, int P) {
        if (rest == 0) {
            // 步数已经用完
            return idx == P ? 1 : 0; // 当前位置在目标P位置，则形成一种方案
        }
        if (idx == 1) {
            // 在1位置，只能走2位置
            return walk1(N, 2, rest - 1, P);
        }
        if (idx == N) {
            // 在N位置，只能走N-1位置
            return walk1(N, N - 1, rest - 1, P);
        }
        // 可以走左右两侧
        return walk1(N, idx + 1, rest - 1, P) + walk1(N, idx - 1, rest - 1, P);
    }

    public static int dp1(int N, int M, int K, int P){
        // dp[i][j]: 表示剩余i步，当前位置在j处
        int[][] dp = new int[K+1][N+1];
        dp[0][P] = 1; // 初始状态
        // 下面逻辑和递归思想一致
        for(int i = 1;i<=K;i++){
            for(int j = 1;j<=N;j++){
                if(j == 1){
                    dp[i][j] = dp[i-1][2];
                }else if(j == N){
                    dp[i][j] = dp[i-1][N-1];
                }else {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1];
                }
            }
        }
        return dp[K][M];
    }

    // 1，2，3，4
    //    M P
    //K = 3
    public static void main(String[] args) {
        int N = 15;
        int M = 4;
        int K = 10;
        int P = 6;
        System.out.println(walk(N, M, K, P, M, 0));
        System.out.println(walk1(N,M, K, P));
        System.out.println(dp1(N,M, K, P));
    }
}
