package chapter_03;

import java.util.*;

public class Ex_09 {
	
	private static Scanner in;

	public static void main (String[] agrs) {
		in = new Scanner(System.in);
		System.out.print("Enter the first 9 digits of an ISBN as ineger: ");
		long inNum = in.nextLong();
		long sub = inNum;
		
		int d1 = (int) (sub/Math.pow(10,8));
		sub -= d1*Math.pow(10,8);
		int d2 = (int) (sub/Math.pow(10,7));
		sub -= d2*Math.pow(10,7);
		int d3 = (int) (sub/Math.pow(10,6));
		sub -= d3*Math.pow(10,6);
		int d4 = (int) (sub/Math.pow(10,5));
		sub -= d4*Math.pow(10,5);
		int d5 = (int) (sub/Math.pow(10,4));
		sub -= d5*Math.pow(10,4);
		int d6 = (int) (sub/Math.pow(10,3));
		sub -= d6*Math.pow(10,3);
		int d7 = (int) (sub/Math.pow(10,2));
		sub -= d7*Math.pow(10,2);
		int d8 = (int) (sub/Math.pow(10,1));
		sub -= d8*Math.pow(10,1);
		int d9 = (int) (sub/Math.pow(10,0));
		
		int d10 = (d1*1 + d2*2 + d3*3 + d4*4 + d5*5 + d6*6 + d7*7 + d8*8 + d9*9) % 11;
		
		if (d10 == 10) 
			System.out.println("The ISBN-10 number is " + d1 + d2+ d3 +d4 + d5 +d6 +d7 +d8 +d9 +"X");
		else 
			System.out.println("The ISBN-10 number is " + d1 + d2+ d3 +d4 + d5 +d6 +d7 +d8 +d9 +d10);
		
		
	}

}
