package chapter_12;

public class Ex_04 {
    public static void main(String[] args) {
        try {
            Loan l1 = new Loan(2.5, 2, 2000);
            Loan l2 = new Loan(3, 0, 4000);
            Loan l3 = new Loan(0, 0, 0);
            l1.setLoanAmount(0);
            l2.setNumberOfYears(6);
            l3.setAnnualInterestRate(100);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
