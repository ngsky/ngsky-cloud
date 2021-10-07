package com.ngsky.algorithm.zuo.a2021.a10;

public class A10_07_LCSbustring {

    /**
     * 经典动态规划问题
     * @param s1
     * @param s2
     * @return
     */
    public static String lcs1(String s1, String s2){
        if(s1 == null || s2 == null) return null;
        int n = s1.length(), m = s2.length();
        if(n == 0 || m == 0) return "";
        char[] ch1 = s1.toCharArray(), ch2 = s2.toCharArray();
        // dp[i][j]: 以ch1[i],ch2[j]结尾的字符的公共字串长度, 只有最后一个字符相同时，才有可能更新dp，否则都是0
        int[][] dp = new int[n][m];
        dp[0][0] = ch1[0] == ch2[0] ? 1 : 0;

        int less = Math.min(n, m);
        int end = dp[0][0] == 1 ? 0 : -1;  // 记录 ch1 的end
        int max = dp[0][0] == 1 ? 1 : 0;

        // 每一行第一列
        for(int i = 1;i<n;i++){
            if(ch1[i] == ch2[0]){
                dp[i][0] = 1;
                end = i;
                max = 1;
            }
        }
        // 每一列第一行
        for(int j = 1;j<m;j++){
            if(ch2[j] == ch1[0]){
                dp[0][j] = 1;
                end = 0;
                max = 1;
            }
        }
        // 其它普遍位置
        for(int i = 1;i<n;i++){
            for(int j = 1;j<m;j++){
                if(ch1[i] == ch2[j]){
                   dp[i][j] = dp[i-1][j-1] + 1;
                   if(dp[i][j] > max) {
                       max = dp[i][j];
                       end = i;
                   }
                }
            }
        }
        char[] ans = new char[max];
        // abcdefdbhk,end=5,len=3 5-3+1
        int idx = 0;
        for(int i = (end-ans.length+1);i<end+1;i++){
            ans[idx++] = ch1[i];
        }
        return String.valueOf(ans);
    }

    /**
     * DP空间压缩，采用有限几个变量进行结果依赖滚动
     * 二维矩阵调度：大范围：从右上角->左上角->左下角
     *             内部：向右下角对角线滑动
     * @param s1
     * @param s2
     * @return
     */
    public static String lcs2(String s1, String s2){
        if(s1 == null || s2 == null) return null;
        int n = s1.length(), m = s2.length();
        if(n == 0 || m == 0) return "";
        char[] ch1 = s1.toCharArray(), ch2 = s2.toCharArray();
        int i = 0,j = m - 1, max = 0, end = 0,idx = 0;
        while(i<n){
            // 内部
            int x = i, y = j, cl = 0;
            while(x < n && y < m){
                cl = ch1[x] == ch2[y] ? cl + 1 : 0;
                if(cl > max){
                    max = cl;
                    end = x;
                }
                x++; y++;
            }

            // 大范围
            if(j>0){
                j--;
            }else {
                i++;
            }
        }
        char[] ans = new char[max];
        for(int k = (end-max+1);k<=end;k++){
            ans[idx++] = ch1[k];
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args){
        String s1 = "ABC1234567DEFGGG000";
        String s2 = "HIJKL12345F67MNGGG000P";
        System.out.println(lcs1(s1,s2));
        System.out.println(lcs2(s1,s2));
    }
}
