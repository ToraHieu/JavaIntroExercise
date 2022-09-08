package chapter_09;

public class CkPts_30 {
	
	private int[] values;
	
	public int[] getValues() {
		return values;
	}
	
	CkPts_30() {
		values = new int[10];
		for (int i = 0; i < values.length; i++) {
			values[i] = i;
		}
	}
	
	// Result from CkPts_30_test: This class is not immutable (with constructor).

}
