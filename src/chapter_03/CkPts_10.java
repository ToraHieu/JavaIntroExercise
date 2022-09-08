package chapter_03;
import java.util.*;

public class CkPts_10 {
	private static Scanner in;

	public static void main (String[] agrs) {
		in = new Scanner(System.in);	
		
		System.out.print("Enter score: ");
		float score = in.nextFloat();
		char grade;
		
		if (score >= 90.0)
			grade = 'A';
		else if (score >= 80.0) 
			grade = 'B';
		else if (score >= 70.0) 
			grade = 'C';
		else if (score >= 60.0) 
			grade = 'D';
		else 
			grade = 'F';
		
		System.out.println(grade);
		
		
	}

}
