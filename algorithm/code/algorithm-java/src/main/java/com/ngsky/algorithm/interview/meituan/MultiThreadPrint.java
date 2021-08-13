package com.ngsky.algorithm.interview.meituan;

/**
 * 有三个线程，A，B，C
 * 要求A打印a，B打印b，C打印c
 * 给定一个N，循环打印N次，结果类似：abcabcabc...
 */
public class MultiThreadPrint {
    private static volatile int count = 1;

    private static volatile int curT = 1;

    public static void main(String[] args){
        int N = 10;

        Thread A = new Thread(){
          public void run(){
            while(count <= N) {
                if(curT == 1 && count < N){
                    System.out.print("a");
                    curT++;
                }
            }
          }
        };
        Thread B = new Thread(){
            public void run(){
                while(count <= N) {
                    if(curT == 2){
                        System.out.print("b");
                        curT++;
                    }
                }
            }
        };
        Thread C = new Thread(){
            public void run(){
                while(count <= N) {
                    if(curT == 3){
                        System.out.print("c");
                        curT = 1;
                        count++;
                    }
                }
            }
        };
        A.start();
        B.start();
        C.start();
    }
}
