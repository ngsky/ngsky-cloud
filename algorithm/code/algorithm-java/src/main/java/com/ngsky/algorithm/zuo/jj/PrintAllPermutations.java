package com.ngsky.algorithm.zuo.jj;
import java.util.*;

/**
 * 全排列
 *
 * 利用回溯
 */
public class PrintAllPermutations {
    // idx: 0->idx-1 范围内的全排列位置已经定好了，
    // idx -> str.length 范围内任意位置元素都可以占用idx位置，构成一种一情况
    private static void permutation(char[] str, int idx, List<String> ans){
        if(idx == str.length){
            ans.add(String.valueOf(str)); // 因为每次调整的是整个数组，因此最终形成结果就是str
            return;
        }
        for(int j = idx;j < str.length;j++){
            swap(str, idx, j);  // j 位置字符占用 idx位置
            permutation(str, idx + 1, ans); // 下一个元素
            swap(str, idx, j) ; // 回溯，需要回到上一步位置
        }
    }

    // 去除字面值相同的全排列序列
    // 要求str必须全是 a - z 的字符
    public static void permutationNoRepeat(char[] str, int idx, List<String> ans){
        if(idx == str.length){
            ans.add(String.valueOf(str));
            return;
        }
        boolean[] visit = new boolean[26]; // 字符a-z,映射成哈希0-26索引，比如 字符f， f-a = 5
        for(int j = idx;j<str.length;j++){
            if(!visit[str[j] - 'a']) {
                swap(str, idx, j);
                visit[str[j] - 'a'] = true; // 表示str[j]字符已经到过j位置
                permutationNoRepeat(str, idx + 1, ans);
                swap(str, idx, j);
            }
        }
    }

    private static void swap(char[] str, int i, int j){
        char t = str[i];
        str[i] = str[j];
        str[j] = t;
    }

    public static void main(String[] args){
        String str = "aaceb";
        List<String> ans = new ArrayList<>();
        permutation(str.toCharArray(), 0, ans);
        System.out.println("全排序:");
        for(int i = 0;i<ans.size();i++){
            System.out.println(ans.get(i));
        }

        List<String> res = new ArrayList<>();
        permutationNoRepeat(str.toCharArray(), 0, res);
        System.out.println("去除字面值相同的全排列：");
        for(int i = 0;i<res.size();i++){
            System.out.println(res.get(i));
        }
    }
}
