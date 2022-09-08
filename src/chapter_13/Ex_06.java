package chapter_13;

public class Ex_06 {
    public static void main(String[] args) {
        ComparableCircle c1 = new ComparableCircle(5);
        ComparableCircle c2 = new ComparableCircle(2);
        if (c1.compareTo(c2) >= 1) 
            System.out.println("c1");
        else 
            System.out.println("c2");
    }
}

class ComparableCircle extends Circle implements Comparable<ComparableCircle> {
    public ComparableCircle() {
        super();
    }
    
    public ComparableCircle(double radius) {
        super(radius);
    }

    @Override
    public int compareTo(ComparableCircle c) {
        if (getArea() > c.getArea()) 
            return 1;
        else if (getArea() < c.getArea()) 
            return -1;
        else 
            return 0;
    }
}
