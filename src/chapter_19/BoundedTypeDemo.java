package chapter_19;

import chapter_13.Circle;
import chapter_13.GeometricObject;
import chapter_13.Rectangle;

public class BoundedTypeDemo {
    public static void main(String[] args ) {
        Rectangle rectangle = new Rectangle(2, 2);
        Circle circle = new Circle(2);

        System.out.println("Same area? " +
                equalArea(rectangle, circle));
    }

    public static <E extends GeometricObject> boolean equalArea(
            E object1, E object2) {
        return object1.getArea() == object2.getArea();
    }
}
