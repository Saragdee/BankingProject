package edgarasmackevicius;

public class BankAccount {
    private double balance;
    public BankAccount() {
        this.balance = 0;
    }
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Invalid or insufficient funds for withdrawal.");
        }
    }
    public void printBalance() {
        System.out.println("Current balance: €" + balance);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void transfer(BankAccount targetAccount, double amount) {
        if (amount > 0 && balance >= amount) {
            this.withdraw(amount);
            targetAccount.deposit(amount);
        } else {
            System.out.println("Invalid or insufficient funds for transfer.");
        }
    }
}