package com.ngsky.algorithm.zuo;

/**
 * 单词反转：
 * the and. dog
 * =>
 * dog and. the
 */
public class RotateWord {
    public static void rotateWord(char[] chs){
       int len = chs.length;
       if(len < 2) return;
       int l = 0, r = len-1;
       while(l < r){
           char tm = chs[l];
           chs[l++] = chs[r];
           chs[r--] = tm;
       }
       // 0,5
       // 6,9
        int prex = -1;
        for(int i = 0;i < len;i++){
            // 确定出一个单词，然后单词之间交换
            if(chs[i] == ' ' && prex < i){ // 正常 空格
               rotate(chs, prex, i);
               prex = i;
            } else if(i == len-1 && prex < i){  // 结尾单词
                rotate(chs, prex, i+1);
                prex = i;
            }
        }
    }

    public static void rotate(char[] chs, int l, int r){
        l = l + 1;
        r = r - 1;
        while(l < r){
           char tm = chs[l] ;
           chs[l++] = chs[r];
           chs[r--] = tm;
        }
    }

    // test print
    public static void printArray(char[] chs){
        for(int i = 0;i<chs.length;i++){
            System.out.print(chs[i]);
        }
    }

    public static void main(String[] args){
//       char[] chs = "the dog and. hello".toCharArray();
//        char[] chs = "t".toCharArray();
        char[] chs = "the and. dog".toCharArray();
        rotateWord(chs);
        printArray(chs);
    }
}
