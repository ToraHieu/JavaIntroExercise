package chapter_02;

import javax.swing.*;

public class PrEx_02_07 {
	public static void main (String[] agrs) {
		String s = JOptionPane.showInputDialog("Enter the number of minutes: ");
		long minutes = Long.parseLong(s);
		long totalDays = minutes / 60 / 24;
		long days = totalDays % 365;
		long years = totalDays / 365;
		
		System.out.print(minutes + " minutes is approximately " + years + " years and "+ days + " days.");
		
	}

}
