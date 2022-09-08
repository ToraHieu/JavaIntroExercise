package chapter_30;

import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex_18 {
    public static void main(String[] args) {
        // Set text in a string
        String text = "Good morning. Have a good class. Have a good visit. Have fun.";

        String[] words = text.split("[\\s{}\\[\\]*.,;?'\")(]");
        Stream.of(words).parallel()
                .filter(e -> e.length() > 0 && Character.isLetter(e.charAt(0)))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(e -> e, TreeMap::new, Collectors.counting()))
                .forEach((k, v) -> System.out.println(v + "\t" + k));
    }
}
