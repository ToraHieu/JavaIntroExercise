package chapter_11;

public class CkPts_21_a {
    public static void main(String[] agrs) {
        new Person_CkPts_21_a().printPerson();
        new Student_CkPts_21_a().printPerson();
    }
}

class Student_CkPts_21_a extends Person_CkPts_21_a {
    @Override
    public String getInfo() {
        return "Student";
    }
    
}

class Person_CkPts_21_a { 
    public String getInfo() {
        return "Person";
    }
    
    public void printPerson() {
        System.out.println(getInfo());
    }
}
