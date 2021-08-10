package com.ngsky.algorithm.zuo;

public class MinDistance {
    public static int minDistance(String[] strs, String s1, String s2){
        if(s1 == null || s2 == null) return -1;
        int min = strs.length;
        int prex = 0;
        int f1 = -1, f2 = -1;
        for(int i = 0;i<strs.length;i++){
            if(strs[i] == s1){
                if(s1 == s2) return 0;
                if(prex == 2) {
                   min = Math.min(min, i - f2);
                }
                f1 = i;
                prex = 1;
            }else if(strs[i] == s2){
                if(prex == 1){
                    min = Math.min(min, i - f1);
                }
                f2 = i;
                prex = 2;
            }
        }
        if(f1 == -1 || f2 == -1) return -1;
        return min;
    }

    public static void main(String[] args){
        // 1 3 3 3 2 3 2 1 1
//        String[] strs = {"1", "3", "3", "3","2", "3","1","1"};
//        String s1 = "1";
//        String s2 = "2";

        String[] strs = {"1", "3", "3", "3","2", "3","1","1"};
        String s1 = "1";
        String s2 = "2";
        System.out.println(minDistance(strs, s1, s2));
    }
}
