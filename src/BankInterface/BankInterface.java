package BankInterface;

public interface BankInterface<T> {
    void saveAccount(T t);
    void updateAccount( String accountHolderName, T t);
    void deleteAccount(T t);
    void deposit(int accountNumber, double amount);
    void withdraw(int accountNumber, double amount);
    void printAllAccounts();
    boolean ifExists(T t);
}




