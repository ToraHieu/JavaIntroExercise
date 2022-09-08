package chapter_11;

import java.util.ArrayList;

public class Ex_05 {
    public static void main(String[] agrs) {
        Course course1 = new Course("Java Programming");
        Course course2 = new Course("Database System");
        String[] students = {"Kenny", "John", "Tora", "Susan", "Lee", "Mirai"};
        for (int i = 0; i < 3; i++) {
            course1.addStudent(students[i]);
            course2.addStudent(students[i+2]);
        }
        course1.dropStudent("John");   
        
        ArrayList<String> course1_students = course1.getStudents();
        ArrayList<String> course2_students = course2.getStudents();
        
        System.out.println(course1_students.toString());
        System.out.println(course2_students.toString());
    }
}
