package chapter_08;

public class Ex_16 {
	public static void main(String[] agrs) {
		int[][] m = {{4, 2}, {1, 7}, {4, 5}, {1, 2}, {1, 1}, {4, 1}};
		sort(m);
		Ex_10.printMatrix(m);
		
	}
	
	public static void sort(int[][] m) {
		// Primary sort at rows. 
		for (int i = 1; i < m.length; i++) {
			int[] currentArray = m[i];
			int k;
			for (k = i - 1; k >= 0 && m[k][0] > currentArray[0]; k--) {
				m[k+1] = m[k];
			}
			m[k+1] = currentArray;
		}
		
		// Secondary sort at columns
		for (int i = 1; i < m.length; i++) {
			int[] currentArray = m[i];
			int k;
			for (k = i - 1; k >= 0 && m[k][1] > currentArray[1] && m[k][0] >= currentArray[0]; k--) {
				m[k+1] = m[k];
			}
			m[k+1] = currentArray;
		}	
	}

}
