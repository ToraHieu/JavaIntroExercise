package chapter_07;

import java.util.Scanner;

public class Ex_11 {
	private static final Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		double[] array = new double[10];
		System.out.print("Enter ten numbers: ");
		for (int i = 0; i < 10; i++) {
			array[i] = in.nextDouble();
		}
		
		System.out.printf("The mean is %.2f\n",mean(array));
		System.out.printf("The standard deviation is %.5f\n",deviation(array));
		
	}
	
	public static double deviation(double[] x) {
		double deviation;
		double mean = mean(x);
		double numerator = 0, denominator;
		for (int i = 0; i < x.length; i++) {
			numerator+= Math.pow((x[i] - mean),2);
		}
		denominator = x.length - 1;
		deviation = Math.sqrt(numerator/denominator);
		
		return deviation;
	}
	
	public static double mean(double[] x) {
		double mean = 0;
		for (int i = 0; i < x.length; i++) {
			mean+= x[i];
		}
		mean/=x.length;
		return mean;
	}

}
