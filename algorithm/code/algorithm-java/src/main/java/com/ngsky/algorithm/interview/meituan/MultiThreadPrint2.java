package com.ngsky.algorithm.interview.meituan;

/**
 * 当时面试的时候写的版本
 *
 * 有三个线程，A，B，C
 * 要求A打印a，B打印b，C打印c
 * 给定一个N，循环打印N次，结果类似：abcabcabc...
 */
public class MultiThreadPrint2 {
    private static volatile int ca = 1;
    private static volatile int cb = 1;
    private static volatile int cc = 1;

    public static void main(String[] args){
        int N = 10;

        Thread A = new Thread(){
          public void run(){
            while(ca <= N) {
                if(ca==cb&&ca==cc){
                    System.out.print("a");
                    ca++;
                }
            }
          }
        };
        Thread B = new Thread(){
            public void run(){
                while(cb <= N) {
                    if(cb<ca&&cb==cc){
                        System.out.print("b");
                        cb++;
                    }
                }
            }
        };
        Thread C = new Thread(){
            public void run(){
                while(cc<= N) {
                    if(cc<ca&&cc<cb){
                        System.out.print("c");
                        cc++;
                    }
                }
            }
        };
        A.start();
        B.start();
        C.start();
    }
}
