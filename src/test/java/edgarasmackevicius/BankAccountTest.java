package edgarasmackevicius;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

// Object Lifespan: Each test method in JUnit is run in isolation. When a test method finishes execution, all objects created within it are typically eligible for garbage collection, as long as there are no references to them from static fields or other persistent structures.
public class BankAccountTest {
    @Test
    public void testDefaultConstructor() {
        BankAccount account = new BankAccount();
        Assertions.assertEquals(0, account.getBalance(), 0.001);

    }

    @Test
    public void testConstructorWithInitialBalance() {
        BankAccount account = new BankAccount(100);
        Assertions.assertEquals(100, account.getBalance(), 0.001);
    }

    @Test
    public void testWithdraw() {
        BankAccount account = new BankAccount(100);
        account.withdraw(50);
        Assertions.assertEquals(50, account.getBalance(), 0.001);
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        BankAccount account = new BankAccount(50);
        account.withdraw(100);
        Assertions.assertEquals(50, account.getBalance(), 0.001);
    }

    @Test
    public void testTransfer() {
        BankAccount sourceAccount = new BankAccount(100);
        BankAccount targetAccount = new BankAccount(50);
        sourceAccount.transfer(targetAccount, 30);
        Assertions.assertEquals(70, sourceAccount.getBalance(), 0.001);
        Assertions.assertEquals(80, targetAccount.getBalance(), 0.001);
    }

    @Test
    public void testTransferInsufficientFunds() {
        BankAccount sourceAccount = new BankAccount(20);
        BankAccount targetAccount = new BankAccount(50);
        sourceAccount.transfer(targetAccount, 30);
        Assertions.assertEquals(20, sourceAccount.getBalance(), 0.001);
        Assertions.assertEquals(50, targetAccount.getBalance(), 0.001);
    }
    // TODO Add testing for file creation and exceptions?
}