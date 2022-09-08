package chapter_21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

public class Ex_02 {
    public static void main(String[] args) {
        // Stimulate command-line input
        args = new String[]{"src/chapter_21/day1", "src/chapter_21/day2", "src/chapter_21/day3",
                "src/chapter_21/day4", "src/chapter_21/day5", "src/chapter_21/day6",};

        TreeSet<String> attendance = new TreeSet<>();
        try {
            for (String fileName : args) {
                try (Scanner input = new Scanner(new File(fileName))) {
                    while (input.hasNextLine()) {
                        String studentName = input.nextLine().trim();
                        if (!attendance.contains(studentName) && !studentName.equals("")) // Avoid duplicate and blank
                            attendance.add(studentName);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File(s) not found.");
        }
        attendance.forEach(System.out::println);
    }
}
