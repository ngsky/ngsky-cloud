package com.ngsky.algorithm.zuo.jj;

public class ConvertToLetterString {
    // 0..idx-1 位置上的字符已经确定
    // idx 位置上的字符有两种可能：要么idx单独作为一份，要么idx和idx+1合在一起作为一份
    public static int convertTry(char[] str, int idx){
        if(idx == str.length){
            // 所有字符已经确定,那这就是一种方案
            return 1;
        }
        if(str[idx] == '0'){
            return -1;  // 不可能构建成字符
        }
        if(str[idx] == '1'){
            // 方案1：单独作为一份,只需求出idx+1转化的结果即可
            int res = convertTry(str, idx+1);
            // 方案2：联合idx+1构成一份
            if(idx+1<str.length){
                res += convertTry(str, idx+2);
            }
            return res;
        }
        if(str[idx] == '2'){
            int res = convertTry(str, idx+1);
            if(idx+1<str.length && str[idx+1] >= '0' && str[idx+1] <= '6'){
                res += convertTry(str, idx+2);
            }
            return res;
        }
        // 其它情况: 3-9 开始,只有一种方案，自己单独作为一份
        return convertTry(str, idx + 1);
    }

    public static void main(String[] args){
        String str = "1111";
        System.out.println(convertTry(str.toCharArray(), 0));
    }
}
