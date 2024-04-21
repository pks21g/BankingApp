package Backend;

public class BankAccount {


    private int accountNumber = 1000;
    private String accountHoldersName;
    private String accountType;
    private double balance;

    public BankAccount(String accountHoldersName, String accountType, double initialBalance) {
        setAccountHoldersName(accountHoldersName);
        setAccountType(accountType);
        setBalance(initialBalance);
        accountNumber = getAccountNumber();
    }

    public int getAccountNumber() {
        return accountNumber++;
    }


    public String getAccountHoldersName() {
        return accountHoldersName;
    }

    public void setAccountHoldersName(String accountHoldersName) {
        this.accountHoldersName = accountHoldersName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance += balance;
    }

    @Override
    public String toString() {
        return
                "Account number: + accountNumber | Account holders name: " + accountHoldersName +
                "| Account type: " + accountType +
                "| Balance: " + balance;
    }
}
