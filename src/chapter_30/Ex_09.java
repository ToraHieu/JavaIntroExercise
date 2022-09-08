package chapter_30;

import java.util.*;

public class Ex_09 {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter the number of students: ");
            int n = in.nextInt();
            List<Student> list = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                System.out.print("Student number " + i + "'s name: ");
                String name = in.next();
                System.out.print("Student number " + i + "'s score: ");
                double score = in.nextDouble();
                list.add(new Student(name, score));
            }
            list.stream()
                    .sorted((s1, s2) -> (int) (s2.getScore() - s1.getScore()))
                    .forEach(s -> System.out.println(s.getName()));
        }
    }

    static class Student {
        private final String name;
        private final double score;

        public Student(String name, double score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public double getScore() {
            return score;
        }
    }
}
