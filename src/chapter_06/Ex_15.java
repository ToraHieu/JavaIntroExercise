package chapter_06;

public class Ex_15 {
	
	public static double computeTax(int status, double taxableIncome) {
		double tax = 0.0;
		switch(status) {
		
		//Single filer
		case 0: {
			if(taxableIncome <= 8350)
				tax = taxableIncome * 0.10;
				else if(taxableIncome <= 33950)
				tax = 8350*0.10+ (taxableIncome - 8350) * 0.15;
				else if(taxableIncome <= 82250)
				tax = 8350*0.10+ (33950-8350) * 0.15+
				(taxableIncome - 33950) * 0.25;
				else if(taxableIncome <= 171550)
				tax = 8350*0.10+ (33950-8350) * 0.15 +
				(82250-33950) * 0.25+ (taxableIncome - 82250) * 0.28;
				else if(taxableIncome <= 372950)
				tax = 8350*0.10+ (33950-8350) * 0.15 +
				(82250-33950) * 0.25+ (171550-82250) * 0.28+
				(taxableIncome - 171550) * 0.33;
				else
				tax = 8350*0.10+ (33950-8350) * 0.15 +
				(82250-33950) * 0.25+ (171550-82250) * 0.28+
				(372950-171550) * 0.33+ (taxableIncome - 372950) * 0.35;
				
			break;
		}
		//Married jointly
		case 1: {
			
			break;
		}
		//Married separately
		case 2: {
			
			break;
		}
		//Head of household
		case 3: {
			
			break;
		}
		
		}
		
		return tax;
	}
	
	public static void main (String[] agrs) {
		System.out.println(Math.round(computeTax(0,50000)));
	}
}
