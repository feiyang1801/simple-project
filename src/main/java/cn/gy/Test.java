package cn.gy;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import sun.nio.cs.UnicodeEncoder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

/**
 * Created by gaoyang on 2017/11/3.
 */
public class Test {

    public static void main(String[] args) {

        System.out.println(new BigDecimal(30).multiply(BigDecimal.ZERO));
    }


    public static boolean isChineseByScript(char c) {
        Character.UnicodeScript sc = Character.UnicodeScript.of(c);
        if (sc == Character.UnicodeScript.HAN) {
            return true;
        }
        return false;
    }

    public static boolean isEnglish(char c){
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }


}
