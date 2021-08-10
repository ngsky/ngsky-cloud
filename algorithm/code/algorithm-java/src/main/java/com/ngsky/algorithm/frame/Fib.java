package com.ngsky.algorithm.frame;

/**
 * 递归版 斐波那契数列
 */
public class Fib {
    int fib(int n){
        if(n == 1 || n == 2) return 1;
        return fib(n-1) + fib(n-2);
    }

    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        Fib f = new Fib();
        System.out.println(f.fib(30));
        long e = System.currentTimeMillis();
        System.out.println("time:"+(e-s));
    }
}
