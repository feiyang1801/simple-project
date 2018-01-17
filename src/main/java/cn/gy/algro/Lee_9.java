package cn.gy.algro;

/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 * Created by gaoyang on 2018/1/17.
 */
public class Lee_9 {

    public boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        int tail = 0;
        while (x > tail) {
            tail = tail * 10 + x % 10;
            x = x / 10;
        }
        return x == tail || x == tail / 10;
    }

    public static void main(String[] args) {
        System.out.println(new Lee_9().isPalindrome(10));
    }

}
