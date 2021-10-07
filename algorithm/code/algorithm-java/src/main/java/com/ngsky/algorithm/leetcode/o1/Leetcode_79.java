package com.ngsky.algorithm.leetcode.o1;

public class Leetcode_79 {
    static int[][] xy = {{0,1},{0,-1},{1,0},{-1,0}};

    public boolean exist(char[][] board, String word) {
        // 1. O(m*n) 时间复杂度看看里面字符是否有 word 字符
        // 2. 向四周扩算寻找
        int[] cz = new int[52];
        int rl = board.length;
        int cl = board[0].length;
        for(int i = 0;i<rl;i++){
            for(int j = 0;j<cl;j++){
                cz[idx(board[i][j])] += 1;
            }
        }

        char[] chs = word.toCharArray();
        boolean flag = true;
        if(chs.length > (rl*cl)) return false;
        for(int i = 0;i<chs.length;i++){
            if(cz[idx(chs[i])] == 0){
                flag = false;
            }
        }
        if(!flag) return false;

        // 向四个方向寻找
        int[][] visit = new int[rl][cl];
        for(int i = 0;i<rl;i++){
            for(int j = 0;j<cl;j++){
                if(board[i][j] == chs[0]){
                    visit[i][j] = 1;
                    boolean ans = dfs(i,j,board,chs,1,visit);
                    if(ans) return true;
                    visit = new int[rl][cl];
                }
            }
        }
        return false;
    }

    public static int idx(char c){
        if(c - 'a' >= 0) return c - 'a';
        return ((c - 'a') + 32) * (-1) + 26;
    }

    public static boolean dfs(int r, int c, char[][] board, char[] chs, int x, int[][] visit){
        if(x >= chs.length) return true;
        for(int i = 0;i<xy.length;i++){
            int nx = r + xy[i][0];
            int ny = c + xy[i][1];
            if(nx >=0 && nx < board.length && ny >= 0 && ny < board[0].length && visit[nx][ny] != 1){
                if(board[nx][ny] == chs[x]){
                    visit[nx][ny] = 1;
                    boolean flag = dfs(nx,ny, board, chs, x+1, visit);
                    if(flag) return true;
                    visit[nx][ny] = 0;
                }
            }
        }
        return false;
    }

    public static void main(String[] args){
        Leetcode_79 lc = new Leetcode_79();
//        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
//        String word = "ABCCED";
        char[][] board = {{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
        String word = "ABCESEEEFS";
       System.out.println(lc.exist(board, word));
    }
}
