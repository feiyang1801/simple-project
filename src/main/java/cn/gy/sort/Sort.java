
package cn.gy.sort;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

/**
 * @author yang.gao created on 2016/5/6 14:53
 * @version $Id$
 */
public class Sort {

    private static void partition(int[] a, int s, int e) {
        int i = s;
        int j = s - 1;
        for (; i < e; i++) {
            if (a[i] <= a[e]) {
                j++;
                replace(a, j, i);
            }
        }
        replace(a, ++j, e);
        if (s < j) {
            partition(a, s, j - 1);
        }
        if (j < e) {
            partition(a, j + 1, e);
        }
    }

    private static void replace(int[] a, int s, int d) {
        int temp = a[d];
        a[d] = a[s];
        a[s] = temp;
    }

    public static void main(String[] args) {
        int[] a = new int[5];
        for(int i = 0;i<a.length;i++){
            a[i] = new Random().nextInt(10);
        }
        partition(a,0,a.length - 1);
        for(int num : a){
            System.out.print(num + ",");
        }
    }

}
