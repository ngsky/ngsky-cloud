package com.ngsky.algorithm.zuo.jj;

public class MyKMP {
    public static int getIndexOf(String str, String match){
        if(null == str || null == match || str.length() < 1 || str.length() < match.length()) return -1;
        int x = 0, y = 0;
        char[] sc = str.toCharArray();
        char[] mc = match.toCharArray();
        int[] next = getNextArray(mc);
        while(x < sc.length && y < mc.length){
            if(sc[x] == mc[y]){  // 匹配成功，都向右移动
                x++;
                y++;
            }else if(next[y] == -1){ // 匹配失败且y回到位置0(只有位置0时next才会为-1)
                x++;
            }else { // 匹配失败且在match中间某个位置，下次跳转到 next[y] 位置
                y = next[y];
            }
        }
        // 1. x 越界,y没有越界，说明匹配失败,-1
        // 2. x 没有越界，y越界,说明匹配成功,第一个位置: x - y
        // 3. x 越界，y越界，说明匹配成功, 第一个位置: x - y
        return y == mc.length ? x - y: -1;
    }

    // 偏难
    public static int[] getNextArray(char[] match){
        if(match.length == 1) return new int[]{-1};
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int idx = 2; // 从第三个元素开始
        int cn = 0;  // 当idx从2开始时，需要拿 match[idx=2] 与 match[next[i-1]] 进行比较，如果相同，那么将next[i-1]++即可。
        while(idx < next.length){
            if(match[idx-1] == match[cn]){  //
                next[idx++] = ++cn;
            }else if(cn > 0){
                cn = next[cn];
            }else {
                next[idx++] = 0;
            }
        }
        return next;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
