package cn.gy.algro;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a circular array (the next element of the last element is the first element of the array),
 * print the Next Greater Number for every element.
 * The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number.
 * If it doesn't exist, output -1 for this number.
 * Created by gaoyang on 2018/1/11.
 */
public class Lee_503 {

    public static int[] nextGreaterElements(int[] nums) {
        int[] next = new int[nums.length];
        Arrays.fill(next, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * nums.length; i++) {
            int num = nums[i % nums.length];
            while (!stack.isEmpty() && num > nums[stack.peek()]) {
                next[stack.pop()] = num;
            }
            if (i < nums.length) {
                stack.push(i);
            }
        }
        return next;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,3,2};
        int[] next = nextGreaterElements(nums);
        System.out.println(Arrays.toString(next));
    }

}
