package chapter_11;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex_15 {
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] agrs) {
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        System.out.print("Enter the number of points : ");
        int n = input.nextInt();
        System.out.print("Enter the coordinates of the points: ");
        for (int i = 0; i < n; i++) {
            x.add(input.nextDouble());
            y.add(input.nextDouble());
        }
        double area = polygonArea(x, y, n);
        System.out.println("The total area is " + area);
    }
    
    public static double polygonArea(ArrayList<Double> x, ArrayList<Double> y, int numPoints) {
        double area = 0;
        int j = numPoints - 1;
        for (int i = 0; i < numPoints; i++) {
            area += (x.get(j) + x.get(i)) * (y.get(j) -  y.get(i));
            j = i;
        }
        return area / 2;
    }

}
