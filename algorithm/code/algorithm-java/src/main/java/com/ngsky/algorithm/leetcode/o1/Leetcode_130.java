package com.ngsky.algorithm.leetcode.o1;

public class Leetcode_130 {
    static int[][] xy = {{0,1},{0,-1},{1,0},{-1,0}};
    public void solve(char[][] board) {
        int m = board.length;
        if(m < 3) return;
        int n = board[0].length;
        if(n < 3) return;
        for(int i = 1;i<m;i++){
            for(int j = 1;j<n;j++){
                if(board[i][j] == 'O'){
                    if(i == 2 && j == 3){
                        System.out.println();
                    }
                    if(oTx(i,j,board, -1)){
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }

    // flag == 0 <==> xy[1] 控制反方向不能继续
    public static boolean oTx(int r, int c, char[][] board, int flag){
        int count = 0;
        if(r == 0 || r == board.length-1 || c == 0 || c == board[0].length) return false;
        for(int i = 0;i<xy.length;i++){
            if(i == flag) continue;
            int nx = r + xy[i][0];
            int ny = c + xy[i][1];
            if(nx >= 0 && nx<board.length && ny >= 0 && ny<board[0].length){
                if(board[nx][ny] == 'O'){  // 超这个方向到底看看是否有X,有的话就为true，否则为false
                    int fn = 0;
                    if(xy[i][1] < 0) {
                       fn = 0;
                    }else if(xy[i][1] > 0){
                       fn = 1;
                    }else if(xy[i][0] < 0){
                       fn = 2;
                    }else {
                       fn = 3;
                    }
                    if(oTx(nx,ny,board, fn)) {
                        count+=1;
                        break;
                    }

//                    int j = ny;
//                    if(xy[i][1] < 0) {  // left
//                        j=j-1;
//                        while(j>=0){
//                            if(board[nx][j] == 'X') {
//                                count+=1;
//                                break;
//                            } else{
//                               if(oTx(nx,j,board, 0)) {
//                                   count+=1;
//                                   break;
//                               }
//                            }
//                            j=j-1;
//                        }
//                    } else if(xy[i][1] > 0){ // right
//                        j=j+1;
//                        while(j<board.length){
//                            if(board[nx][j] == 'X'){
//                                count+=1;
//                                break;
//                            }else{
//                                if(oTx(nx, j, board, 1)){
//                                    count+=1;
//                                    break;
//                                }
//                            }
//                            j=j+1;
//                        }
//
//                    } else if(xy[i][0] < 0){ // up
//                        j = nx -1;
//                        while(j>=0){
//                            if(board[j][ny] == 'X'){
//                                count+=1;
//                                break;
//                            }else {
//                                if(oTx(j,ny, board, 2)){
//                                    count+=1;
//                                    break;
//                                }
//                            }
//                            j=j-1;
//                        }
//
//                    } else if(xy[i][0] > 0) { // down
//                        j = nx+1;
//                        while(j < board[0].length){
//                            if(board[j][ny] == 'X'){
//                                count+=1;
//                                break;
//                            }else {
//                                if(oTx(j,ny, board,3)){
//                                    count+=1;
//                                    break;
//                                }
//                            }
//                            j=j+1;
//                        }
//                    }
                }else {
                    count+=1;
                }
            }
        }
        return flag == -1 ? count == 4 : count == 3;
    }

    public static void main(String[] args){
        Leetcode_130 lc = new Leetcode_130();
//        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        char[][] board =  {{'O','X','X','O','X'},{'X','O','O','X','O'},{'X','O','X','O','X'},{'O','X','O','O','O'},{'X','X','O','X','O'}};

        lc.solve(board);
    }
}
