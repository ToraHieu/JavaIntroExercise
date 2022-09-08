package chapter_04;
import java.util.Scanner;
public class Ex_26 {
	private static Scanner input = new Scanner(System.in);
	public static void main(String[] agrs) {
		System.out.print("Enter an amount: ");
		String amountString = input.next();
		int decimalPosition = amountString.indexOf('.');
	    
	    int amount; // amount in cents
	    
	    if (decimalPosition == -1) {
	      amount = Integer.parseInt(amountString)*100;
	    }
	    else {
	      String fractionPart = amountString.substring(decimalPosition + 1);
	      
	      if (fractionPart.length() >= 2) {
	        fractionPart = fractionPart.substring(0, 2);
	      }
	      else if (fractionPart.length() == 1) {
	        fractionPart = fractionPart + "0";
	      } 
	      else {
	        fractionPart = fractionPart + "00";
	      }
	    
	      amount = Integer.parseInt(
	        amountString.substring(0, decimalPosition) + fractionPart);
	    }

	    int remainingAmount = amount;

	    // Find the number of one dollars
	    int numberOfOneDollars = remainingAmount / 100;
	    remainingAmount = remainingAmount % 100;
		int numberOfQuarters = remainingAmount / 25;
		remainingAmount %= 25;
		int numberOfDimes = remainingAmount / 10;
		remainingAmount %= 10;
		int numberOfNickels = remainingAmount/5;
		remainingAmount %= 5;
		int numberOfPennies = remainingAmount;
		
		System.out.println("Your amount " + amountString + " consists of"); 
	    System.out.println("    " + numberOfOneDollars + " dollars");
	    System.out.println("    " + numberOfQuarters + " quarters");
	    System.out.println("    " + numberOfDimes + " dimes"); 
	    System.out.println("    " + numberOfNickels + " nickels");
	    System.out.println("    " + numberOfPennies + " pennies");
	}
}
