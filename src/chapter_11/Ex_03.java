package chapter_11;

import chapter_09.Account;

public class Ex_03 {
    public static void main(String[] agrs) {
        SavingAccount saving = new SavingAccount();
        saving.setBalance(500);
        saving.deposit(300);
        saving.withdraw(1000);
        System.out.println(saving.toString());
        
        CheckingAccount checking = new CheckingAccount();
        checking.setBalance(500);
        checking.setOverdraftLimit(500);
        checking.deposit(300);
        checking.withdraw(900);
        checking.withdraw(1000);
        System.out.println(checking.toString());
    }
}

class SavingAccount extends Account {
    public String toString() {
        return "Saving Account\nBalance: " + getBalance() +".";
    }
}

class CheckingAccount extends Account {
    private double overdraftLimit;
    
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public String toString() {
        return "Checking Account\nBalance: " + getBalance() + "\nOverdraft limit: " + getOverdraftLimit() + ".";
    }
    
    @Override
    public void withdraw(double withdrawAmount) {
        if (withdrawAmount > (getBalance() + getOverdraftLimit())) 
            System.out.println("Unable to withdraw. The amount entered is greater than the balance and overdraft limit.");
        else {
            setBalance(getBalance() - withdrawAmount);
            System.out.println("Withdraw successful. The current balance is " + getBalance() + ".");
        }
    }
}