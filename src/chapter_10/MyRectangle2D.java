package chapter_10;
/** Undone due to lack of knowledge about geometry. This class/exercise is left unfinished. */
public class MyRectangle2D {
    private double x;
    private double y;
    private double width;
    private double height;
    
    public MyRectangle2D() {
        this(0, 0, 1, 1);
    }
    
    public MyRectangle2D(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public double getX() {
        return x;
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public double getY() {
        return y;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public double getWidth() {
        return width;
    }
    
    public void setWidth(double width) {
        this.width = width;
    }
    
    public double getHeight() {
        return height;
    }
    
    public void setHeight(double height) {
        this.height = height;
    }
    
    public double getArea() {      
        return height * width;
    }
    
    public double getPerimeter() {
        return (height + width) * 2;
    }
    
    public boolean contains(double x, double y) {
        
        return false;
    }
    
}
