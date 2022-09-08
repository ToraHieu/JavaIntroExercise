package chapter_07;

public class Ex_34 {
	public static void main(String[] agrs) {
		System.out.print(sort("acb"));
	}
	
	public static String sort(String s) {
		String result = "";
		char[] temp = new char[s.length()];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = s.charAt(i);
		}
		java.util.Arrays.sort(temp);
		for (int i = 0; i < temp.length; i++) {
			result += temp[i];
		}
		return result;
	}

}
