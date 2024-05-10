package com.suprun.designpatterns.structural;

/**
 * @author Yurii_Suprun
 */
public class SecureBankAccountProxy implements BankAccount {
    private BankAccountImpl realAccount;

    public SecureBankAccountProxy(double initialBalance) {
        // The proxy controls access to the real account
        this.realAccount = new BankAccountImpl(initialBalance);
    }

    @Override
    public void deposit(double amount) {
        // Perform security checks before allowing deposit
        if (amount > 0) {
            realAccount.deposit(amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    @Override
    public double getBalance() {
        // Perform security checks before allowing balance inquiry
        System.out.println("Balance inquiry requested.");
        return realAccount.getBalance();
    }
}

