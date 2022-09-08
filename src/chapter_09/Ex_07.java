package chapter_09;

public class Ex_07 {
	public static void main(String[] agrs) {
		Account test = new Account(1122, 20000);
		Account.setAnnualInterestRate(4.5);
		test.withdraw(2500);
		test.deposit(3000);
		System.out.printf("%-20s%10.2f\n","Balance",test.getBalance());
		System.out.printf("%-20s%10.2f\n","Monthly Interest",test.getMonthlyInterest());
		System.out.printf("%-20s%-10s\n","Date created",test.getDateCreated());
	}
}
