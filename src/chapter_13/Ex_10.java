package chapter_13;

public class Ex_10 {
    public static void main(String[] args) {
        Rectangle10 r1 = new Rectangle10(4, 5);
        Rectangle10 r2 = new Rectangle10(4, 6);
        System.out.println(r1.compareTo(r2) + " " + r1.equals(r2));
        r2.setHeight(5);
        System.out.println(r1.compareTo(r2) + " " + r1.equals(r2));

    }
}

class Rectangle10 extends GeometricObject implements Comparable<Rectangle10> {
    private double width;
    private double height;

    public Rectangle10() {
    }

    public Rectangle10(double width, double height) {
        this.width = width;
        this.height = height;
    }

    /** Return width */
    public double getWidth() {
        return width;
    }

    /** Set a new width */
    public void setWidth(double width) {
        this.width = width;
    }

    /** Return height */
    public double getHeight() {
        return height;
    }

    /** Set a new height */
    public void setHeight(double height) {
        this.height = height;
    }

    @Override /** Return area */
    public double getArea() {
        return width * height;
    }

    @Override /** Return perimeter */
    public double getPerimeter() {
        return 2 * (width + height);
    }
    
    @Override
    public int compareTo(Rectangle10 r) {
        if (this.getArea() > r.getArea()) 
            return 1;
        else if (this.getArea() < r.getArea()) 
            return -1;
        else 
            return 0;
    }
    
    @Override
    public boolean equals(Object rectangle) {
        if (rectangle instanceof Rectangle10) {
            if (this.getArea() == ((Rectangle10)(rectangle)).getArea()) 
                return true;
            else 
                return false;
        } else 
            return false;
    }
}