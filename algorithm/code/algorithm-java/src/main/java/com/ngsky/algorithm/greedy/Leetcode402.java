package com.ngsky.algorithm.greedy;

/**
 * <dl>
 * <dt>Leetcode402</dt>
 * <dd>Description:
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 * <p>
 * Note:
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 * Example 1:
 * <p>
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * Example 2:
 * <p>
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 * Example 3:
 * <p>
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 * </dd>
 * <dd>CreateDate: 12/16/2018 3:10 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
public class Leetcode402 {

    public String removeKdigits(String num, int k) {
        if (num == null) return "0";
        int numLen = num.length();
        if (numLen <= 0 || numLen <= k) return "0";
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < numLen; i++) {
            int number = Integer.parseInt(String.valueOf(num.charAt(i)));
            while (ret.length() != 0 && Integer.parseInt(String.valueOf(ret.charAt(ret.length() - 1))) > number && k > 0) {
                ret.deleteCharAt(ret.length() - 1);
                k--;
            }
            if (number != 0 || ret.length() != 0) {
                ret.append(number);
            }
        }
        while (ret.length() > 0 && k > 0) {
            ret.deleteCharAt(ret.length() - 1);
            k--;
        }
        return ret.length() == 0 ? "0" : ret.toString();
    }

    public static void main(String[] args) {
        Leetcode402 let = new Leetcode402();
//        String ret = let.removeKdigits("1432219", 3);
        String ret = let.removeKdigits("10200", 1);
//        String ret = let.removeKdigits("1002", 3);
        System.out.println("ret:" + ret);
    }
}
