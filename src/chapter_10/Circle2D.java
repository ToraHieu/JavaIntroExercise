package chapter_10;

public class Circle2D {
    private double x;
    private double y;
    private double radius;
    
    public Circle2D(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    
    public Circle2D() {
        this(0,0,1);
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double getRadius() {
        return radius;
    }
    
    public double getArea() {
        double area;
        area = radius * radius * Math.PI;
        return area;
    }
    
    public double getPerimeter() {
        // Perimeter is also called Circumference.
        double perimeter;
        perimeter = 2 * Math.PI * radius;
        return perimeter;
    }
    
    public boolean contains(double x, double y) {
        // A point is inside a circle if its distance to the center is less than radius.
        double distance;
        distance = Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
        return distance < radius;
    }
    
    public boolean contains(Circle2D circle) {
        // A circle is inside a circle if their distance <= |r1 - r2|.
        double distance;
        distance = Math.sqrt(Math.pow(x - circle.x, 2) + Math.pow(y - circle.y, 2));
        return distance <= Math.abs(radius - circle.radius);
    }
    
    public boolean overlaps(Circle2D circle) {
        // A circle overlaps another circle if their distance <= (r1 + r2). Note: It need to not be inside the other circle.
        if (this.contains(circle))
            return false;
        double distance;
        distance = Math.sqrt(Math.pow(x - circle.x, 2) + Math.pow(y - circle.y, 2));
        return distance <= radius + circle.radius;
    }

}
