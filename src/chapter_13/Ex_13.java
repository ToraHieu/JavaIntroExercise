package chapter_13;

public class Ex_13 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Course c1 = new Course("Java");
        c1.addStudent("A");
        c1.addStudent("B");
        c1.addStudent("C");
        Course c2 = (Course) c1.clone();
        c2.addStudent("D");
        System.out.println(c1 == c2);
        String[] s1 = c1.getStudents();
        String[] s2 = c2.getStudents();
        System.out.println((s1 == s2) + "\n" + s1.toString() + "\n" + s2.toString());
        for (String s: s2) {
            System.out.print(s);
        }
    }
}

class Course implements Cloneable {
    private String courseName;
    private String[] students = new String[100];
    private int numberOfStudents;
      
    public Course(String courseName) {
      this.courseName = courseName;
    }
    
    public void addStudent(String student) {
      students[numberOfStudents] = student;
      numberOfStudents++;
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
      // Left as an exercise in Exercise 9.9
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        Course c = (Course) super.clone();
        c.students = (String[]) (this.students.clone());
        return c;
        
    }
  }