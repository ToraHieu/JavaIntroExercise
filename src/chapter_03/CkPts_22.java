package chapter_03;

public class CkPts_22 {

	public static void main(String[] args) {
		int x = 1;
		boolean b1 = (x >= 1) && (x++ > 1);
		System.out.println(b1 + " " + x);
		
		int y = 1;
		boolean b2 = (y > 1) && (y++ > 1);
		System.out.println(b2 + " " + y);

	}

}
