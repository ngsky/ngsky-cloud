package com.ngsky.algorithm.zuo.jj;

/**
 * 求两个字符串中最长公共字串
 * <p>
 * 从左往右尝试模型
 * ab1234a
 * 123a
 * <p>
 * 找到某个位置最长公共字串最长，最后才生成这个字串, 假设这个位置是 0~k
 * i:s1 位置，j: s2位置
 * dp[i][j]: s1[i],s2[j]上最长公共字串长度
 * dp[i][0]...
 * dp[0][j]
 * 以s1[i]结尾字串/s2[j]结尾字串/s1[i]==s2[j]
 */
public class LongestCommonSubstring {
    public static String substring1(String s1, String s2) {
        if (null == s1 || s1 == "" || null == s2 || s2 == "") return "";
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int[][] dp = new int[c1.length][c2.length];
        dp[0][0] = c1[0] == c2[0] ? 1 : 0;
        for (int i = 1; i < c1.length; i++) {  // c2[0]取一个字符 abcd caaa
            dp[i][0] = Math.max(c2[0] == c1[i] ? 1 : 0, dp[i - 1][0]);
        }
        for (int i = 1; i < c2.length; i++) { // c1[0]取一个字符
            dp[0][i] = Math.max(c1[0] == c2[i] ? 1 : 0, dp[0][i - 1]);
        }
        int im = 0;
        int max = 0;
        for (int i = 1; i < c1.length; i++) {
            for (int j = 1; j < c2.length; j++) {
                // 保证连续  abcd caaad, 前一个字符是否相同
                if(c1[i] == c2[j]){
                    dp[i][j] = c1[i-1] == c2[j-1] ? dp[i-1][j-1]+1: 1; // 如果前一个字符也相同，说明可以算作字串,否则重新开始
                }else if(c1[i] == c2[j-1]){
                    dp[i][j] = dp[i][j-1];
                }else if(c1[i-1] == c2[j]){
                    dp[i][j] = dp[i-1][j];
                }else { // 相当于 s1 和  s2 当前字符都没啥用
                    dp[i][j] = dp[i-1][j-1];
                }
                if(dp[i][j] > max){  // 记录最长字串终点位置
                    max = dp[i][j];
                    im = i;
                }
            }
        }
        String ans = "";
        for (int i = im - max + 1; i <= im; i++) {
           ans += c1[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        String s1 = "1AB2345CD";
        String s2 = "12345EF";
        System.out.println(substring1(s1, s2));
    }
}
