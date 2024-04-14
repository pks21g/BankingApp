package Backend;

import BankInterface.BankInterface;

import java.util.HashMap;
import java.util.Map;

public class BaseAccountCRUD implements BankInterface {


        private Map<Integer, BaseAccount> accounts = null;
        private int acN = 1000;
        private BaseAccount baseAccount;

        public BaseAccountCRUD(){
            this.accounts = new HashMap<>();
        }
        @Override
        public void createAccount(String acHolderName, String acType, double initialDeposit) {
            baseAccount = new BaseAccount(acHolderName, acType, initialDeposit);
            accounts.put(acN++, baseAccount);
        }

        @Override
        public boolean deleteAccount(int accountNumber) {
            if (accounts.containsKey((Integer) accountNumber)){
                accounts.remove(accountNumber);
                return true;
            }
            return false;

        }

        @Override
        public void getAccountInfo(int accountNumber) {
            baseAccount = accounts.get(accountNumber);
            if(baseAccount == null)
                return;
            System.out.println(baseAccount.toString());

        }

        @Override
        public void getBalance(int acN) {
            if (accounts.containsKey(acN)){
                System.out.println(baseAccount.getBalance());
            }
            else
                return;
        }

        @Override
        public void getTransactionHistory(int acN) {
            baseAccount = accounts.get(acN);
            if(baseAccount != null){
                baseAccount.getTransactionsHistory();
            }
        }

        @Override
        public boolean deposit(int acN, double amount) {
            baseAccount = accounts.get(acN);
            if (baseAccount == null)
                return false;
            if(amount <= 0 )
                return false;

            baseAccount.deposit(amount);
//        accounts.put(acN, baseAccount);

            return true;
        }

        @Override
        public boolean withdraw(int acN, double amount) {
            baseAccount = accounts.get(acN);
            if (baseAccount == null)
                return false;
            if(baseAccount.getBalance() < amount){
                return false;
            }
            baseAccount.withdraw(amount);
//        accounts.put(acN, baseAccount);
            return true;
        }

        public void getAccounts(){
            System.out.println("Accounts:");
            if (accounts.size() == 0 || accounts == null){
                System.out.println("No accounts to show!");
            }
            accounts.forEach((k, v) ->{
                System.out.println("Account Number: " + k + " " + v);
            });
        }
    }
