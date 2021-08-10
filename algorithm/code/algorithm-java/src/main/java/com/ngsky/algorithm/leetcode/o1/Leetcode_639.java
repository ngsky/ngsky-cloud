package com.ngsky.algorithm.leetcode.o1;

public class Leetcode_639 {
    public int numDecodings(String s) {
        if (null == s || "" == s) return 0;
        // c[i] = 1~9 // 考虑 c[i-1]
        // c[i] = 0   // 考虑 c[i-1]
        // c[i] = *  // 考虑 c[i+1] = 0/1~9 + c[i-1]
        char[] c = s.toCharArray();
        int len = c.length;
        int[] dp = new int[len];
        if (len == 1) {
            if (c[0] == '0') return 0;
            else if (c[0] == '*') return 9;
            return 1;
        }
        dp[0] = c[0] == '0' ? 0 : c[0] == '*' ? 9 : 1;
        char[] tmp = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (int i = 1; i < len; i++) {
            char cur = c[i];
            char prex = c[i - 1];
            // [0/1~9/*] [0/1~9/*] [0/1~9/*]
            if (prex == '*') {
                if (cur == '*') {
                    int tmpSum = 0;
                    for (int x = 0; x < tmp.length; x++) {
                        for (int y = 0; y < tmp.length; y++) {
                            tmpSum += subcount(tmp[x], tmp[y]) + 8;
                        }
                    }
                    dp[i] = dp[i - 1] + tmpSum;
                } else {
                    int tmpSum = 0;
                    for (int x = 0; x < tmp.length; x++) {
                        int xs = subcount(cur, tmp[x]);
                        if (xs != -1) {
                            tmpSum += xs;
                        }
                    }
                    dp[i] = dp[i - 1] + tmpSum;
                }
            } else {
                if(cur == '*'){
                    int tmpSum = 0;
                    for (int x = 0; x < tmp.length; x++) {
                        int xs =  subcount(tmp[x], prex);
                        if(xs != -1){
                            tmpSum += xs;
                        }
                    }
                    dp[i] = dp[i - 1] + tmpSum + 8;
                }else {
                    int xs = subcount(cur, prex);
                    if(xs == -1) return 0;
                    dp[i] = dp[i-1] + xs;
                }
            }
        }
        for(int i = 0;i<len;i++){
            System.out.print(dp[i] + " ");
        }
        return dp[len - 1];
    }

    public static int subcount(int cur, int prex) {
        // 0/1~9 0/0
        // 1~9/1~9 1~9/0
        if (cur == '0') {
            return (prex == '1' || prex == '2') ? 1 : -1;
        }
        return (prex == '1' || (prex == '2' && cur <= '6')) ? 1 : 0;
    }

    public static void main(String[] args){
        Leetcode_639 l = new Leetcode_639();

        String[] str = new String[]{
                "*",
                "1*",
                "21222*0",
                "2222****3212111",
                "9890*989**0**12",
                "1*",
                "2*",
                "5*",
                "30"
        };
        // 1*
//        for(int i = 0;i<str.length;i++){
//            System.out.println(l.numDecodings(str[i]));
//        }
        // 2: 1
        // 21: 2
        // 212: 3
        // 2122: 4
        // 21222: 5
        // 21222*: 7
        System.out.println(l.numDecodings("21222*0"));
    }
}
