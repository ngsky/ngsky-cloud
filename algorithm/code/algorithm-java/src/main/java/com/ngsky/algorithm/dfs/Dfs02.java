package com.ngsky.algorithm.dfs;

import java.io.File;
import java.util.Scanner;

/**
 * 园子积水问题
 *
 * input:
 * 10 12
 * W........WW.
 * .WWW.....WWW
 * ....WW...WW.
 * .........WW.
 * .........W..
 * ..W......W..
 * .W.W.....WW.
 * W.W.W.....W.
 * .W.W......W.
 * ..W.......W.
 *
 * output:
 * 3
 */
public class Dfs02 {
    static int M,N;
    static char[][] arr;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(new File("/Users/ngsky/coding/2021/xcoding/ngsky-cloud/algorithm/code/algorithm-java/src/main/java/com/ngsky/algorithm/dfs/Dfs02.txt"));
        M = sc.nextInt();N = sc.nextInt();
        arr = new char[M][N];
        int idx = 0;
        while(sc.hasNextLine()){
            String l = sc.next();
            arr[idx++] = l.toCharArray();
        }
        int ans = 0;
        for(int i = 0;i<M;i++){
            for(int j = 0;j<N;j++){
               if(arr[i][j] == 'W') {
                   dfs(i, j);
                   ans+=1;
               }
            }
        }

        System.out.println(ans);
   }
   public static void dfs(int x,int y){
     arr[x][y] = '.';
     for(int i = -1;i<=1;i++){
         for(int j = -1;j<=1;j++){
             int dx = x+i, dy = y+j;
             if(dx >= 0 && dx < M && dy>=0&& dy<N && arr[dx][dy] == 'W') dfs(dx,dy);
         }
     }
   }
}
