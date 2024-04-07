package BankingInterface;

public interface BankingInterface {

    void createAccount(String accountHolderName, String accountType, double initialDeposit);
    boolean deposit(int accountNumber, double amount);
    boolean withdraw(int accountNumber, double amount);
    boolean getAccountInfo(int accountNumber);
    double getBalance(int accountNumber);
    void getTransactionsHistory(int accountNumber);




}
