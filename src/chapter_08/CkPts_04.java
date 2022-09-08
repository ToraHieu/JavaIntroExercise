package chapter_08;

public class CkPts_04 {
	public static void main(String[] agrs) {
		int[][] n = {{1,2}, {2, 3},{} , };
		for (int i = 0; i < n.length; i++) {
			for (int j = 0; j < n[i].length; j++) {
				System.out.print(n[i][j] + " ");
			}
			System.out.println();
		}
	}
}
