package chapter_10;

public class MyPoint {
    private double x, y;
    
    MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    MyPoint() {
        this(0, 0);
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double distance(MyPoint point) {
        double distance = 0;
        distance = Math.sqrt(Math.pow(point.x - this.x, 2) + Math.pow(point.y - this.y, 2));
        
        return distance;
    }
    
    public double distance(double x, double y) {
        double distance = 0;
        distance = Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
        
        return distance;
    }
}
