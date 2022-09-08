package chapter_20;

import chapter_13.Circle;
import chapter_13.GeometricObject;
import chapter_13.Rectangle;

import java.util.Comparator;

public class Ex_19 {
    public static void main(String[] args) {
        GeometricObject[] list1 = {new Circle(5), new Rectangle(4, 5),
        new Circle(5.5), new Rectangle(2.4, 5), new Circle(0.5),
        new Rectangle(4, 65), new Circle(4.5), new Rectangle(4.4, 1),
        new Circle(6.5), new Rectangle(4, 5)};

        selectionSort(list1, new GeometricObjectComparator());
        for (GeometricObject geometricObject : list1)
            System.out.println(geometricObject.getArea());
    }

    public static <E> void selectionSort(E[] list, Comparator<? super E> comparator) {
        for (int i = 0; i < list.length - 1; i++) {
            // Find the minimum in the list[i..list.length-1]
            E currentMin = list[i];
            int currentMinIndex = i;

            for (int j = i + 1; j < list.length; j++) {
                if (comparator.compare(currentMin, list[j]) > 0) {
                    currentMin = list[j];
                    currentMinIndex = j;
                }
            }

            // Swap list[i] with list[currentMinIndex] if neccessay
            if (currentMinIndex != i) {
                list[currentMinIndex] = list[i];
                list[i] = currentMin;
            }
        }
    }
}
