package chapter_10;
import java.util.Scanner;
import chapter_09.Account;

/** Once the system starts, it will not stop*/
public class Ex_07 {
    private static Scanner input = new Scanner(System.in);
    
    // Game: ATM Machine
    public static void main(String[] agrs) {
        Account[] accounts = new Account[10];
        for (int i = 0; i < 10; i++) {
            accounts[i] = new Account(i, 100);
        }
        boolean programStarted = true;
        boolean IDchosen = false;
        
        while (programStarted) {
            int ID = 0;
            while(!IDchosen) {
                ID = chooseID(accounts.length);
                IDchosen = true;
            }
            while(IDchosen) {
                printMainMenu(accounts[ID]);
                System.out.print("Enter a choice: ");
                int choice = input.nextInt();
                if (choice == 4) {
                    System.out.println();
                    IDchosen = false;
                    break;
                }
                else
                    processChoice(choice, accounts[ID]);
                
            }
        }
    }
    
    public static int chooseID(int ID_limit) {
        boolean invalid_ID = true;
        int id = 0;
        while (invalid_ID) {
            System.out.print("Enter an id: ");
            id = input.nextInt();
            if (id < 0 || id >= ID_limit) {
                System.out.println("Invalid ID, please try again.");
                continue;
            }
            invalid_ID = false;
        }
        return id;
    }
    
    public static void printMainMenu(Account accountID) {
        String menu = "Main menu", 
                c1 = "1: check balance", c2 = "2: withdraw",
                c3 = "3: deposit", c4 = "4: exit";
        System.out.printf("\n%s\n%s\n%s\n%s\n%s\n",menu,c1,c2,c3,c4);
        
    }
    
    public static void processChoice(int choice, Account account) {
        switch (choice) {
        case 1: 
            System.out.println("The balance is " + account.getBalance());
            break;
        case 2:
            System.out.print("Enter an amount to withdaraw: ");
            double withdrawAmount = input.nextDouble();
            account.withdraw(withdrawAmount);
            break;
        case 3: 
            System.out.print("Enter an amount to deposit: ");
            double depositAmount = input.nextDouble();
            account.deposit(depositAmount);
            break;
            
            default: System.out.println("Invalid input. Please try again.");
        }
    }
    
}
