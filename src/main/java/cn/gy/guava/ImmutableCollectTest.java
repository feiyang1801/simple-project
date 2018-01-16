package cn.gy.guava;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

/**
 * Created by gaoyang on 2017/12/28.
 */
public class ImmutableCollectTest {

    static void setTest(){
        Set<Integer> set = ImmutableSet.of(2,3,4);
        System.out.println(set);
        Lists.newArrayList();
    }

    static <T> ArrayList<T> cretate(){
        return new ArrayList<T>();
    }

    public static void main(String[] args) {
        setTest();

    }

}
