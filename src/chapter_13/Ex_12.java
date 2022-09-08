package chapter_13;

public class Ex_12 {
    public static void main(String[] args) {
        GeometricObject[] a = new GeometricObject[4];
        a[0] = new Circle(5);
        a[1] = new Circle(2);
        a[2] = new Rectangle();
        a[3] = new Rectangle(5, 10);
        System.out.println(sumArea(a));
    }
    
    public static double sumArea(GeometricObject[] a) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum+= a[i].getArea();
        }
        return sum;
    }
}
