package Backend;

import BankInterface.BankInterface;
import Database.JDBC;

import java.sql.*;
import java.util.List;

public class AccountDAO implements BankInterface<BankAccount> {

    private int accountNumber = 1000;
    Connection con = JDBC.getConnection();

    public AccountDAO(){
        this.accountNumber++;

    }
    @Override
    public void saveAccount(BankAccount bankAccount) {

        String insertRecord = "insert into Accounts (accountNumber, accountHoldersName, accountType, balance) values (?,?,?, ?)";

        try{
            PreparedStatement ps = con.prepareStatement(insertRecord);
            ps.setInt(1, accountNumber);
            ps.setString(2, bankAccount.getAccountHoldersName());
            ps.setString(3, bankAccount.getAccountType());
            ps.setDouble(4, bankAccount.getBalance());
            ps.executeUpdate();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }


    @Override
    public void updateAccount(String accountHolderName, BankAccount bankAccount) {
    }

    @Override
    public void closeAccount(int ID) {
        try{
           PreparedStatement ps =
                   con.prepareStatement("delete from Accounts where ID = ?");
           ps.setInt(1, ID);
           ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deposit(int accountNumber, double amount) {
        try (Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)){
            ResultSet rs = stm.executeQuery("select ID, balance from Accounts");

            while (rs.next()){
                        if( amount <= 0){
                            System.out.println("Invalid deposit amount!");
                        }

                        int id = rs.getInt(1);
                        if (id == accountNumber) {
                            double balance = rs.getDouble("balance");
                            rs.updateDouble("balance", balance + amount);
                            rs.updateRow();
                        }
                        else{
                            System.out.println("Account not found");
                        }
                break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void withdraw(int accountNumber, double amount) {
        try (Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)){
            ResultSet rs = stm.executeQuery("select ID, balance from Accounts");

            while (rs.next()){
                if( amount <= 0){
                    System.out.println("Invalid deposit amount!");
                }

                int id = rs.getInt(1);
                if (id == accountNumber) {
                    double balance = rs.getDouble("balance");
                    if(balance >= amount) {
                        rs.updateDouble("balance", balance - amount);
                        rs.updateRow();
                    }
                    else {
                        System.out.println("Insufficient fund!");
                    }
                }
                else{
                    System.out.println("Account not found");
                }
                break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void printAllAccounts() {
        String heading =
                String.format("%-5s %-15s %-25s %-15s %-10s" , "ID", "Account Number", "Accountholder's Name", "Account Type", "Balance");
        System.out.println(heading);
        for (int i = 0; i <72; i++){
            System.out.print("-");
        }
        System.out.println();
        try {
            String sql = "select * from Accounts";
            PreparedStatement p = con.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                int acN = rs.getInt(2);
                String acHN = rs.getString(3);
                String acT = rs.getString(4);
                double bal = rs.getDouble(5);
                String output = String.format("%-5s %7s %15s %24s %15s", id, acN, acHN, acT, bal);
                System.out.println(output);
//                System.out.println(
//                        "ID:" + id + "\t\tAC/N:" + acN + "\t\tACH/N:" +
//                                acHN + "\t\tAC/T:" + acT + "\t\tBalance:" + bal
//                );
            }
        }

        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public boolean ifExists(BankAccount bankAccount) {
        boolean flag = false;
        try(Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)){
            ResultSet rs = stm.executeQuery("Select accountHoldersName from Accounts");

            while (rs.next()){
                if(
                bankAccount.getAccountHoldersName() == rs.getString("accountHoldersName")){
                    flag = true;
                }
                break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }

    @Override
    public double checkBalance(int accountNumber) {
        double balance = 0;
        String query = "select * from Accounts";
        try{
           Statement stm = con.createStatement();
           ResultSet rs = stm.executeQuery(query);
           while (rs.next()){
               if(rs.getInt("accountNumber") == accountNumber ){
                   balance = rs.getDouble("balance");
                   System.out.println("Current Balance: " + balance);
               }
               else {
                   System.out.println("Account not found");
               }
               break;
           }
        }
         catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return balance;
    }

    public List<BankAccount> getBankAccounts(){
        return null;
    }
}
