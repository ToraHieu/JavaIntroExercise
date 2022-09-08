package chapter_07;
// I didn't do this, mainly because misunderstanding the question.
public class Ex_30 {
	private static java.util.Scanner input;

	public static void main (String[] args) {
	    input = new java.util.Scanner(System.in);
	    
	    System.out.print("Enter the number of values: ");
	    int size = input.nextInt();
	    
	    int[] values = new int[size];
	    
	    System.out.print("Enter the values: ");
	    for (int i = 0; i < values.length; i++)
	      values[i] = input.nextInt();
	    
	    if (isConsecutiveFour(values))
	      System.out.println("The series has consecutive fours");
	    else
	    	System.out.println("The series has no consecutive fours");
	  }

	  public static boolean isConsecutiveFour(int[] values) {    
	    for (int i = 0; i < values.length - 3; i++) {
	      boolean isEqual = true;        
	      for (int j = i; j < i + 3; j++) {
	        if (values[j] != values[j + 1]) {
	          isEqual = false;
	          break;
	        }
	      }
	     
	      if (isEqual) return true;
	    }
	    
	    return false;
	  }
}
