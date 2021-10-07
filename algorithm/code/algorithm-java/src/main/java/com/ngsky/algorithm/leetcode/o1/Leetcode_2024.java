package com.ngsky.algorithm.leetcode.o1;

public class Leetcode_2024 {
    public int characterReplacement(String s, int k) {
        int maxf = 0, i = 0, n = s.length(), count[] = new int[26];
        for (int j = 0; j < n; ++j) {
            maxf = Math.max(maxf, ++count[s.charAt(j) - 'A']);
            if (j - i + 1 > maxf + k)
                --count[s.charAt(i++) - 'A'];
        }
        return n - i;
    }

    public static void main(String[] args){
        Leetcode_2024 lc = new Leetcode_2024();

        String s = "TTFTTFFFFTTT";
        int k = 3;
        int max = lc.characterReplacement(s, k);
        System.out.println(max);
    }
}
