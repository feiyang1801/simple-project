package cn.gy.algro;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * <p>
 * Return all possible palindrome partitioning of s.
 * <p>
 * For example, given s = "aab",
 * Return
 * <p>
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 * Created by gaoyang on 2018/1/17.
 */
public class Lee_131 {

    public List<List<String>> partition(String s) {
        List<List<String>> retList = palindList(s, 0, s.length() - 1);
        return retList;
    }

    private static List<List<String>> palindList(String s, int i, int j) {
        List<List<String>> retList = new ArrayList<>();
        if (i == j) {
            List<String> subs = new ArrayList<>();
            subs.add(s.substring(i, i + 1));
            retList.add(subs);
            return retList;
        }
        for (int k = i; k <= j; k++) {
            if (isPalindrome(s, i, k)) {
                if (k == j) {
                    List<String> subs = new ArrayList<>();
                    subs.add(s.substring(i, k + 1));
                    retList.add(subs);
                } else {
                    List<List<String>> subRetList = palindList(s, k + 1, j);
                    for (List<String> list : subRetList) {
                        list.add(0, s.substring(i, k + 1));
                        retList.add(list);
                    }
                }
            }
        }
        return retList;
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
        System.out.println(new Lee_131().partition("aba"));
    }

}
