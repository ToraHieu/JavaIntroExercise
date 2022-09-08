package chapter_05;

import java.util.Scanner;

public class Ex_22 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Loan Amount: ");
            double loan = scanner.nextDouble();
            System.out.print("Number of Years: ");
            double numberOfYears = scanner.nextDouble();
            System.out.print("Annual Interest Rate: ");
            double interestRate = scanner.nextDouble() / 1200; // Convert to monthly interest as percentage

            double monthlyPayment = loan * interestRate/
                    (1 - (Math.pow(1 / (1 + interestRate), numberOfYears * 12)));
            double totalPayment = monthlyPayment * numberOfYears * 12;

            System.out.printf("\nMonthly Payment: %.2f", monthlyPayment);
            System.out.printf("\nTotal Payment: %.2f", totalPayment);

            System.out.printf("\n\n%-8s%12s%12s%12s", "Payment#", "Interest", "Principal", "Balance");
            double interest, principal, balance = loan;
            for (int i = 1; i < numberOfYears * 12; i++) {
                interest = interestRate * balance;
                principal = monthlyPayment - interest;
                balance = balance - principal;
                System.out.printf("\n%-8d%12.2f%12.2f%12.2f", i, interest, principal, balance);
            }
            interest = interestRate * balance;
            principal = monthlyPayment - interest;
            balance = balance - principal;
            System.out.printf("\n%-8d%12.2f%12.2f%12.2f", (int) (numberOfYears * 12), interest + balance, principal + balance, 0d);
        }
    }
}