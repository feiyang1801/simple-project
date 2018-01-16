package cn.gy.algro;

/**
 * Given a string, your task is to count how many palindromic substrings in this string.
 * <p>
 * The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
 * Created by gaoyang on 2018/1/16.
 */
public class Lee_647 {

    public int countSubstrings(String s) {
        int count = 0;
        if (s == null || s.isEmpty()) {
            return count;
        }
        for (int i = 0; i < s.length(); i++) {
            count += subPalidromic(s, i, i);
            count += subPalidromic(s, i, i + 1);
        }
        return count;
    }

    private int subPalidromic(String s, int i, int j) {
        int count = 0;
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            count++;
            i--;
            j++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Lee_647().countSubstrings("aba"));
    }

}
