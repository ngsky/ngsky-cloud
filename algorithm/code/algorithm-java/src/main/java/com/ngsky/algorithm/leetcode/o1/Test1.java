package com.ngsky.algorithm.leetcode.o1;

public class Test1 {
    private volatile static Object[] queue;

    private volatile static int l, r;

    private static int X = 120;
    public static void main(String[] args){
        int M = 100,N=200;
        l = 0;
        r = 0;
        queue = new Object[X];
        final Test1 t1 = new Test1();
        // 生产
        new Thread(){
           public void run(){
               for(int i = 0;i<N;i++){
                   final Object o = i;
                   new Thread(){
                       public void run(){
                           t1.prod(o);
                       }
                   }.start();
               }
           }
        }.start();

        // 消费
        new Thread(){
            public void run(){
                for(int i = 0;i<M;i++) {
                    new Thread(){
                        public void run(){
                            System.out.println(t1.consume());
                        }
                    }.start();
                }
            }
        }.start();
    }

    public synchronized void prod(Object o){
        if(r >= X) return;
       queue[r++] = o;
    }
    public synchronized Object consume() {
        if(l>=X) return null;
        return queue[l++];
    }
}
