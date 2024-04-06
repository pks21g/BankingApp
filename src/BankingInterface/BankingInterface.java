package BankingInterface;

import java.util.List;

public interface BankingInterface {
    enum accountType {
        CHECKING,
        SAVING,
        STUDENT_CHECKING
    }
    void createAccount(int accountHolderName, accountType accountType, double initialDeposit);
    void deposit(int accountNumber, double depositAmount);
    void withdraw(int accountNumber, double withdrawalAmount);
    void checkBalance(int accountNumber);
    void getTransactionHistory();
    void closeAccount(int accountNumber);
    void Transfer(int fromAccountNo, int toAccountNo, double amount);
    


}
