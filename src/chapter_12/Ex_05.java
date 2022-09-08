package chapter_12;

public class Ex_05 {
    public static void main(String[] args) {
        try {
            Triangle t1 = new Triangle();
            Triangle t2 = new Triangle(3, 4, 5);
            Triangle t3 = new Triangle(1, 1, 6);
            t1.getArea();
            t2.getArea();
            t3.getArea();
        } 
        catch(IllegalTriangleException e) {
            System.out.println(e.getMessage());
        }
    }
}
