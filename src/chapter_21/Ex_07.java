package chapter_21;

import java.util.*;
import java.util.stream.Collectors;

public class Ex_07 {
    public static void main(String[] args) {
        // Set text in a string
        String text = "Good morning. Have a good class. Have a good visit. Have fun.";

        // Create a TreeMap to hold words as key and count as value
        Map<String, Integer> map = new TreeMap<>();

        String[] words = text.split("[\\s+\\p{P}]");
        for (String word : words) {
            String key = word.toLowerCase();

            if (key.length() > 0) {
                // One-line
                map.put(key, !map.containsKey(key) ? 1 : map.get(key) + 1);
            }
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort(Map.Entry.comparingByValue());

        // A way to get a sorted Map from Java 8
//        Map<String, Integer> sorted = map.entrySet().stream().sorted(Map.Entry.comparingByValue())
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (integer, integer2) -> integer2, LinkedHashMap::new));
        // Display key and value for each entry
        entries.forEach((entry) -> System.out.println(entry.getKey() + "\t" + entry.getValue()));
    }
}
