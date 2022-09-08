package chapter_10;

public class Course {
    private String courseName;
    private String[] students = new String[16];
    private int numberOfStudents;
      
    public Course(String courseName) {
        this.courseName = courseName;
    }
    
    public void addStudent(String student) {
        if (numberOfStudents >= students.length) {
            String[] temp = new String[students.length*2];
            System.arraycopy(students, 0, temp, 0, numberOfStudents);
            students = temp;
        }
        students[numberOfStudents++] = student;
    }
    
    public String[] getStudents() {
        return students;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }  

    public String getCourseName() {
        return courseName;
    }  
    
    public void dropStudent(String student) {
        for (int i = 0; i < students.length; i++) {
            if (students[i].equals(student)) {
                String[] temp = new String[students.length];
                System.arraycopy(students, 0, temp, 0, i);
                System.arraycopy(students, i+1, temp, i, numberOfStudents-i-1);
                students = temp;
                numberOfStudents--;
                return;
            }
        }
    }
    
}
