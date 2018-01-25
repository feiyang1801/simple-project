package cn.gy.algro;

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

    //通过不了时间限制
    public String shortestPalindromeV2(String s) {
        char[] chars = s.toCharArray();
        StringBuilder appStr = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (isPalindromeV2(s, 0, i)) {
                appStr.delete(0, appStr.length());
            } else {
                appStr.append(chars[i]);
            }
        }
        appStr.reverse().append(s);
        return appStr.toString();
    }

    private static boolean isPalindromeV2(String s, int i, int j) {
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
        System.out.println(new Lee_214().shortestPalindrome("abc"));
    }

    //新版本
    public String shortestPalindrome(String s) {
        String es = s + "#" + new StringBuilder(s).reverse();
        int[] next = getNext(es);
        return new StringBuilder(s.substring(next[es.length() - 1] +1)).reverse() + s;
    }

    private int[] getNext(String s) {
        int[] next = new int[s.length()];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < s.length() - 1) {
            if (k == -1 || s.charAt(j) == s.charAt(k)) {
                k++;
                j++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

}
