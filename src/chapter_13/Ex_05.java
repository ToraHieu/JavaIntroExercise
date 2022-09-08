package chapter_13;

public class Ex_05 {
    public static void main(String[] args) {
        CircleFromComparableGeometricObject c1 = new CircleFromComparableGeometricObject(5);
        CircleFromComparableGeometricObject c2 = new CircleFromComparableGeometricObject(3);
        System.out.println((ComparableGeometricObject.max(c1, c2)) == c1 ? "c1" : "c2");
        
        RectangleFromComparableGeometricObject t1 = new RectangleFromComparableGeometricObject(3, 4);
        RectangleFromComparableGeometricObject t2 = new RectangleFromComparableGeometricObject(5, 6);
        System.out.println((ComparableGeometricObject.max(t1, t2)) == t1 ? "t1" : "t2");

    }
}

/** Copied from Circle in chapter_13 */
class CircleFromComparableGeometricObject extends ComparableGeometricObject {
    private double radius;

    public CircleFromComparableGeometricObject() {
    }

    public CircleFromComparableGeometricObject(double radius) {
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
      System.out.println("The circle is created " + getDateCreated() +
        " and the radius is " + radius);
    }
}

/** Copied from Rectangle in chapter_13 */
class RectangleFromComparableGeometricObject extends ComparableGeometricObject {
    private double width;
    private double height;

    public RectangleFromComparableGeometricObject() {
    }

    public RectangleFromComparableGeometricObject(double width, double height) {
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
}
