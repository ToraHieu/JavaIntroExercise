package chapter_17;

import java.io.*;

public class Ex_06 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Exercise17_06.dat"));
            //ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Exercise17_06.dat"))
        ) {
            output.writeObject(new SerializableLoan());
            output.writeObject(new SerializableLoan(7.2, 15, 55000));
            output.writeObject(new SerializableLoan(0, 1, 500));
            output.writeObject(new SerializableLoan());
            output.writeObject(new SerializableLoan(1.8, 1, 1000));

//            System.out.println("" + ((SerializableLoan)inputStream.readObject()).getLoanDate().getTime());
//            System.out.println("" + ((SerializableLoan)inputStream.readObject()).getTotalPayment());
//            System.out.println("" + ((SerializableLoan)inputStream.readObject()).getMonthlyPayment());
//            System.out.println("" + ((SerializableLoan)inputStream.readObject()).getLoanDate().getTime());
//            System.out.println("" + ((SerializableLoan)inputStream.readObject()).getTotalPayment());
        }
    }
}

class SerializableLoan implements Serializable {
    private double annualInterestRate;
    private int numberOfYears;
    private double loanAmount;
    private java.util.Date loanDate;

    /** Default constructor */
    public SerializableLoan() {
        this(2.5, 1, 1000);
    }

    /** Construct a loan with specified annual interest rate,
     number of years and loan amount
     */
    public SerializableLoan(double annualInterestRate, int numberOfYears,
                             double loanAmount) {
        this.annualInterestRate = annualInterestRate;
        this.numberOfYears = numberOfYears;
        this.loanAmount = loanAmount;
        loanDate = new java.util.Date();
    }

    /** Return annualInterestRate */
    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    /** Set a new annualInterestRate */
    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    /** Return numberOfYears */
    public int getNumberOfYears() {
        return numberOfYears;
    }

    /** Set a new numberOfYears */
    public void setNumberOfYears(int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    /** Return loanAmount */
    public double getLoanAmount() {
        return loanAmount;
    }

    /** Set a newloanAmount */
    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    /** Find monthly payment */
    public double getMonthlyPayment() {
        double monthlyInterestRate = annualInterestRate / 1200;
        double monthlyPayment = loanAmount * monthlyInterestRate / (1 -
                (Math.pow(1 / (1 + monthlyInterestRate), numberOfYears * 12)));
        return monthlyPayment;
    }

    /** Find total payment */
    public double getTotalPayment() {
        double totalPayment = getMonthlyPayment() * numberOfYears * 12;
        return totalPayment;
    }

    /** Return loan date */
    public java.util.Date getLoanDate() {
        return loanDate;
    }
}
