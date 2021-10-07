package com.ngsky.algorithm;

import java.util.LinkedList;
import java.util.*;

public class Test {
    public static void main(String[] args){
//        LinkedList<Integer> ll = new LinkedList<>();
//        long st = System.currentTimeMillis();
//        for(int i = 0;i < Integer.MAX_VALUE;i++){
//            if(i == (Integer.MAX_VALUE >> 4)){
//                System.out.println("GGG");
//            }
//            if(i == (Integer.MAX_VALUE >> 2)){
//                System.out.println("AAA");
//            }
//            if(i == (Integer.MAX_VALUE >> 1)){
//                System.out.println("BBB");
//            }
//            ll.add(i);
//        }
////        for(int i = 0;i < Integer.MAX_VALUE;i++){
////            ll.add(i);
////        }
//        long et = System.currentTimeMillis();
//        System.out.println((et - st) / 1000);
//        System.out.println(ll.size());

//        int x = Integer.MAX_VALUE;
//        System.out.println(x + 1);
//        x = x + 1;
//        System.out.println(x);
//
//        System.out.println(x - 1);
//        System.out.println(x-5);
//        x = x - 1;
//        System.out.println(x);

//        List<Integer> ll = new ArrayList<>();
//        System.out.println(ll.get(9));

//        System.out.println(1<<29);
//        System.out.println((1<<29) - 1);

//        System.out.println(Runtime.getRuntime().availableProcessors());
//        System.out.println('A'-'a');  // -32 -> 26: -32+32 = 0 + 26
//        System.out.println('B'-'a');  // -33 -> 27: -33+32 = -1 -> 1 + 26 = 27
//        System.out.println('Z'-'a');  // -34 -> 28: -34+32 = -2 -> 2 + 26 = 28

        Set<Integer> set = new HashSet<>();
        set.add(5);
        set.add(4);
        set.add(9);
        set.add(10);
        set.add(1);
        set.add(2);
        set.add(4);
        set.add(200);

        Iterator<Integer> it = set.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
