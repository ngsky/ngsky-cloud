package com.ngsky.algorithm.zuo.jj;

// 汉诺塔问题：有三根塔围成一个三角形，开始时塔1上有n个圆盘，从大到小依次放到塔上，要求将塔1上的圆盘移动到塔2上，在移动过程中
// 必须保证小圆盘压住大圆盘，不能使用大圆盘压住小圆盘。
// 求：最佳移动过程
public class Hanoi {
    // n：n层汉诺塔
    // from: 从哪根塔上移动
    // to:移动到哪根塔上
    // other: 第三根辅助塔
    private static void func(int n, String from, String to, String other) {
        if (n == 1) {  // 只有1个圆盘在from上，那么之间移动即可
            System.out.println("move 1 " + from + "->" + to);
        } else {  // 大于1个圆盘，先将n-1个圆盘移动到other上，再将最后一个圆盘移动到to上，最后再将other上的n-1个圆盘移动到to上即可
            func(n - 1, from, other, to);
            System.out.println("move " + n  + " " + from + "->" + to);
            func(n - 1, other, to, from);
        }
    }

    public static void main(String[] args){
        // 10层汉诺塔，从left移动到right
        func(4, "left", "right", "mid");
    }
}
