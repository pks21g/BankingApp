package Backend;

import BankInterface.BankInterface;
import Database.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements BankInterface<BankAccount> {

    List<BankAccount> bankAccounts;
    Connection con = JDBC.getConnection();

    public AccountDAO(){
        this.bankAccounts = new ArrayList<>();

    }
    @Override
    public void saveAccount(BankAccount bankAccount) {



        String insertRecord = "insert into Accounts (accountNumber, accountHoldersName, accountType, balance) values (?,?,?, ?)";

        try{

            PreparedStatement ps = con.prepareStatement(insertRecord);
            ps.setInt(1, bankAccount.getAccountNumber());
            ps.setString(2, bankAccount.getAccountHoldersName());
            ps.setString(3, bankAccount.getAccountType());
            ps.setDouble(4, bankAccount.getBalance());
            ps.executeUpdate();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        bankAccounts.add(bankAccount);
    }


    @Override
    public void updateAccount(String accountHolderName, BankAccount bankAccount) {
        bankAccounts.replaceAll(bankAccount1 -> (bankAccount));
    }

    @Override
    public void deleteAccount(BankAccount bankAccount) {
        bankAccounts.remove(bankAccount);

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

    }

    @Override
    public void printAllAccounts() {
        for( BankAccount ac : bankAccounts){
            System.out.println(ac);
        }
    }

    @Override
    public boolean ifExists(BankAccount bankAccount) {
        try(Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)){
            ResultSet rs = stm.executeQuery("Select accountHoldersName from Accounts");

            while (rs.next()){
                if(
                bankAccount.getAccountHoldersName() == rs.getString("accountHoldersName")){
                    System.out.println("Account already exists");
                    return true;
                }
                break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public List<BankAccount> getBankAccounts(){
        return bankAccounts;
    }
}
