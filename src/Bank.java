import Backend.BaseAccountCRUD;

import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        boolean depositSuccess;
        boolean withdrawSuccess;
        String name = "";

        name = getName("Enter first name", name);
        System.out.println(name);

        BaseAccountCRUD bankOps = new BaseAccountCRUD();
        System.out.println("First Account: ");
        bankOps.createAccount("Amit", "Checking", 1000);
        System.out.print("Initial deposit 1000: "); bankOps.getBalance(1000);
        depositSuccess = bankOps.deposit(1000, -500);
        if(!depositSuccess){
            System.out.println("Deposit fail, invalid amount!");
        }
        System.out.print("After -500 deposit: "); bankOps.getBalance(1000);
        withdrawSuccess = bankOps.withdraw(1000, 70000);
        if(!withdrawSuccess){
            System.out.println("Withdawal failed, insufficient funds!");
        }
        System.out.print("After 70000 withdrawal: "); bankOps.getBalance(1000);
        System.out.println("Transaction history: ");
        bankOps.getTransactionHistory(1000);
        System.out.print("Current Balance: "); bankOps.getBalance(1000);

        System.out.println("Second Account: ");
        bankOps.createAccount("Jaydev", "Saving", 2000);
        System.out.print("Initial deposit 2000: "); bankOps.getBalance(1001);

        bankOps.deposit(1001, 5500);
        System.out.print("After 5500 deposit: ");bankOps.getBalance(1001);
        bankOps.withdraw(1001, 1500);
        System.out.print("After 1500 withdrawal: ");bankOps.getBalance(1001);
        bankOps.getTransactionHistory(1001);

        System.out.print("Current Balance: "); bankOps.getBalance(1001);


    }

    private static String getName(String message, String name){
        Scanner scan = new Scanner(System.in);
        System.out.print(message + ": ");
        name = scan.next();
        if (name.isBlank() || name == null || !name.matches("^[A-Za-z]*")){
            System.out.println("Invalid Name: ");
        }
        return name;
    }

}
