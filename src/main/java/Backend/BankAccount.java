package Backend;

public class BankAccount {


    private String accountHoldersName;
    private String accountType;
    private double balance;

    public BankAccount(){
        accountHoldersName = "";
        accountType = "C";
        balance = 0;
    }

    public BankAccount(String accountHoldersName, String accountType, double initialBalance) {
        setAccountHoldersName(accountHoldersName);
        setAccountType(accountType);
        setBalance(initialBalance);
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
                "Account holders name: " + accountHoldersName +
                "| Account type: " + accountType +
                "| Balance: " + balance;
    }
}
