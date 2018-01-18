package cn.gy.algro;

import java.util.Arrays;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * <p>
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * <p>
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 * Created by gaoyang on 2018/1/18.
 */
public class Lee_132 {

    public int minCut(String s) {
        if (s == null || s.trim().equals("")) {
            return 0;
        }
        int[] minC = new int[s.length()];
        boolean[][] isPa = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            int min = i;
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (j + 1 > i - 1 || isPa[j + 1][i - 1])) {
                    isPa[j][i] = true;
                    min = j == 0 ? 0 : Math.min(min, minC[j - 1] + 1);
                }
            }
            minC[i] = min;
        }
        return minC[s.length() - 1];
    }


    public static void main(String[] args) {
        System.out.println(new Lee_132().minCut("aba"));
    }

}
