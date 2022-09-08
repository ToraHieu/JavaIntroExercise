package chapter_13;

public class Ex_07 {
    public static void main(String[] args) {
        GeometricObject[] obj = new GeometricObject[5];
        obj[0] = new Square(10);
        obj[1] = new Circle();
        obj[2] = new Triangle();
        obj[3] = new Square();
        obj[4] = new Circle(5);
        
        for (GeometricObject o: obj) {
            if (o instanceof Colorable) {
                ((Colorable) o).howToColor();;
            } else {
                System.out.println("Not colorable");
            }
        }
    }
}

class Square extends GeometricObject implements Colorable {
    private double side;
    
    public Square() {
        side = 1;
    }
    
    public Square(double side) {
        this.side = side;
    }
    
    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * side;
    }
    
    @Override
    public double getPerimeter() {
        return side * 2;
    }
    
    @Override
    public void howToColor() {
        System.out.println("Color all four sides");
    }
}
