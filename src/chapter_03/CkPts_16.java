package chapter_03;

public class CkPts_16 {
	public static void main (String[] agre) {
		//Generate an integer number in the range [0,20).
	 	int a = (int)(Math.random()*20);
		System.out.println(a);
		
		//Generate an integer number in the range [10, 20).
		int b = (int)(Math.random()*10 + 10);
		System.out.println(b);
		
		//Generate an integer number in the range [10,50].
		int c = (int)(Math.random() * (50 - 10) + 1) + 10;
		System.out.println(c);
		
		/*Basic formula:
		 Math.random() * (max - min) + 1 + min
		 */
		
	}
}
