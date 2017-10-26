
package cn.gy.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 For example,
 [1,1,2] have the following unique permutations:
 [
 [1,1,2],
 [1,2,1],
 [2,1,1]
 ]
 * @author yang.gao created on 2016/9/22 17:51
 * @version $Id$
 */
public class Permutations {

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> list = new LinkedList<Integer>();
        for(int n : nums){
            list.add(n);
        }
        return permute(list.get(0), list.subList(1, list.size()));
    }

    public static List<List<Integer>> permute(int n, List<Integer> subNum){
        List<List<Integer>> retList = new LinkedList<List<Integer>>();
        if(subNum.size() == 0){
            List<Integer> list = new LinkedList<Integer>();
            list.add(n);
            retList.add(list);
            return retList;
        }else{
            List<List<Integer>> subList = permute(subNum.get(0),subNum.subList(1,subNum.size()));
            for(List<Integer> list : subList){
                int ls = list.size();
                int f = 0;
                while (f <= ls){
                    List<Integer> ss1 = new LinkedList<Integer>();
                    List<Integer> ss2 = new LinkedList<Integer>();
                    List<Integer> ss = new LinkedList<Integer>();
                    if(f == 0){
                        ss2.addAll(list);
                    }else if(f == ls){
                        if(list.get(f-1) != n){
                            ss1.addAll(list);
                        }
                    }else{
                        if(list.get(f-1) != n){
                            ss1 = list.subList(0,f);
                            ss2 = list.subList(f,ls);
                        }
                    }
                    if(!ss1.isEmpty() || !ss2.isEmpty()){
                        ss.addAll(ss1);
                        ss.add(n);
                        ss.addAll(ss2);
                        if(!retList.contains(ss)){
                            retList.add(ss);
                        }
                    }
                    f += 1;
                }
            }
            return retList;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2};
        List<List<Integer>> retList = permuteUnique(nums);
        System.out.println(retList);
    }

}
