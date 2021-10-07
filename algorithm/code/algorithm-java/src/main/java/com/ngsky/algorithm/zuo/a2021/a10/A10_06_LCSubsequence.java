package com.ngsky.algorithm.zuo.a2021.a10;

/**
 * 最长公共子序列
 */
public class A10_06_LCSubsequence {

    // 1.从左往右的尝试模型
    public static String lcs1(String s1, String s2){
       if(s1 == null || s2 == null) return null;
       int n = s1.length(), m = s2.length();
       if(n == 0 || m == 0) return "";
       char[] ch1 = s1.toCharArray(), ch2 = s2.toCharArray();
       String ans = ch1[0] == ch2[0] ? ch1[0] + "" : "";

       // 每一行的第一列
       for(int i = 1;i<n;i++){
           ans = (ch1[i] == ch2[0]) ? ch2[0]+"" : ans;
       }
       // 每一列的第一行
        for(int j = 1;j<m;j++){
           ans = (ch2[j] == ch1[0]) ? ch1[0]+"" : ans;
        }
        // 其它普遍位置
        for(int i = 1;i<n;i++){
            for(int j = 1;j<m;j++){
                ans = (ch1[i] == ch2[j]) ? ans + ch1[i] + "" : ans;
            }
        }
        return ans;
    }

    public static void main(String[] args){
        String s1 = "A1BC2D3EFGH45I6JK7LMN";
        String s2 = "12OPQ3RST4U5V6W7XYZ";
        System.out.println(lcs1(s1, s2));
    }
}
