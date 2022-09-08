package chapter_04;

public class Ex_25 {
	public static void main(String[] agrs) {
		String s = "";
		s+= (char) (Math.random()*('Z' - 'A' + 1) + 'A');
		s+= (char) (Math.random()*('Z' - 'A' + 1) + 'A');
		s+= (char) (Math.random()*('Z' - 'A' + 1) + 'A');
		s+= "-";
		s+= (int)(Math.random()*10);
		s+= (int)(Math.random()*10);
		s+= (int)(Math.random()*10);
		s+= (int)(Math.random()*10);
		System.out.println(s);
	}
}
