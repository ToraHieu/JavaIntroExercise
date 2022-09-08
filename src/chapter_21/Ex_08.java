package chapter_21;

import java.util.Map;
import java.util.TreeMap;

public class Ex_08 {
    public static void main(String[] args) {
        // Set text in a string
        String text = "Good morning. Have a good class. Have a good visit. Have fun.";

        // Create a TreeMap to hold words as key and count as value
        Map<String, Integer> map = new TreeMap<>();

        String[] words = text.split("[\\s{}\\[\\]*.,;?'\")(]");
        for (String word : words) {
            if (word.length() > 0 && Character.isLetter(word.charAt(0))) {
                String key = word.toLowerCase().trim();
                // One-line
                map.put(key, !map.containsKey(key) ? 1 : map.get(key) + 1);
            }
        }

        // Display key and value for each entry
        map.forEach((k, v) -> System.out.println(v + "\t" + k));
    }
}
