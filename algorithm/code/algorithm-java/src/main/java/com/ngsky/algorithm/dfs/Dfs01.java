package com.ngsky.algorithm.dfs;

/**
 * 验证数组中的子串累加后能否凑成k
 */
public class Dfs01 {
    public static void main(String[] args){
       int[] arr = {23,1,2,3,-1,3,4,43,23,1,-9,-2,-3,-5,9,8};
       int k = 13;

       Dfs01 d = new Dfs01();
       System.out.println(d.dfs(arr, 0, 0, k));
    }

    public boolean dfs(int[] arr, int idx, int cur, int k){
        if(idx == arr.length) return cur == k;
        if(dfs(arr, idx+1, cur, k)) return true; // 不加arr[idx]
        if(dfs(arr, idx+1, cur+arr[idx], k)) return true; // 加arr[idx]
        return false;
    }
}
