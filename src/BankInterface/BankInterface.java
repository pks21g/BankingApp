package BankInterface;

public interface BankInterface {
    void createAccount(String acHolderName, String acType, double initialDeposit);
    boolean deposit(int acN, double amount);
    boolean withdraw(int acN, double amount);
    boolean deleteAccount(int accountNumber);
    void getAccountInfo(int accountNumber);
    void getBalance(int acN);
    void getTransactionHistory(int acN);

}




