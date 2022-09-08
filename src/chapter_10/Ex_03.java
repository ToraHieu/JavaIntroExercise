package chapter_10;

public class Ex_03 {
	public static void main(String[] agrs) {
		MyInteger mi1 = new MyInteger(19);
		System.out.println(mi1.getValue() + " is Even/Odd/Prime?\n" + mi1.isEven() + " " + mi1.isOdd() + " " + mi1.isPrime());
		
		System.out.println(MyInteger.isPrime(31));
	
		char[] ch = {'1','2','3'};
		System.out.println(MyInteger.parseInt(ch));
		String s = "123456";
		System.out.println(MyInteger.parseInt(s));

	}

}
