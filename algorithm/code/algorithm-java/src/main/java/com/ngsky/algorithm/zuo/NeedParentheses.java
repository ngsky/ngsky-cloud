package com.ngsky.algorithm.zuo;

/**
 * 括号匹配问题
 */
public class NeedParentheses {

   // 1.括号匹配问题，给定一个字符串，每个字符规定为左括号(，或者右括号),判断这个字符串括号是否是匹配的也就左右括号正常匹配
   public static boolean need(String str){
      if(null == str || str.length() < 2) return false;
      int count = 0;
      char[] chs = str.toCharArray();
      for(int i = 0;i<chs.length;i++){
         // 遇到左括号count++, 遇到右括号count--;
         if(chs[i] == '('){
            count++;
         } else {
            count--;
         }
         if(count < 0) return false;  // 右括号数量多余左括号数量，直接返回false
      }
      return count == 0;
   }

   // 2.给定一个括号字符串，如果括号无效，那么至少添加几个字符能够让字符串整体有效
   // 遇到左括号，count++，遇到右括号，count--，如果count为-1，表示右括号比左括号多一个，让need++,并且count重置为0
   // 遍历完所有字符后，让count+need表示至少需要的字符数，因为count++表示左括号多的数量，need加表示右括号多的数
   public static int need2(String str){
      if(str == null) return -1;
      int len = str.length();
      if(len == 0) return 2;
      if(len == 1) return 1;
      int count = 0, need = 0;
      char[] chs = str.toCharArray();
      for(int i = 0;i<len;i++){
         if(chs[i] == '('){
            count++;
            continue;
         }
         if(chs[i] == ')' && count == 0) {
            need++;
            continue;
         }
         count--;
      }
      return count + need;
   }

   // 3.返回个括号字符串中，最长的有效括号字串的长度 [动态规划]
   // ((()))()()()
   // ()()()
   // dp[i]表示：以i位置结尾的括号最长有效字符串长度,这样的话，如果遇到i位置是左括号，那么一定是0，因为它是无效的
   //           如果遇到i位置是右括号，那么需要看一下前一个位置dp[i-1]最长是多长，然后向前推dp[i-1]的前一个位置，
   //           如果i - dp[i-1] - 1 的位置上是左括号，那么刚刚可以和当前右括号配对，那么i位置最长有效括号至少是 2 + dp[i-1],
   //           还需要再向前推进一个位置，看一下 i - dp[i-1] -1 的位置上最长有效是多少，因为会出现()((()))这种情况，其实最开始的()也需要算上
   public static int maxValidLength(String str) {
      if(str == null || str.length() < 2) return 0;
      char[] chs = str.toCharArray();
      int len  = str.length();
      int pre = 0, max = 0;
      int[] dp = new int[len];
      // dp[0]=0, 因为0位置上，最长有效括号字串一定是0
      for(int i = 1;i<len;i++){
        // 只有当当前位置为右括号时才考虑
         if(chs[i] == ')'){
            pre = i - dp[i-1] - 1;
            if(pre >= 0 && chs[pre] == '(') {
               dp[i] = dp[i-1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
            }
         }
         max = Math.max(max, dp[i]);
      }
      return max;
   }

   public static void main(String[] args){
//      String str = "((()))()()()";
//      String str = "((())()()()";
      String str = ")))()((()))";
      System.out.println(need(str));

      System.out.println(need2(str));

      System.out.println(maxValidLength(str));
   }
}
