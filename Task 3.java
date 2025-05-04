//TASK 3 ATM MACHINE//
import java.util.Scanner;

public class ATMSystem {

    static class BankAccount {
        private double balance;

        public BankAccount(double initialBalance) {
            if (initialBalance >= 0) {
                this.balance = initialBalance;
            } else {
                System.out.println("Initial balance cannot be negative.");
                this.balance = 0;
            }
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposited: " + amount);
            } else {
                System.out.println("Invalid deposit amount.");
            }
        }

        public boolean withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println("Withdrawn: " + amount);
                return true;
            } else if (amount > balance) {
                System.out.println("Insufficient funds for withdrawal.");
                return false;
            } else {
                System.out.println("Invalid withdrawal amount.");
                return false;
            }
        }
    }

    static class ATM {
        private BankAccount bankAccount;
        private Scanner scanner;

        public ATM(BankAccount bankAccount) {
            this.bankAccount = bankAccount;
            this.scanner = new Scanner(System.in);
        }

        public void displayMenu() {
            System.out.println("\nATM Menu:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
        }

        public void run() {
            boolean exit = false;
            while (!exit) {
                displayMenu();
                System.out.print("Choose an option: ");
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        withdraw();
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        checkBalance();
                        break;
                    case 4:
                        System.out.println("Exiting ATM. Goodbye!");
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        }

        private void withdraw() {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            if (bankAccount.withdraw(amount)) {
                System.out.println("Transaction successful.");
            }
        }

        private void deposit() {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            bankAccount.deposit(amount);
            System.out.println("Transaction successful.");
        }

        private void checkBalance() {
            System.out.println("Your current balance is: " + bankAccount.getBalance());
        }
    }

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(5000);
        ATM atm = new ATM(userAccount);
        atm.run();
    }
}
