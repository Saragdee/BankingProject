package edgarasmackevicius;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BankAccountDemo {
    protected static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        logger.info("BankAccountDemo application started");
        Scanner scanner = new Scanner(System.in);
        Map<Integer, BankAccount> accounts = new HashMap<>();
        accounts.put(1, new BankAccount());
        accounts.put(2, new BankAccount(100));

        while (true) {
            try {
                System.out.println("""
                        Choose an option:\s
                        1. Deposit to Account 1
                        2. Withdraw from Account 1
                        3. Check Balance of Account 1
                        4. Transfer from Account 1 to Account 2
                        5. Transfer from Account 2 to Account 1
                        6. Exit""");
                int choice = scanner.nextInt();
                logger.info("User selected option " + choice);


                switch (choice) {
                    case 1: // Deposit to Account 1
                        System.out.println("Enter the amount to deposit into Account 1:");
                        double depositAmount = scanner.nextDouble();
                        accounts.get(1).deposit(depositAmount);
                        logger.info("Deposit to Account 1 processed");
                        break;
                    case 2: // Withdraw from Account 1
                        System.out.println("Enter the amount to withdraw from Account 1:");
                        double withdrawAmount = scanner.nextDouble();
                        accounts.get(1).withdraw(withdrawAmount);
                        logger.info("Withdraw from Account 1 processed");
                        break;
                    case 3: // Check Balance of Account 1
                        System.out.println("Account 1 Balance:");
                        accounts.get(1).printBalance();
                        logger.info("Check Balance of Account 1 processed");
                        break;
                    case 4: // Transfer from Account 1 to Account 2
                        System.out.println("Enter the amount to transfer from Account 1 to Account 2:");
                        double transferAmount = scanner.nextDouble();
                        accounts.get(1).transfer(accounts.get(2), transferAmount);
                        logger.info("Transfer from Account 1 to Account 2 processed");
                        break;
                    case 5: // Transfer from Account 2 to Account 1
                        System.out.println("Enter the amount to transfer from Account 2 to Account 1:");
                        double transferAmount2 = scanner.nextDouble();
                        accounts.get(2).transfer(accounts.get(1), transferAmount2);
                        logger.info("Transfer from Account 2 to Account 1 processed");

                        break;
                    case 6: // Exit
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("account_balances.txt"))) {
                            writer.write("Final account balances are:\n");
                            for (Map.Entry<Integer, BankAccount> entry : accounts.entrySet()) {
                                String balanceInfo = String.format("Account %d Balance: €%.2f\n", entry.getKey(), entry.getValue().getBalance());
                                writer.write(balanceInfo);
                            }
                            System.out.println("Account balances written to 'account_balances.txt'. Exiting program.");
                        } catch (IOException e) {
                            System.out.println("An error occurred while writing to the file.");
                            e.printStackTrace();
                        }
                        logger.info("Program exiting, final balances written to file");

                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                        logger.info("User entered an invalid choice " + choice);
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                logger.info("User entered an invalid choice test");
                scanner.nextLine();
            }
        }
    }
}
