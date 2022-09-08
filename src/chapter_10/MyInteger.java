package chapter_10;

public class MyInteger {
	private int value;
	
	MyInteger(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean isEven() {
		if (value % 2 == 0)
			return true;
		return false;
	}
	
	public boolean isOdd() {
		if (value % 2 != 0)
			return true;
		return false;
	}
	
	public boolean isPrime() {
		if (value == 2)
			return true;
		else if (isEven())
			return false;
		
		for (int i = 3; i < value / 2; i+=2) {
			if (value % i == 0)
				return false;
		}
		
		return true;
	}
	
	public static boolean isEven(int value) {
		if (value % 2 == 0)
			return true;
		return false;
	}
	
	public static boolean isOdd(int value) {
		if (value % 2 != 0)
			return true;
		return false;
	}
	
	public static boolean isPrime(int value) {
		if (value == 2)
			return true;
		else if (isEven(value))
			return false;
		
		for (int i = 3; i < value / 2; i+=2) {
			if (value % i == 0)
				return false;
		}
		
		return true;
	}
	
	public static boolean isEven(MyInteger myInteger) {
		if (myInteger.value % 2 == 0)
			return true;
		return false;
	}
	
	public static boolean isOdd(MyInteger myInteger) {
		if (myInteger.value % 2 != 0)
			return true;
		return false;
	}
	
	public static boolean isPrime(MyInteger myInteger) {
		if (myInteger.value == 2)
			return true;
		else if (isEven(myInteger.value))
			return false;
		
		for (int i = 3; i < myInteger.value / 2; i+=2) {
			if (myInteger.value % i == 0)
				return false;
		}
		
		return true;
	}
	
	public boolean equals(int i) {
		if (value == i) 
			return true;
		
		return false;
	}
	
	public boolean equals(MyInteger myInteger) {
		if (value == myInteger.value)
			return true;
		
		return false;
	}
	
	public static int parseInt(char[] array) {
		int value = 0;
		for (int i = 0; i < array.length; i++) {
			value+= (int) (array[i] - 48) * Math.pow(10,array.length-1-i);
		}
		
		return value;
	}
	
	public static int parseInt(String s) {
		int value = 0;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			value+= (int) (ch - 48) * Math.pow(10, s.length()-1-i);
		}
		
		return value;
	}
}
