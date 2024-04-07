package Backend;

import Validation.Validate;

import java.util.ArrayList;
import java.util.List;

public class Account {
    int accountNumber;
    String accountHoldersName;
    String accountType;
    double balance;
    List<Transaction> transactions;

    public Account(int accountNumber, String accountHoldersName, String accountType, double initialBalance){
        this.accountNumber = accountNumber;
        this.accountHoldersName = accountHoldersName;
        this.accountType = accountType;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }


    public void deposit(double amount){
        if(!Validate.isValidDeposit(amount))
            return;
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
    }

    public void withdrawal(double amount){
        balance -= amount;
        transactions.add(new Transaction("Withdrawal", -amount));
    }
    public int getAccountNumber(){
        return  accountNumber;
    }

    public double getBalance(){
        return balance;
    }

    public String toString(){
        return "Account Number: " + accountNumber + "\n" +
                "Account Holder's Name: " + accountHoldersName + "\n" +
                "Account Type: " + accountType + "\n" +
                "Account Balance: " + balance ;
    }

    public void getTransactionsHistory() {
        for (Transaction tns : transactions) {
            System.out.println();
            System.out.println(tns.toString());
        }
    }
}
