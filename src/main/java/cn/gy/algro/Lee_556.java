package cn.gy.algro;

import java.util.Arrays;
import java.util.Collections;

/**
 * Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly
 * the same digits existing in the integer n and is greater in value than n.
 * If no such positive 32-bit integer exists, you need to return -1.
 * Created by gaoyang on 2018/1/9.
 */
public class Lee_556 {

    public static int nextGreaterElement(int n) {
        char[] nums = String.valueOf(n).toCharArray();
        int index = nums.length -1;
        for(;index >0; index --){
            if(nums[index] > nums[index - 1]) {
                break;
            }
        }
        if(index == 0){
            return -1;
        }
        int sIndex = index - 1;
        int gap = nums[index] - nums[sIndex];
        for(int i = index + 1; i < nums.length;i++){
            if((nums[i] > nums[sIndex]) && (nums[i] - nums[sIndex] < gap) ){
                index = i;
                gap = nums[i] - nums[sIndex];
            }
        }
        char tmp = nums[index];
        nums[index] = nums[sIndex];
        nums[sIndex] = tmp;
        Arrays.sort(nums,sIndex + 1,nums.length);
        long val = Long.parseLong(new String(nums));
        return (val <= Integer.MAX_VALUE) ? (int) val : -1;
    }

    public static void main(String[] args) {
        System.out.println(nextGreaterElement(2323));
    }

}
