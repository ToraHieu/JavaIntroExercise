package chapter_02;

import java.util.*;

public class PrEx_02_05 {
	private static Scanner in;

	public static void main (String[] agrs) {
		in = new Scanner (System.in);
		
		System.out.print("Enter the subtotal (e,g: 100) and a gratuity rate (%): ");
		double subtotal = in.nextDouble();
		double gratuityRate = in.nextDouble();
		double gratuity = subtotal * gratuityRate / 100;
		double total = gratuity + subtotal;
		
		System.out.println("The gratuity is $" + gratuity + " and the total is $" + total);
	}

}
