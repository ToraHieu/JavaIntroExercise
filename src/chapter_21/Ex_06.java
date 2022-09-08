package chapter_21;

import java.io.*;
import java.util.*;

public class Ex_06 {
        public static void main(String[] args) {
        // Stimulate command-line input
        args = new String[]{"src/chapter_21/day1", "src/chapter_21/day2", "src/chapter_21/day3",
                "src/chapter_21/day4", "src/chapter_21/day5", "src/chapter_21/day6",};

        TreeMap<String, Integer> attendance = new TreeMap<>();
        try {
            for (String fileName : args) {
                try (Scanner input = new Scanner(new File(fileName))) {
                    while (input.hasNextLine()) {
                        String studentName = input.nextLine().trim();
                        if (!studentName.equals("")) // Avoid blank
                            attendance.put(studentName, !attendance.containsKey(studentName) ? 1 : attendance.get(studentName) + 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File(s) not found.");
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(attendance.entrySet());
        entries.sort(Collections.reverseOrder(Comparator.comparing(Map.Entry::getValue)));

        entries.forEach(entry -> System.out.printf("%-16s\t" + entry.getValue() + "\n", entry.getKey()));
    }
}
