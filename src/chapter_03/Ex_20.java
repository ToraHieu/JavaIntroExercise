package chapter_03;

import java.util.*;

public class Ex_20 {
	private static Scanner in;
	
	public static void main (String[] agrs) {
		in = new Scanner (System.in);
		
		System.out.print("Enter temperature in Fahrenheit: ");
		float t = in.nextFloat();
		
		System.out.print("Enter the win speed in miles per hour: ");
		float v = in.nextFloat();
		
		if (t > -58.0 && t < 41 && v >= 2) {
			float wc = (float) (35.74 + 0.6215*t +- 35.75*Math.pow(v, 0.16) + 0.4275*t*Math.pow(v, 0.16));
			System.out.println(wc);
		}
		else if ((t > -58.0 && t < 41) ^ v >= 2) 
			System.out.println("Invalid input. The temperature or the wind speed is invalid.");
		else 
			System.out.println("Invalid input. The temperature and the wind speed are both invalid.");

	}

}
