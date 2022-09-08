package chapter_10;

public class Ex_11 {
    public static void main(String[] agrs) {
        Circle2D c1 = new Circle2D(2, 2, 5.5);
        System.out.println(c1.contains(3, 3));
        System.out.println(c1.contains(new Circle2D(4, 5, 10.5)));
        System.out.println(c1.overlaps(new Circle2D(3, 5, 2.3)));
       
    }

}
