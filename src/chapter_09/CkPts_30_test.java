package chapter_09;

public class CkPts_30_test {
	public static void main(String[] agrs) {
		CkPts_30 test = new CkPts_30();
		int[] values = test.getValues();
		for (int u: values) {
			System.out.println(u);
		}
		for (int i = 0; i < values.length; i++)	{
			values[i] = 0;
		}
		int[] newValues = test.getValues();
		for (int i = 0; i < values.length; i++) {
			System.out.println(values[i] + "----" + newValues[i]);
		}
		
		
	}
}
