package com.suprun.designpatterns.structural.proxy;

/**
 * SecureBankAccountProxy is the proxy in the Proxy pattern.
 * It controls access to the real BankAccountImpl and adds security checks.
 *
 * @author Yurii_Suprun
 */
public class SecureBankAccountProxy implements BankAccount {

    private final BankAccountImpl realAccount;

    /**
     * Constructs a proxy that wraps a bank account with security controls.
     *
     * @param initialBalance the initial balance for the account
     */
    public SecureBankAccountProxy(double initialBalance) {
        // The proxy controls access to the real account
        this.realAccount = new BankAccountImpl(initialBalance);
    }

    @Override
    public void deposit(double amount) {
        // Perform security checks before allowing deposit
        if (amount > 0) {
            System.out.println("Security Check: Deposit amount validated.");
            realAccount.deposit(amount);
        } else {
            System.out.println("Security Check: Invalid deposit amount. Transaction denied.");
        }
    }

    @Override
    public double getBalance() {
        // Perform security checks before allowing balance inquiry
        System.out.println("Security Check: Balance inquiry requested and authorized.");
        return realAccount.getBalance();
    }
}

