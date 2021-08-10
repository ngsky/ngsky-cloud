package com.ngsky.algorithm.zuo.jj;

public class BitYS {
    public static void main(String[] args){
//        int a = 9;
//        int b = 300;
//        int c = a ^ b;
//        int d = c ^ a;
//        int e = c ^ b;
//        System.out.println(c);
//        System.out.println(d);
//        System.out.println(e);

//        int a = 2;
//        int b = 3;
//        int c = a << 3; // 2 * 2^3 = 2 * 8 = 16
//        int d = b >> 2; // 2 / (2^2) = 2/4 = 0
//        System.out.println(c);
//        System.out.println(d);

        int a = 7; // 0000 0111
        int b = 4;
        // 1.获取a的第b位
        // 7 >> 4 = 7 / 2^4 = 7 / 16 = 0
        // 0000 0000 &
        // 0000 0001 = 0
        System.out.println((a >> b) & 1);
    }
}
