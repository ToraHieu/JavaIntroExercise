package chapter_08;

public class Ex_12 {
	public static void main(String[] agrs) {
		int[][] brackets = {
				{8350, 33950, 82250, 171550, 372950},   // Single
				{16700, 67900, 137050, 208850, 372950}, // Married jointly
				{8350, 33950, 68525, 104425, 186475},   // Married separately
				{11950, 45500, 117450, 190200, 372950}  // Head of household
		};
		
		double[] rates = {0.10, 0.15, 0.25, 0.28, 0.33, 0.35};
		
		int income = 400000;
		// 0 = Single, 1 = Married jointly, 2 = married separately, 3 = Head of household
		int status = 0; 
		double tax = computeTax(income, brackets, rates, status);
		System.out.println(tax);
	}
	
	public static double computeTax(int income, int[][] brackets, double[] rates, int status) {
		double tax = 0; 
		int currentTier = 0;
		if (income <= brackets[status][currentTier]) {
			tax = income * rates[currentTier];
			return tax;
		}
		else {
		tax+= brackets[status][currentTier] * rates[currentTier];
		double remainder = income - brackets[status][currentTier];
		currentTier++;
		while (currentTier < brackets[status].length && income >= brackets[status][currentTier]) {
			tax+= (brackets[status][currentTier] - brackets[status][currentTier-1]) * rates[currentTier];
			remainder -= (brackets[status][currentTier]- brackets[status][currentTier-1]);
			currentTier++;
		}
		if (remainder > 0) 
			tax+= remainder * rates[currentTier];
		return tax;
		}
	}
}
