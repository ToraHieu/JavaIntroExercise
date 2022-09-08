package chapter_20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class CkPts_20_6_6 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Hello", "Is", "It", "Me", "You", "Looking", "For", "?"));
        list.sort(Comparator.comparing(e -> e.charAt(e.length() - 1)));
        System.out.println(list);
    }
}
