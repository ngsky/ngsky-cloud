package com.ngsky.algorithm.zuo;

/**
 * 判断一个数子是否是回文数
 * 时间复杂度：O（N）
 */
public class Palindrome {

    // 121 : 0<>len-1, 1<>len-2, 2<>len-3
    // 1221: 0<>len-1, 1<>len-2
    public static boolean isPalindrome(int num){
        if(num < 0) num = -1 * num;
       // 先搞个数组，将int数字拆分到数组中，最大20亿多一点，也就是 9个0，10位即可
       int[] ns = new int[10];
       int si = -1, idx = 9, i = 0;
       while(idx >= 0){
           // 确定几个0
           int zero = 1;
           for(int j = 1;j<=idx;j++){
              zero *= 10;
           }
           // 取模装到 ns 中
           int tmp = num / zero;
           if(si == -1 && tmp > 0){
              si = i;
           }
           ns[i++] = tmp;
           num = num % zero;

           idx--;
       }
       System.out.println("si:" + si);
       printArray(ns);
       return isP(ns, si);
    }

    public static boolean isP(int[] ns, int si){
        if(si == -1) return true;
        int l = si, r = ns.length - 1;
        while(l < r) {
            if(ns[l++] != ns[r--]) return false;
        }
        return true;
    }

    public static void main(String[] args){
//        int num = 89800;
        int num = 1222342221;
        System.out.println(isPalindrome(num));
    }

    // test
    public static void printArray(int[] ns){
        for(int i = 0;i<ns.length;i++){
            System.out.print(ns[i] + " ");
        }
        System.out.println();
    }
}
