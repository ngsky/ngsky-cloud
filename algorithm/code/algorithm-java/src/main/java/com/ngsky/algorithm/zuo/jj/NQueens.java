package com.ngsky.algorithm.zuo.jj;

/**
 * n 皇后问题
 */
public class NQueens {

    // 暴力递归求解
    public static int num1(int n){
        if(n < 1) return 0;
        int[] alread = new int[n];
        return process1(alread, 0, n);
    }

    // alread[i]: 表示第i行第 alread[i] 列放置了皇后,有效范围: 0~n-1
    // idx：当前想要放置皇后的行, 此时0~idx-1 位置的皇后已经放置好，无需考虑
    // n: n皇后
    public static int process1(int[] alread, int idx, int n){
       // base case: idx == n: 表示0~n-1位置已经放置好，也就是皇后都放好，那这就是一种方案
        if(idx == n) return 1;
        int ans = 0;
        // 考虑每一列是否可以放置皇后
        for(int col=0;col<n;col++){
            // 验证当前行当前列是否可以放置皇后
            if(valid(alread, idx, col)){
                // 可以放置,继续下一行(也就是放置下一个皇后)
                alread[idx] = col; // 标记idx行,col列放置了皇后
                ans += process1(alread, idx + 1, n);
            }
        }
        return ans;
    }

    // 验证某行某列是否可以放置皇后
    public static boolean valid(int[] alread, int row, int col){
       for(int i = 0;i < row;i++) {  // 验证row行col列，说明alread 中0-row-1 列已经确定放置过，这需要验证这部分即可
           // alread[i]: 表示i行alread[i]列
           // 只需要验证 是否同列，是否同对角线
           if(alread[i] == col || Math.abs(i - row) == Math.abs(col - alread[i])) return false;
       }
       return true;
    }

    public static void main(String[] args){
        int n = 15;
        System.out.println(num1(n));
    }
}
