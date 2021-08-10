package com.ngsky.algorithm.leetcode.o1;


import java.util.*;

public class Leetcode_22 {
    public List<String> generateParenthesis(int n) {
        char[] cur = new char[n*2];
        cur[0] = '(';
        cur[(2*n)-1] = ')';
        List<String> ans = new ArrayList<>();
        subparent(1, cur, ans, 1, 1, n);
        return ans;
    }

    public static void subparent(int idx, char[] cur, List<String> ans, int ll, int rr, int n){
        if(idx == (2*n) - 1) {
            ans.add(String.valueOf(cur));
            return;
        }
        int flag = 0;
        // 每个位置两种选择,首尾位置不能选，必须固定 ( )
        // 选 (
        if(ll < n) {   // (()
            cur[idx] = '(';
            flag = 1;
            subparent(idx + 1, cur, ans, ll + 1, rr, n);
        }
        // 选 )
        if(rr < n && ll != rr - 1) {  //  ())(()   ll == rr - 1 说明左边已成对，此时只能选择左
            cur[idx] = ')';
            flag = 2;
            subparent(idx + 1, cur, ans, ll, rr + 1, n);
        }
//        if(flag == 0) return;  // 两个分支都没有进去，说明当前位置无法选择，只能回退到上面进行重新选择
    }

    public static void main(String[] args){
        Leetcode_22 lc = new Leetcode_22();
        int n = 4;
       List<String> ans = lc.generateParenthesis(n);
       for(int i = 0;i<ans.size();i++){
            System.out.println(ans.get(i));
       }
    }
}
