package chapter_06;

import java.util.Scanner;

public class Ex_21 {
	private static Scanner in = new Scanner(System.in);
	
	public static double greatCircleDistance(double x1, double y1, double x2, double y2, double radius) {
		double result;
		x1 = Math.toRadians(x1);
		y1 = Math.toRadians(y1);
		x2 = Math.toRadians(x2);
		y2 = Math.toRadians(y2);
		
		result = radius * Math.acos(Math.sin(x1) * Math.sin(x2) 
				+ Math.cos(x1) * Math.cos(x2) * Math.cos(y1 - y2));
		return result;
	}
	
	public static void main (String[] agrs) {
		final double radius = 6371.01;
		double x1, y1, x2, y2;
		System.out.println("Enter point 1 (latitude and longitude); in degrees: ");
		x1 = in.nextDouble();
		y1 = in.nextDouble();
		System.out.println("Enter point 2 (latitude and longitude); in degrees: ");
		x2 = in.nextDouble();
		y2 = in.nextDouble();
		System.out.println("The distance between the two points is " + greatCircleDistance(x1,y1,x2,y2,radius) + " km");
	}

}
