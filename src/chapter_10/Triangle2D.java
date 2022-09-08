package chapter_10;

/** Lack of source materials from the book (10th Edition). This exercise/class is unfinished.*/
public class Triangle2D {
    MyPoint p1;
    MyPoint p2;
    MyPoint p3;
    
    /** Constructor with default points (0,0), (1,1), (2,5)*/
    Triangle2D() {
        p1 = new MyPoint(0,0);
        p2 = new MyPoint(1,1);
        p3 = new MyPoint(2,5);
        
    }
    
    /** Constructor with default specified points*/
    Triangle2D(MyPoint p1, MyPoint p2, MyPoint p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    
    public void setP1(MyPoint p1) {
        this.p1 = p1;
    }
    
    public MyPoint getP1() {
        return p1;
    }
    
    public void setP2(MyPoint p2) {
        this.p2 = p2;
    }
    
    public MyPoint getP2() {
        return p2;
    }
    
    public void setP3(MyPoint p3) {
        this.p3 = p3;
    }
    
    public MyPoint getP3() {
        return p3;
    }
    
    public double getArea() {
        double area = 0;
        double side1 = p1.distance(p2);
        double side2 = p1.distance(p3);
        double side3 = p2.distance(p3);
        double s = (side1 + side2 + side3) / 2;
        
        area = Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
        
        return area;
    }
    
    public double getPerimeter() {
        double perimeter;
        double side1 = p1.distance(p2);
        double side2 = p1.distance(p3);
        double side3 = p2.distance(p3);
        perimeter = side1 + side2 + side3;
        
        return perimeter;
    }
    
    /** Not implemented.*/
    public boolean contains(MyPoint p) {
        
        return false;
    }
    
    /** Not implemented.*/
    public boolean contains( Triangle2D t) {
        
        return false;
    }
    
    /** Not implemented.*/
    public boolean overlaps( Triangle2D t) {
        
        return false;
    }
    
    
    

}
