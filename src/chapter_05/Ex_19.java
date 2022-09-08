package chapter_05;
import java.util.*;
public class Ex_19 {
	private static Scanner in;
	
	public static void main (String[] agrs) {
		in = new Scanner(System.in);
		
		System.out.print("Enter the number of lines: ");
		int line = in.nextInt();
		int AoS = 1;
		//Starting loops. 1 line per iteration. 
		for (int i = 1; i <= line; i++) {
			//Print the blank spaces before the numbers on the left. 
			for (int a = line-i; a > 0; a--) {
				System.out.print("    ");
			}
			
			//Print the numbers on the left
			for (int b = 1; b < i; b++) {
				System.out.printf("%4d",(int)Math.pow(2, b-1));
			}
			
			//Print the AoS number. (Axis of Symmetry: Trục đối xứng)
			AoS = (int)Math.pow(2, i-1);
			System.out.printf("%4d", AoS);
			
			//Print the number on the right.
			for (int b = i-1; b > 0; b--) {
				System.out.printf("%4d",(int)Math.pow(2, b-1));
			}
			
			//Print the blank spaces after the numbers on the right. 
			for (int d = line-i; d > 0; d--) {
				System.out.print("    ");
			}
			//Go down the line before ending iteration.
			System.out.println();
		}
	}
}
