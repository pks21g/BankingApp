package Backend;

import java.util.ArrayList;
import java.util.List;

public class BaseAccount {

        private String acHolderName;
        private String acType;
        private double balance;
        private List<Transaction> transactionsHistory;

        public BaseAccount(){
        }

        public BaseAccount(String acHolderName, String acType, double initialDeposit) {
            this.transactionsHistory = new ArrayList<>();
            this.acHolderName = acHolderName;
            this.acType = acType;
            balance = initialDeposit;
            transactionsHistory.add(new Transaction("Initial deposit", initialDeposit));
        }

        public void deposit(double amount){
            balance += amount;
            transactionsHistory.add(new Transaction("deposit", +amount));
        }

        public void withdraw(double amount){
            balance -= amount;
            transactionsHistory.add(new Transaction("withdraw", +amount));

        }

        public double getBalance(){
            return balance;
        }

        public void getTransactionsHistory(){
            for (Transaction transaction : transactionsHistory){
                System.out.println(transaction);
            }
        }

        @Override
        public String toString() {
            return
                    "Account Holder's Name: " + acHolderName +
                            " Account Type: " + acType +
                            " Balance: " + balance;
        }
    }

