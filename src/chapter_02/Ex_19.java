package chapter_02;

public class Ex_19 {
	public static void main (String[] agrs) {
		System.out.println(23%3*2);
		int a = 10;
		a %= 6 / a + 5;
		System.out.println(a);
		
		double d = 1.0;
		d = 4 + d * d + 4;
		System.out.println(d);
		
		a = 1;
		d = 1.0; 
		d -= 1.5 * 3 + a++;
		System.out.println(d);
		
		a = 10;
		a %= 6 / a + 5; //Error because a is now 0 and cannot be divided (Zero)
		System.out.println(a);
		
 

	}

}
