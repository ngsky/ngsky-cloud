package com.ngsky.algorithm.zuo.a2021.a10;

public class A10_06_MinPathSum {
    // 1.经典动态规划问题：一个普遍位置依赖于它的上面的位置和左边的位置
    public static int minPathSum1(int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null || arr[0].length == 0) return 0;
        int row = arr.length, col = arr[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = arr[0][0];
        // 第一列：只能从上往下进行累加
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + arr[i][0];
        }
        // 第一行：只能从左往右进行累加
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + arr[0][j];
        }
        // 其它位置，选择从上往下、从左往右中最小的那个进行累加
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + arr[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    // 2.动态规划压缩空间方法：采用一维数组，进行滚动，直到最后得出结论
    public static int minPathSum2(int[][] arr){
        if(arr == null || arr.length == 0 || arr[0] == null || arr[0].length == 0) return 0;
        int more = Math.max(arr.length, arr[0].length);
        int less = Math.min(arr.length, arr[0].length);
        boolean rowMore = more == arr.length;
        int[] dp = new int[less];  // 压缩空间，二维变一维，且采用行或者列的最小值进行申请数组空间
        dp[0] = arr[0][0];
        // 确定一行或者一列
        for(int i = 1;i<less;i++){
            dp[i] = dp[i-1] + (rowMore ? arr[0][i] : arr[i][0]);
        }
        // 一维数组滚动求和
        for(int i = 1;i<more;i++){
            // 此时已经滚动到下一行或者下一列，相当于要覆盖之前的求解结果
            dp[0] = dp[0] + (rowMore ? arr[i][0] : arr[0][i]);
            for(int j = 1;j<less;j++){
                // 此时是在同一行或者同一列进行求解
               dp[j] = Math.min(dp[j-1], dp[j]) + (rowMore ? arr[i][j] : arr[j][i]);
            }
        }
        return dp[less - 1];
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 2, 3},{3, 2, 1},{3, 3, 3},{5, 6, 3}};
        System.out.println(minPathSum1(arr));
        System.out.println(minPathSum2(arr));
    }
}
