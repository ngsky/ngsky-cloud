package com.ngsky.algorithm.frame;

/**
 * 优化存储大小 将为 O(1)
 */
public class Fib3 {
    private static int fib(int n){
        if(n < 0) return 0;
        if(n == 2 || n == 1) return 1;
        int s = 1,e = 1;
        for(int i = 3;i <=n;i++) {
            int sum = s + e;
            s = e;
            e = sum;
        }
        return e;
    }

    public static void main(String[] args) {
        System.out.println(fib(30));
    }
}
