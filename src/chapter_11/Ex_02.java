package chapter_11;

import chapter_10.MyDate;

public class Ex_02 {
    public static void main(String[] agrs) {
        Person person = new Person();
        person.setName("Person A");
        
        Student student = new Student();
        student.setName("Student B");
        
        Employee employee = new Employee();
        employee.setName("Employee C");
        
        Faculty faculty = new Faculty();
        faculty.setName("Faculty D");
        
        Staff staff = new Staff();
        staff.setName("Staff E");
        
        System.out.print(person.toString() + "\n" 
                        + student.toString() +"\n"
                        + employee.toString() + "\n"
                        + faculty.toString() + "\n"
                        + staff.toString() + "\n");
    }
}

class Person {
    protected String name = "";
    protected String address;
    protected String phoneNumber;
    protected String emailAddress;
    
    public Person() {
        
    }

    public Person(String name, String address, String phoneNumber, String emailAddress) {
        super();
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public String toString() {
        return "This is a person whose name is " + name;
    }
    
}

class Student extends Person {
    public static final String FRESHMAN = "Freshman";
    public static final String SOPHOMORE = "Sophomore";
    public static final String JUNIOR = "Junior";
    public static final String SENIOR = "Senior";
    
    protected String classStatus;
    
    public Student() {
        
    }

    public Student(String classStatus) {
        super();
        this.classStatus = classStatus;
    }

    public String getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(String classStatus) {
        this.classStatus = classStatus;
    }
    
    @Override
    public String toString() {
        return "This is a student whose name is " + name;
    }
    
}

class Employee extends Person {
    protected String office;
    protected double salary; 
    protected MyDate dateHired;
    
    public Employee() {
        
    }

    public Employee(String office, double salary, MyDate dateHired) {
        super();
        this.office = office;
        this.salary = salary;
        this.dateHired = dateHired;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public MyDate getDateHired() {
        return dateHired;
    }

    public void setDateHired(MyDate dateHired) {
        this.dateHired = dateHired;
    }
    
    @Override
    public String toString() {
        return "This is an employee whose name is " + name;
    }
    
}

class Faculty extends Employee {
    protected double officeHours;
    protected String rank;
    
    public Faculty() {
        
    }
    
    public Faculty(double officeHours, String rank) {
        super();
        this.officeHours = officeHours;
        this.rank = rank;
    }

    public double getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(double officeHours) {
        this.officeHours = officeHours;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
    
    @Override
    public String toString() {
        return "This is a faculty whose name is " + name;
    }
}

class Staff extends Employee {
    protected String title;

    public Staff() {

    }

    public Staff(String title) {
        super();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public String toString() {
        return "This is a staff whose name is " + name;
    }
}