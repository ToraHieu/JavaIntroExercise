package chapter_10;

public class CkPts_31 {
	public static void main(String[] agrs) {
		StringBuilder s1 = new StringBuilder("Java");
		StringBuilder s2 = new StringBuilder("HTML");
		
		// d.
		System.out.println(s1.insert(1, s2));
		
		// h.
		System.out.println(s1.delete(1, 3));

	}

}
