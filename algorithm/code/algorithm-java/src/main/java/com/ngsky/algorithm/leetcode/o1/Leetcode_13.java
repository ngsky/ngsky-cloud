package com.ngsky.algorithm.leetcode.o1;

public class Leetcode_13 {
    public int romanToInt(String s) {
        char[] ch = new char[]{'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int[] chval = new int[]{1, 5,10,50,100,500,1000};

        char[] sc = s.toCharArray();
        int idx = 1;
        int prex = convert(ch, chval, sc[0]);
        int sum = prex;
        int pv = 0; // 标记当前字符是否和上一个字符组成一对
        // IL I能否和L拼接在一起???    IXL [IX L]=59 [I XL] = X
        // 只能从 大到小
        while(idx < sc.length){
            char c = sc[idx];
            if((c == 'V' || c == 'X') && sc[idx-1] == 'I' && pv == 0){  // 上一个I在上一次只能是单独存在
                int cur = convert(ch, chval, c) - 1 - 1;
                prex = cur;
                pv = 1;
                sum += cur;  // 减去上一步加过的值

                idx = idx + 1;
                continue;
            }
            if((c == 'L' || c == 'C') && sc[idx-1] == 'X' && pv == 0){ // IXC 保证从大到小
                int cur = convert(ch, chval, c) - 10 - 10;
                prex = cur;
                pv = 1;
                sum += cur;

                idx = idx + 1;
                continue;
            }
            if((c == 'D' || c == 'M') && sc[idx-1] == 'C' && pv == 0){
                int cur = convert(ch, chval, c) - 100 -100;
                prex = cur;
                pv = 1;
                sum += cur;

                idx = idx + 1;
                continue;
            }
            int cur = convert(ch, chval, c);
            if(cur > prex) return 0;
            prex = cur;
            pv = 0;
            sum += cur;

            idx = idx + 1;
        }
        return sum;
    }

    public static int convert(char[] ch, int[] chval, char c){
        for(int i = 0;i< 7;i++){
            if(c == ch[i]) return chval[i];
        }
        return 0;
    }

    public static void main(String[] args){
        Leetcode_13 lc = new Leetcode_13();

        String str = "IXL";
        System.out.println(lc.romanToInt(str));
    }
}
