package com.ngsky.algorithm.zuo;

/**
 * 判断两个字符串是否是形近词：出现字符种类相同，每种字符出现频次相同
 */
public class Deformation {
    public static boolean isDeformation(String s1, String s2){
        if((s1 == null && s2 != null) || (s2 == null && s1 != null) || s1.length() != s2.length()) return false;
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int[] times1 = new int[256];
        int[] times2 = new int[256];
        int len = ch1.length;  // len 必须相同
        int f1 = 0, f2 = 0;
        for(int i = 0;i<len;i++){
            if(times1[ch1[i] - 'a'] == 0) f1++;
            if(times2[ch2[i] - 'a'] == 0) f2++;
           times1[ch1[i] - 'a'] += 1;
           times2[ch2[i] - 'a'] += 1;
        }
        if(f1 != f2) return false;
        for(int i = 0;i<times1.length;i++){
            if(times1[i] != times2[i]) return false;
        }
        return true;
    }

    public static void main(String[] args){
       String s1 = "abd";
       String s2 = "dba";
       System.out.println(isDeformation(s1, s2));
    }
}
