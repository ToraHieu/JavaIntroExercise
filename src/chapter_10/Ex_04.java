package chapter_10;

public class Ex_04 {
    public static void main(String[] agrs) {
        MyPoint point1 = new MyPoint();
        MyPoint point2 = new MyPoint(10, 30.5);
        System.out.println(point1.distance(point2));
    }
}
