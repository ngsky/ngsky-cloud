package com.ngsky.algorithm.leetcode.o1;

import java.util.*;

public class Leetcode_44 {
    public boolean isMatch(String s, String p) {
        // * / bac* / *?
        if(s == p) return true;
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        int sl = sc.length, pl = pc.length;
        HashMap<String,Boolean> visit = new HashMap<>();
        return subMatch(sc, pc, sl, pl, 0, 0, 0, visit);
    }

    public static boolean subMatch(char[] sc,char[] pc, int sl, int pl, int si, int pi, int prex, HashMap<String,Boolean> visit) {
        if(null != visit.get(si + "_" + pi)) return false;
        while(pi < pl){
            if(pc[pi] == '*') {
                prex = 1;
                pi = pi + 1;
                continue;
            }
            if(pc[pi] != '?' && prex == 1){  // 上一个字符为 *, 寻找到sc中某个字符和当前字符相等的位置，可能需要多次寻找
                boolean findi = false;
                int tmpsi = si;
                boolean subans = false;

                if(si >= sl) break;
                for(int i = si;i<sl;i++){
                    if(pc[pi] == sc[i]){
                        // pi = pi + 1;
                        // si = i + 1;
                        // prex = 0;
                        tmpsi = i + 1;
                        findi = true;

//                        if(i == 199 && pi == 40){
//                            System.out.println("i=" + i + ";pi=" + pi);
//                        }
                        subans = subMatch(sc, pc, sl, pl, i + 1, pi + 1, 0, visit);
                        if(subans) return true;
                        visit.put((i+1) + "_" + (pi+1), false);
                    }
                }
                if(!findi) return false;  // 如果没有找到合适位置，那么直接返回false
                if(!subans) return false;  // 子过程都失败，则失败

                // 这个过程结束，需要跳过
                pi = pi + 1;
                // si 应该是多少 ?
                si = tmpsi;
                continue; // 阻断继续上层循环
            }
            if((si < sl && pc[pi] == sc[si]) || pc[pi] == '?'){
                prex = (pc[pi] == '?') ? prex : 0;
                pi = pi + 1;
                si = si + 1;
                continue;
            }
            return false;
        }
//        System.out.println("--->");
        if(pi == pl && si == sl) return true;
        // 如果 p 已经到最后位置，但是 s 还没有到最后位置
        if(si > sl) return false;
        // si < sl
        if(pc[pl-1] == '*') return true;
        return prex == 1 && pi == pl;  // pc 最后一个位置为*
    }

    public static void main(String[] args){
        //"aa"
        //"a"
        //"aa"
        //"*"
        //"cb"
        //"?a"
        //"adceb"
        //"*a*b"
        //"acdcb"
        //"a*c?b"
        //"hhhh"
        //"*"
        //"a"
        //"*"
        //"tt"
        //"*******"
        //"aaaaaiiiiiiifdfdfd"
        //"**d"
        //"test"
        //"*?"
        Leetcode_44 lc = new Leetcode_44();

        String a = "abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb";
        String b = "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb";
        System.out.println(lc.isMatch(a, b)) ;
    }
}
