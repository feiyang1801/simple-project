package cn.gy.algro;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string which consists of lowercase or uppercase letters,
 * find the length of the longest palindromes that can be built with those letters.
 * Created by gaoyang on 2018/1/16.
 */
public class Lee_409 {

    public int longestPalindrome(String s) {
        Set<Character> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                set.remove(s.charAt(i));
                count++;
            } else {
                set.add(s.charAt(i));
            }
        }
        if (!set.isEmpty()) {
            return count * 2 + 1;
        } else {
            return count * 2;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Lee_409().longestPalindrome("abccccdd"));
    }

}
