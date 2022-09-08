package chapter_10;

public class Ex_09 {
    public static void main(String[] agrs) {
        Course course1 = new Course("Java Programming");
        Course course2 = new Course("Database System");
        String[] students = {"Kenny", "John", "Tora", "Susan", "Lee", "Mirai"};
        for (int i = 0; i < 3; i++) {
            course1.addStudent(students[i]);
            course2.addStudent(students[i+2]);
        }
        course1.dropStudent("John");       
        
        String[] course1_Students = course1.getStudents();
        String[] course2_Students = course2.getStudents();
        System.out.println("The students of " + course1.getCourseName() + " are " );
        for (int i = 0; i < course1.getNumberOfStudents(); i++) {
            System.out.print(course1_Students[i] + " ");
        }
        System.out.println();
        System.out.println("The students of " + course2.getCourseName() + " are " );
        for (int i = 0; i < course2.getNumberOfStudents(); i++) {
            System.out.print(course2_Students[i] + " ");
        }
        
    }

}
