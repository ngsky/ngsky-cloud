package com.ngsky.algorithm.interview.meituan;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock + Condition 实现
 *
 * 采用A打印完通知B，B打印完通知C，C打印完通知A这种机制
 *
 * 首先设置一个全局锁，每次进入方法都获取全局锁，不满足条件的话,利用condition.await() 方法阻塞,当打印完成后利用condition.signal()通知
 *
 * 问题：Condition.await() 方法会不会释放当前锁资源？？？
 *
 * Condition由Lock对象创建的，当调用condition.await() 方法的时候，当前线程会进入阻塞状态，
 * 并且自动释放Condition关联的lock锁资源
 * 当线程收到signal的时候会被唤醒
 */
public class MultiThreadPrint4 {
    private Lock lock = new ReentrantLock();
    private volatile int flag = 1;
    private Condition ca = lock.newCondition();
    private Condition cb = lock.newCondition();
    private Condition cc = lock.newCondition();

    private void printA(){
        try{
           lock.lock(); // 获取锁资源
           if(flag != 1) {
               try{
                   // flag!=1，说明A不能打印，应该阻塞
                   ca.await();
               }catch(InterruptedException e){
                   e.printStackTrace();
               }
           }
           // flag == 1,说明A可以打印
            System.out.print("a");
           flag++;
           // 通知B线程执行
            cb.signal();
        }finally{
          lock.unlock();
        }
    }

    public void printB(){
        try{
            lock.lock();
            if(flag!=2){
                try{
                    // flag!=2,B阻塞
                    cb.await();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            // flag==2,B可以打印,打印完通知C进行打印
            System.out.print("b");
            flag++;
            cc.signal();
        }finally{
           lock.unlock();
        }
    }

    public void printC(){
        try{
            lock.lock();
            if(flag!=3){
                try{
                    cc.await();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.print("c");
            flag=1;
            ca.signal();
        }finally{
           lock.unlock();
        }
    }

    public static void main(String[] args){
        MultiThreadPrint4 mt = new MultiThreadPrint4();

        int N = 10;

        Thread a = new Thread(){
            public void run(){
                for(int i = 0;i<N;i++){
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
