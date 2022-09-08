package chapter_20;

import java.util.*;

public class Ex_10 {
    /** This implement is easy to understand but takes cost in performance. Redesign it if needed. */
    public static void main(String[] args) {
        PriorityQueue<String> queue1 = new PriorityQueue<>(Arrays.asList("Chemistry", "Mathematics", "Biology", "English"));
        PriorityQueue<String> queue2 = new PriorityQueue<>(Arrays.asList("Biology", "English", "Geography", "Physics"));

        ArrayList<String> q1Only = new ArrayList<>();
        ArrayList<String> q2Only = new ArrayList<>();
        ArrayList<String> bothQ = new ArrayList<>();

        for (String s : queue1) {
            if (!queue2.contains(s))
                q1Only.add(s);
            else
                bothQ.add(s);
        }

        for (String s : queue2) {
            if (!queue1.contains(s))
                q2Only.add(s);
        }

        System.out.println("Subjects that are only present in the first stack: " + q1Only);
        System.out.println("Subjects that are only present in the second stack: " + q2Only);
        System.out.println("Subjects that are present in both stack: "+ bothQ);
    }
}
