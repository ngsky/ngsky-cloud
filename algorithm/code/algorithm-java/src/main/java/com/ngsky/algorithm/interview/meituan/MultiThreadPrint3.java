package com.ngsky.algorithm.interview.meituan;

// https://www.cnblogs.com/hoonick/p/10794968.html
/**
 * synchronized + wait + notifyAll 实现
 *
 * 定义三个同步方式，每个同步方法中打印一个字符，在同步方法通过wait方法进行阻塞
 * 每个同步方法的执行通过全局状态flag变量来确定
 *
 * 通过 while循环来等待，每次打印完都通知所有线程
 */
public class MultiThreadPrint3 {
    private volatile int flag = 1;

    private synchronized void printA(){
        // 先阻塞判断是否可以进行打印, flag == 1 表示可以打印,否则阻塞
        while(flag != 1){
           try{
               this.wait();  // 阻塞，直到有其它线程进行通知，notifyAll()/notify()
           } catch(InterruptedException e){
               e.printStackTrace();
           }
        }
        System.out.print("a");
        flag++;
        this.notifyAll();
    }

    private synchronized void printB(){
        // flag == 2 可以打印，并通知其它线程，否则阻塞
        while(flag != 2){
            try{
                this.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.print("b");
        flag++;
        this.notifyAll();
    }

    private synchronized void printC(){
        // flag == 3 可以打印，并通知其它线程，否则阻塞
        while(flag != 3){
            try{
               this.wait();
            }catch(InterruptedException e){
               e.printStackTrace();
            }
        }
        System.out.print("c");
        flag=1;
        this.notifyAll();
    }

    public static void main(String[] args){
        int N = 10;
        MultiThreadPrint3 mt = new MultiThreadPrint3();

        Thread a = new Thread(){
            public void run(){
               for(int i = 0;i<N;i++) {
                  mt.printA();
               }
            }
        };
        Thread b = new Thread(){
            public void run(){
                for(int i = 0;i<N;i++){
                    mt.printB();
                }
            }
        };
        Thread c = new Thread(){
            public void run(){
                for(int i = 0;i<N;i++){
                    mt.printC();
                }
            }
        };
        a.start();
        b.start();
        c.start();
    }
}
