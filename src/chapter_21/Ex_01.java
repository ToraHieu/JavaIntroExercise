package chapter_21;

import java.util.HashSet;

public class Ex_01 {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        HashSet<String> set1 = new HashSet<>();
        set1.add("Chemistry");
        set1.add("Mathematics");
        set1.add("Biology");
        set1.add("English");

        HashSet<String> set2 = new HashSet<>();
        set2.add("Biology");
        set2.add("English");
        set2.add("Geography");
        set2.add("Physics");

        HashSet<String> temp = (HashSet<String>)set1.clone();
        temp.removeAll(set2);
        System.out.println("(1) " + temp);

        temp = (HashSet<String>)set2.clone();
        temp.removeAll(set1);
        System.out.println("(2) " + temp);

        temp = (HashSet<String>)set1.clone();
        temp.retainAll(set2);
        System.out.println("(3) " + temp);
    }
}
