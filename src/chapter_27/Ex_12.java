package chapter_27;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Ex_12 {
    @SuppressWarnings("OverwrittenKey")
    public static void main(String[] args) {
        // Create a hash set
        Set<String> set = new HashSet<>();

        // Add strings to the set
        set.add("London");
        set.add("Paris");
        set.add("New York");
        set.add("San Francisco");
        set.add("Beijing");
        set.add("New York");

        System.out.println(set);

        // Display the elements in the hash set
        for (String s: set) {
            System.out.print(s.toUpperCase() + " ");
        }

        // Process the elements using a forEach method
        System.out.println();
        set.forEach(e -> System.out.print(e.toLowerCase() + " "));

        System.out.println("\nList: " + setToList(set));
    }

    public static <E> ArrayList<E> setToList(Set<E> s) {
        ArrayList<E> list = new ArrayList<>(s.size());
        list.addAll(s);
        return list;
    }
}
