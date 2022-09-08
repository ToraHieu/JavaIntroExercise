package chapter_10;

public class CkPts_18 {

	public static void main(String[] args) {
		String s1 = "Welcome";
		String s2 = "welcome";
		
		s2 = s1.replace('e', 'E');
		System.out.println(s1 + "\t" + s2);
		
		String[] tokens = "Welcome to Java and HTML".split("[ ]");
		for (String u: tokens) {
			System.out.println(u);
		}

	}

}
