package cn.gy.algro;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
 * Find and return the shortest palindrome you can find by performing this transformation.
 * <p>
 * For example:
 * <p>
 * Given "aacecaaa", return "aaacecaaa".
 * <p>
 * Given "abcd", return "dcbabcd".
 * Created by gaoyang on 2018/1/18.
 */
public class Lee_214 {

    public String shortestPalindrome(String s) {
        char[] chars = s.toCharArray();
        StringBuilder appStr = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (isPalindrome(s, 0, i)) {
                appStr.delete(0, appStr.length());
            } else {
                appStr.append(chars[i]);
            }
        }
        appStr.reverse().append(s);
        return appStr.toString();
    }

    private static boolean isPalindrome(String s, int i, int j) {
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Lee_214().shortestPalindrome("aacecaaa"));
    }

}
