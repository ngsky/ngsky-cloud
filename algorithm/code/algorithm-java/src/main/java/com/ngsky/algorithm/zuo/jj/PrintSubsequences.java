package com.ngsky.algorithm.zuo.jj;
import java.util.*;

/**
 * 打印字符串的所有字串
 * 字串：连续不可跳跃
 *
 * 二叉思想`
 */
public class PrintSubsequences {
    // str: 原字符串，idx：当前想要操作的位置, path：记录每条支路结果，cur：当前支路构成的子串`
    public static void subsequence(char[] str, int idx, List<String> path, String cur){
        if(idx == str.length){
            path.add(cur);
            return;
        }
        // 不选idx位置
        subsequence(str, idx+1, path, cur);
        // 选择idx位置
        subsequence(str, idx+1, path, cur + String.valueOf(str[idx]));
    }

    // 打印所有子串，但是需要去除掉字面值相同的子串
    // 利用 HashSet 无序不可重复的特性过滤数据
    public static void printNoRepeat(char[] str, int idx, HashSet<String> set, String path) {
        if(idx == str.length){
            set.add(path); // 其实这里add的时候相当于去除掉重复的子串
            return;
        }
        // 不选idx位置的字符
        printNoRepeat(str, idx + 1, set, path) ;
        // 选择idx位置的字符
        printNoRepeat(str, idx + 1, set, path + String.valueOf(str[idx]));
    }

    public static void main(String[] args){
        String str = "abcaaa";
        List<String> ans = new ArrayList<>();
        subsequence(str.toCharArray(), 0, ans, "");
        System.out.println("二叉思想打印所有子串:");
        for(int i = 0;i<ans.size();i++){
            System.out.println(ans.get(i));
        }

        HashSet<String> res = new HashSet<>();
        printNoRepeat(str.toCharArray(), 0, res, "");
        System.out.println("二叉思想打印所有子串，但是要求去除字面值相同的子串:");
        for(String s : res){
           System.out.println(s);
        }
    }
}
