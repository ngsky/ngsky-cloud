package com.ngsky.algorithm.dfs;

public class Dfs3 {
    public static void dfs(int row, int col, int[][] nums){
        if(row >= 4 && col >= 4){
            String str = "";
            for(int i = 0;i<5;i++){
                for(int j = 0;j<5;j++){
                    str += nums[i][j] == 0 ? "黑" : "白";
                }
                str += ";";
            }
            System.out.println(str);
            return;
        }
        if(row >= 5 || col >= 5) return;
        nums[row][col] = 0;
        dfs(row, col+1, nums);
        dfs(row+1, col, nums);

        nums[row][col] = 1;
        dfs(row, col+1, nums);
        dfs(row+1, col, nums);
    }

    public static void main(String[] args){
        int[][] nums = new int[5][5];
        dfs(0,0, nums);
    }
}
