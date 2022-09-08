package chapter_02;

public class CastingToChar {

	public static void main(String[] args) {
		char ch = (char)0xff0041; //0041 is 65 Decimal
		System.out.println(ch);
		char ch2 = '\u03B1'; //Decimal 65 is assign to ch
		System.out.println(ch2);

	}

}
