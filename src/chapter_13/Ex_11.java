package chapter_13;

public class Ex_11 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Octagon o1 = new Octagon(5);
        System.out.println("Area: " + o1.getArea());
        System.out.println("Perimeter: " +  o1.getPerimeter());
        Octagon o2 = (Octagon) o1.clone();
        System.out.println(o1.compareTo(o2));
    }
}

/** All eight sides of this octagon are of equal length */
class Octagon extends GeometricObject implements Comparable<Octagon>, Cloneable {
    private double side;
    
    public Octagon() {
        this(1);
    }

    public Octagon(double side) {
        super();
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }
    
    @Override
    public double getPerimeter() {
        return 8 * side;
    }
    
    @Override
    public double getArea() {
        return (2 + 4 / Math.sqrt(2)) * side * side;
    }
    
    @Override
    public int compareTo(Octagon o) {
        if (this.side > o.side)
            return 1;
        else if (this.side < o.side) 
            return -1;
        else
            return 0;
    }
    
    @Override
    public boolean equals(Object o) {
        return this.side == ((Octagon) o).side; 
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
