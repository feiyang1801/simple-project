package cn.gy.algro;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by gaoyang on 2018/1/12.
 */
public class Lee_496 {

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] nextNums = new int[nums1.length];
        Arrays.fill(nextNums, -1);
        Map<Integer, Integer> numLoc = new HashMap<>(nums1.length);
        for (int i = 0; i < nums1.length; i++) {
            numLoc.put(nums1[i], i);
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums2.length; i++) {
            int current = nums2[i];
            while (!stack.isEmpty() && current > nums2[stack.peek()]) {
                int baseNum = nums2[stack.pop()];
                if (numLoc.keySet().contains(baseNum)) {
                    nextNums[numLoc.get(baseNum)] = current;
                }
            }
            stack.push(i);
        }
        return nextNums;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 1, 2};
        int[] nums2 = new int[]{1, 3, 4, 2};
        int[] next = nextGreaterElement(nums1, nums2);
        System.out.println(Arrays.toString(next));
    }

}
