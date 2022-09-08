package chapter_09;

public class Ex_13 {
	public static void main(String[] agrs) {
		Location test;
		double[][] a = {{1, 2, 3}, {6, 5, 4}, {8, 9, 0}};
		test = locateLargest(a);
		System.out.println(test.toString());
	}
	
	public static Location locateLargest(double[][] a) {
		Location test = new Location();
		int rowIndex = 0, columnIndex = 0;
		double maxValue = a[0][0];
		
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] > a[rowIndex][columnIndex]) {
					rowIndex = i;
					columnIndex = j;
					maxValue = a[i][j];
				}
			}
		}
		
		test.setRow(rowIndex);
		test.setColumn(columnIndex);
		test.setMaxValue(maxValue);
		
		return test;
	}
}
