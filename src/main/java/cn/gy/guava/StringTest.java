package cn.gy.guava;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yang.gao on 2016/2/16.
 */
public class StringTest {

//    public static void main(String[] args) {
//        List<String> list = Lists.newArrayList("a","b","c");
//        Joiner joiner = Joiner.on(";").skipNulls();
//        String mergeStr = joiner.join(list);
//        System.out.println(mergeStr);
//        Iterable<String> iterable = Splitter.on(";").trimResults().omitEmptyStrings().split(mergeStr);
//        List<String> list1 = Lists.newArrayList(iterable);
//        System.out.println(list1);
//    }

    public static void preCheck(int n){
        Preconditions.checkArgument(n > 0, "negative value %s",n);
    }

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("a","b","c");
        Function<String,String> function = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        };
        Collection<String> collection = Collections2.transform(list,function);
        System.out.println(collection);
        preCheck(-2);
    }

}
