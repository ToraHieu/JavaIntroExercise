package chapter_06;

public class Ex_37 {
	public static void main (String[] agrs) {
		String s = format (34,5);
		System.out.print(s);
	}
	
	public static String format (int number, int width) {
		String S = "";
		int remain = number, counter = 0;
		while (remain > 0) {
			counter++;
			remain /= 10;
		}
		for (int i = counter; i < width; i++) {
			S+= 0;
		}
		S+= number;
					
		return S;
	}

}
