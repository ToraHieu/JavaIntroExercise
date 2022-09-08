package chapter_20;

import java.util.ArrayList;
import java.util.Collection;

public class TestForEach {
    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();
        collection.add("New York");
        collection.add("Atlanta");
        collection.add("Dallas");
        collection.add("Madison");

        collection.forEach(e -> System.out.print(e.toUpperCase() + " "));
        // Checkpoint 20.4.2
        // collection.forEach(e -> System.out.print(e.replace(0, 1, e.substring(0, 1).toUpperCase()) + " "));
    }
}
