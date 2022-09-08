package chapter_30;

import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Ex_05 {
    public static void main(String[] args) {
        new Random().ints(100, 0, 10).boxed()
                .collect(Collectors.groupingBy(e -> e, TreeMap::new, Collectors.counting()))
                .forEach((k, v) -> System.out.println(k + ": " + v));
    }
}