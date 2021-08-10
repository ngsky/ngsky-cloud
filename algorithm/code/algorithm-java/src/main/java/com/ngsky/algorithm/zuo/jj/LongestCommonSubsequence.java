package com.ngsky.algorithm.zuo.jj;

/**
 * 求两个字符串中最长公共子序列长度
 *
 * "abcdabc123"
 * "abc123"
 *  ==》 abc123
 * 1) 不以 s1 和 s2 最后一个字符结尾的
 * 2) 以s1 最后一个字符结尾，但是不以s2最后一个字符结尾
 * 3) 不以s1最后一个字符结尾，但是以 s2 最后一个字符结尾
 * 4) 以s1 和 s2 最后一个字符结尾
 */
public class LongestCommonSubsequence {

    public static int way1(String s1, String s2){
        // 行 s1, 列 s2
        // 0行， 0列
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int[][] dp = new int[c1.length][c2.length];
        dp[0][0] = c1[0] == c2[0] ? 1 : 0;  // 选s1第一个字符，选s2第一个字符
        for(int i = 1;i<c1.length;i++){  // 选s2 第一个字符，用s1来匹配
            dp[i][1] = Math.max(dp[i-1][1],c2[1] == c1[i] ? 1 : 0); // 如果在某个位置存在字符相同，那么最大子序列长度就是1，后序位置最大子序列都将是1
        }
        for(int i = 1;i<c2.length;i++){
            dp[1][i] = Math.max(dp[1][i-1], c1[1] == c2[i] ? 1: 0); // 同理
        }
        // 其它情况
        // abc123ccx
        // 123ccdd =>
        for(int i = 2;i<c1.length;i++){
            for(int j = 2;j<c2.length;j++){
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]); // dp[i-1][j]: 以s2[j]结尾为最大公共子序列；dp[i][j-1]: 以s1[i]结尾为最大公共子序列
                if(c1[i] == c2[j]){
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + 1); // 如果以s1[i], s2[j] 结尾为最大公共子序列
                }
            }
        }
        return dp[c1.length-1][c2.length-1];
    }

    public static void main(String[] args){
        String s1 = "abcdabc123";
        String s2 = "abc123";
        System.out.println(way1(s1, s2));
    }
}
