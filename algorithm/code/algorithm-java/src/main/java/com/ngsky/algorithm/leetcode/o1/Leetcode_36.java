package com.ngsky.algorithm.leetcode.o1;

public class Leetcode_36 {
    public boolean isValidSudoku(char[][] board) {
        // 暴力破解, right->, down-> 两个方向
        // row, col (row+1,col+1)(row+1,col+2)(row+2,col+1)(row+2,col+2)

        for(int i = 0;i<9;i++){
            for(int j = 0;j<9;j++){
                char ch = board[i][j];
                if(ch == '.') continue;
                // 九宫格,只关注0~2,3~5,6~8,中间交叉不需考虑
                // (0,0)(0,3)(0,6)
                // (3,0)(3,3)(3,6)
                // (6,0)(6,3)(6,6)
                // 先看 (i,j) 落在哪个九宫格中，再进行判断,当前位置和当前九宫格剩下位置比较即可
                // (4,8)  (3,5) -> (5,8)  // 大不过2
                // i-0/i-3/i-6 <= 2 && >= 0, => i 在 3~5 行
                // j-0/j-3/j-6 <= 2 && >= 0, => j 在 6~8 列
                int[] step = new int[]{0,3,6};
                int rl = -1, cl = -1;
                for(int x = 0;x < 3;x++){
                    if(i - step[x] >= 0 && i - step[x] <= 2) rl = step[x];
                    if(j - step[x] >= 0 && j - step[x] <= 2) cl = step[x];
                }
                if(rl == -1 || cl == -1) return false;
//                for(int x = i;x <= rl+2;x++){  // 过滤掉无须验证的格子
//                   for(int y = j;y<=cl+2;y++) {
//                       if(ch == board[x][y]) return false;
//                   }
//                }
                for(int x = rl;x <= rl+2;x++){  // 某个九宫格全部遍历
                    for(int y = cl;y<=cl+2;y++) {
                        if(i!=x && j!=y && ch == board[x][y]) return false;
                    }
                }

                // 同行
                int col = j+1;
                while(col < 9){
                    if(ch == board[i][col++]) {
                        return false;
                    }
                }
                // 同列
                int row = i+1;
                while(row < 9){
                    if(ch == board[row++][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args){
        Leetcode_36 lc = new Leetcode_36();

        char[][] board = new char[][]{
//                {'.','.','.','.','5','.','.','1','.'},
//                {'.','4','.','3','.','.','.','.','.'},
//                {'.','.','.','.','.','3','.','.','1'},
//                {'8','.','.','.','.','.','.','2','.'},
//                {'.','.','2','.','7','.','.','.','.'},
//                {'.','1','5','.','.','.','.','.','.'},
//                {'.','.','.','.','.','2','.','.','.'},
//                {'.','2','.','9','.','.','.','.','.'},
//                {'.','.','4','.','.','.','.','.','.'}
//                {'.','.','5','.','.','.','.','.','.'},{'1','.','.','2','.','.','.','.','.'},{'.','.','6','.','.','3','.','.','.'},{'8','.','.','.','.','.','.','.','.'},{'3','.','1','5','2','.','.','.','.'},{'.','.','.','.','.','.','.','4','.'},{'.','.','6','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','9','.'},{'.','.','.','.','.','.','.','.','.'}
//                {'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}
                {'.','.','.','.','.','.','.','.','.'},{'.','.','.','3','.','.','5','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','8','.','.','.','.','.'},{'.','.','.','.','1','1','6','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','1','.','.'},{'.','.','.','.','.','.','.','.','7'},{'.','.','.','.','.','.','.','4','.'}
        };
        System.out.println(lc.isValidSudoku(board));
    }
}
