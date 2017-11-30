package cn.gy;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import sun.nio.cs.UnicodeEncoder;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

/**
 * Created by gaoyang on 2017/11/3.
 */
public class Test {

    public static void main(String[] args) {

        String[] strings = new String[]{"Hello", "你好？?1", "㐀㐂㐄", "にほんご", "조선어",",.?!@#$%^&()", "，。？！＠＃￥％……＆（）"};
        for(String str : strings){
            char[] chars = str.toCharArray();
            for(char c : chars){
//                System.out.println( c + ":" + isChineseByScript(c));
                System.out.println( c + ":" + isEnglish(c));
            }
        }
        System.out.println(Integer.toHexString((int)'a'));
        System.out.println(Integer.toHexString((int)'A'));
        System.out.println(Integer.toHexString((int)'Z'));
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
