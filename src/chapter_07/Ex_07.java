package chapter_07;
import java.util.Arrays;
public class Ex_07 {
	public static void main (String[] args) {
		int[] count =  new int[10];
		int random;
		for (int i = 0; i < 100; i++) {
			random = (int) (Math.random() * 10);
			count[random]++;
		}
		System.out.println(Arrays.toString(count));
	}
}
