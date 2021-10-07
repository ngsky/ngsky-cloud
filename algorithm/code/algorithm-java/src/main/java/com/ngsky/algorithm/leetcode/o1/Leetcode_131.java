package com.ngsky.algorithm.leetcode.o1;

import java.util.*;
public class Leetcode_131 {
    public List<List<String>> partition(String s) {
        // aabcbaa
        // DP: 动态规划
        // a                   [a]
        // a,aa                [a,a],[aa]
        // b,                  [a,a,b],[aa,b]
        // c,                  [a,a,b,c],[aa,b,c]
        // b,bcb               [a,a,b,c,b],[aa,b,c,b]...[a,a,bcb],[aa,bcb]
        // a,abcba             [a,a,b,c,b,a],[aa,b,c,b,a],[a,a,bcb,a],[aa,bcb,a]...[a,abcba]
        // a,aa,aabcbaa        ...
        char[] chs = s.toCharArray();
        int len = chs.length;
        List<List<List<String>>> ans = new ArrayList<>();
        List<String> zero = new ArrayList<>();
        zero.add(chs[0] + "");
        List<List<String>> zeroList = new ArrayList<>();
        zeroList.add(zero);
        ans.add(zeroList);

        for(int i=1;i<len;i++){
           // 1.拿到上一条记录，往上一条记录中添加当前值
           List<List<String>> newLast = new ArrayList<>();
           for(List<String> ls: ans.get(i-1)){
              List<String> newLs = new ArrayList<>(ls);
              newLs.add(chs[i] + "");
              newLast.add(newLs);
           }
           // 2.循环和上一个字符组合看看能否构成回文，如果可以，那么计算相应的位置，在构建一个子串，直到位置到达0
            String tmp = chs[i] + "";
            for(int j = i-1;j>=0;j--){
                tmp = chs[j] + tmp;
                if(palindrome(tmp)){
                    if(i - tmp.length() >= 0){
                        for(List<String> ls: ans.get(i-tmp.length())){
                            List<String> newLs = new ArrayList<>(ls);
                            newLs.add(tmp);
                            newLast.add(newLs);
                        }
                    }else {
                       // 只添加tmp即可
                        List<String> newLs = new ArrayList<>();
                        newLs.add(tmp);
                        newLast.add(newLs);
                    }
                }
            }
            ans.add(newLast);
        }
        return ans.get(ans.size() - 1);
    }

    public static boolean palindrome(String str) {
        int l = 0, r = str.length() - 1;
        char[] chs = str.toCharArray();
        while(l < r){
            if(chs[l++] != chs[r--]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        Leetcode_131 lc = new Leetcode_131();

//        String s = "aabcbaa";
        String s = "a";
        List<List<String>> ans = lc.partition(s);
        System.out.println(ans);
    }
}
