package com.ngsky.algorithm.thread.threadlocal;

public class ThreadLocal1 {
    public static void main(String[] args) throws Exception {
        ThreadLocal<Integer> tl = new ThreadLocal<Integer>();
        tl.set(1);
        Thread a = new Thread(){
            public void run(){
                System.out.println("a:" + tl.get());
                tl.set(2);
                System.out.println("a set:" + tl.get());
//                System.out.println(Thread.currentThread().threadLocals);
            }
        };
        Thread b = new Thread(){
            public void run(){
                System.out.println("b:" + tl.get());
                tl.set(3);
                System.out.println("b set:" + tl.get());
//                System.out.println(Thread.currentThread().threadLocals);
            }
        };
        Thread c = new Thread(){
            public void run(){
                System.out.println("c:" + tl.get());
                tl.set(4);
                System.out.println("c set:" + tl.get());
//                System.out.println(Thread.currentThread().threadLocals);
            }
        };
        a.start();
        b.start();
        c.start();

//        a.join();
//        b.join();
//        c.join();
//        System.out.println("main:" + tl.get());
    }
}
