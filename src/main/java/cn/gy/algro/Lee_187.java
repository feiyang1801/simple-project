package cn.gy.algro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG".
 * When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 * <p>
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * Created by gaoyang on 2018/1/16.
 */
public class Lee_187 {

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> rets = new ArrayList<>();
        int[] map = new int[26];
        map[0] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;
        Set<Integer> ones = new HashSet<>();
        Set<Integer> doubles = new HashSet<>();
        for (int i = 0; i < s.length() - 9; i++) {
            int value = 0;
            for (int j = i; j < i + 10; j++) {
                value = value << 2;
                value |= map[s.charAt(j) - 'A'];
            }
            if (!ones.add(value) && doubles.add(value)) {
                rets.add(s.substring(i, i + 10));
            }
        }
        return rets;
    }

    public static void main(String[] args) {
        List<String> rets = new Lee_187().findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
        System.out.println(rets);
    }

}
