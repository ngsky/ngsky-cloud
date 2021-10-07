package com.ngsky.algorithm.zuo.a2021.a10;

public class A10_07_TopKTimes {
    public static void main(String[] args){
       int a = 10;
       t(a++);
       System.out.println("main:"+a);
    }

    public static void t(int i){
        System.out.println("t:"+i);
    }
}
