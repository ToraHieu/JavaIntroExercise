package chapter_21;

import java.util.*;

public class CkPts_2_10 {
    public static void main(String[] args) {
        Set<String> set = new TreeSet<>(Comparator.comparing(String::length));
        set.add("ABC");
        set.add("ABD");
        System.out.println(set);
    }
}
