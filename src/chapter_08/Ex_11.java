package chapter_08;

public class Ex_11 {
	public static void main(String[] agrs) {
		System.out.println("Enter a numbher between 0 and 511: ");
		int n = 7;
		int remainder = n;
		boolean[] isTail = new boolean[9];
		int currentBit = isTail.length-1;
		while (remainder != 0 && currentBit >= 0) {
			if (remainder % 2 != 0) 
				isTail[currentBit] = true;
			currentBit--;
			remainder /= 2;
		}
		for (int i = 0; i < isTail.length; i++) {
			System.out.print((isTail[i]) ? "T " : "H ");
			if ((i+1) % 3 == 0)
				System.out.println();
		}
	}

}
