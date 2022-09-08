package chapter_21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Ex_03 {
    public static void main(String[] args) {
        // Stimulate command-line input
        args = new String[]{"src/chapter_21/day1", "src/chapter_21/day2", "src/chapter_21/day3",
                "src/chapter_21/day4", "src/chapter_21/day5", "src/chapter_21/day6",};

        TreeSet<StudentName> attendAtLeastOnce = new TreeSet<>();
        TreeSet<String> attendMoreThanOnce = new TreeSet<>(); // The result set
        HashSet<String> qualifiedStudentName = new HashSet<>();
        HashSet<String> duplicatedFirstName = new HashSet<>();
        try {
            for (String fileName : args) {
                try (Scanner input = new Scanner(new File(fileName))) {
                    while (input.hasNextLine()) {
                        String fullName = input.nextLine().trim();
                        if (!fullName.equals("")) { // Avoid blank
                            StudentName studentName = new StudentName(
                                    fullName.substring(0, fullName.indexOf(' ')),
                                    fullName.substring(fullName.indexOf(' ') + 1));
                            if (attendAtLeastOnce.contains(studentName)) {
                                /* Found an element in the list, process to find out if this name
                                * is another student with the same first name
                                * or the same previous student. */
                                if (attendMoreThanOnce.contains(studentName.firstName)) {
                                    // Handling the first case of duplicate
                                    // If it isn't in the nonDuplicateName set, that means it's a duplicate
                                    if (!qualifiedStudentName.contains(studentName.getFullName())) {
                                        // Duplicate means this student share the same first name with someone else before them.
                                        // Register this first name as a duplicate
                                        duplicatedFirstName.add(studentName.firstName);

                                        // Add this student's full name to the attendMoreThanOnce set
                                        qualifiedStudentName.add(studentName.getFullName());
                                        attendMoreThanOnce.add(studentName.getFullName());

                                        // Remove the previous first name, then find and add the full name back
                                        attendMoreThanOnce.remove(studentName.firstName);
                                        for (String name: qualifiedStudentName) {
                                            // Find the full name
                                            if (studentName.firstName.equals(name.substring(0, name.indexOf(' ')))) { // Same first name
                                                attendMoreThanOnce.add(name); // Add it in
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    // Attend the second time
                                    qualifiedStudentName.add(studentName.getFullName());
                                    if (duplicatedFirstName.contains(studentName.firstName))
                                        attendMoreThanOnce.add(studentName.getFullName());
                                    else
                                        attendMoreThanOnce.add(studentName.firstName);
                                }
                            }
                            else
                                // Attend the first time
                                attendAtLeastOnce.add(studentName);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File(s) not found.");
        }
        attendMoreThanOnce.forEach(System.out::println);
    }

    static class StudentName implements Comparable {
        String firstName, lastName;

        StudentName(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public int compareTo(Object o) {
            if (firstName.equals(((StudentName)o).firstName))
                return lastName.compareTo(((StudentName)o).lastName);
            else
                return firstName.compareTo(((StudentName)o).firstName);
        }

        @Override
        public String toString() {
            return firstName + " " + lastName;
        }

        public String getFullName() {
            return firstName + " " + lastName;
        }
    }
}
