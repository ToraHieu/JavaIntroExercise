package chapter_11;

public class Ex_01 {
    public static void main(String[] agrs) {
        Triangle triangle = new Triangle(3, 4, 5);
        triangle.setColor("Blue");
        triangle.setFilled(true);
        System.out.println(triangle.toString() 
                + "\nColor: " + triangle.getColor() 
                + "\nArea: " + triangle.getArea()
                + "\nPerimeter: " + triangle.getPerimeter()
                + "\nFilled: " + triangle.isFilled());
    }

}
