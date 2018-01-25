package cn.gy.algro;

/**
 * Given a non-empty string s, you may delete at most one character.
 * Judge whether you can make it a palindrome.
 * Created by gaoyang on 2018/1/19.
 */
public class Lee_680 {

    public boolean validPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int head = 0;
        int tail = s.length() - 1;
        while (head <= tail) {
            if (s.charAt(head) == s.charAt(tail)) {
                head++;
                tail--;
            } else {
                return isSubPalindrome(s, head, tail - 1) || isSubPalindrome(s, head + 1, tail);
            }
        }
        return true;
    }

    private static boolean isSubPalindrome(String s, int i, int j) {
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
        System.out.println(new Lee_680().validPalindrome("abcdbcba"));
    }

}
