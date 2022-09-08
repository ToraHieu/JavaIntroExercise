package chapter_03;
import java.util.Scanner;

public class CS_09_Computing_BMI {

	private static Scanner in;

	public static void main(String[] args) {
		in = new Scanner (System.in);
		
		System.out.print("Enter weight in kilograms: ");
		double weight = in.nextDouble();
		
		System.out.print("Enter height in meters: "	);
		double height = in.nextDouble();
		
		double bmi = weight / (height * height);
		
		System.out.println("BMI is " + bmi);
		if (bmi < 18.5) 
			System.out.println("You're underweighted.");
		else if (bmi <= 24.9) 
			System.out.println("You're normal.");
		else if (bmi <= 29.9) 
			System.out.println("You're overwreighted");
		else 
			System.out.println("You're obesed.");
		
		

	}

}
