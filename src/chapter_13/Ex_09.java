package chapter_13;

public class Ex_09 {
    public static void main(String[] args) {
        Circle09 c1 = new Circle09(5);
        Circle09 c2 = new Circle09(3);
        System.out.println(c1.compareTo(c2) + " " + c1.equals(c2));
        c2.setRadius(5);
        System.out.println(c1.compareTo(c2) + " " + c1.equals(c2));
    }
}

class Circle09 extends GeometricObject implements Comparable<Circle09> {
    private double radius;

    public Circle09() {
        this(1.0);
    }

    public Circle09(double radius) {
        this.radius = radius;
    }

    /** Return radius */
    public double getRadius() {
        return radius;
    }

    /** Set a new radius */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override /** Return area */
    public double getArea() {
        return radius * radius * Math.PI;
    }

    /** Return diameter */
    public double getDiameter() {
        return 2 * radius;
    }

    @Override /** Return perimeter */
    public double getPerimeter() {
        return 2 * radius * Math.PI;
    }

    /* Print the circle info */
    public void printCircle() {
        System.out.println("The circle is created " + getDateCreated() + " and the radius is " + radius);
    }
    
    @Override
    public int compareTo(Circle09 circle) {
        if (this.radius > circle.radius) {
            return 1;
        } else if (this.radius < circle.radius) 
            return -1;
        else 
            return 0;
    }
    
    @Override 
    public boolean equals(Object circle) {
        if (circle instanceof Circle09) {
            if (this.radius == ((Circle09)(circle)).radius) 
                return true;
            else 
                return false;
        } else 
            return false;
    }
}
