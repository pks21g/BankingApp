package Backend;

import BankInterface.BankInterface;
import Database.JDBC;

import java.sql.*;
import java.util.List;
import java.sql.Date;

public class AccountDAO implements BankInterface<BankAccount> {


    Connection con = JDBC.getConnection();
    long millsec = System.currentTimeMillis();
    Date date = new Date(millsec);

    public AccountDAO(){

    }
    @Override
    public void saveAccount(BankAccount bankAccount) {

        String insertRecord = "insert into Accounts (accountHoldersName, accountType, balance) values (?,?,?)";
        String insertTrans = "insert into Transactions (description, amount, date) values ( ? , ?, ?)";

//TODO fix if exists account in the database
//        if (!ifExists(bankAccount)) {

            try {
                PreparedStatement ps = con.prepareStatement(insertRecord);
                ps.setString(1, bankAccount.getAccountHoldersName());
                ps.setString(2, bankAccount.getAccountType());
                ps.setDouble(3, bankAccount.getBalance());
                ps = con.prepareStatement(insertTrans);

                ps.setString(1, "Int Balance");
                ps.setDouble(2, bankAccount.getBalance());
                ps.setDate(3, date );
                ps.executeUpdate();

                System.out.println("Account saved!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //TODO fix if exists account in database
//        }
//        else{
//            System.out.println("Account already exists");
//        }

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
    public void deposit(int acn, double amount) {
        double newBalance = 0;
        try (Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)){
            ResultSet rs = stm.executeQuery("select accountNumber, balance from Accounts");
            String insertTrans = "insert into Transactions (description, amount, date) values ( ? , ?, ?)";


            while (rs.next()){
                        if( amount <= 0){
                            System.out.println("Invalid deposit amount!");
                        }

                        int id = rs.getInt(1);
                        if (id == acn) {
                            double balance = rs.getDouble("balance");
                            newBalance = balance + amount;
                            rs.updateDouble("balance", newBalance);
                            rs.updateRow();
                        }
                        else{
                            System.out.println("Account not found");
                        }
                        PreparedStatement ps = con.prepareStatement(insertTrans);
                        ps.setString(1, "Deposit");
                        ps.setDouble(2,amount);
                        ps.setDate(3, date);
                        ps.executeUpdate();
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
            }
        }

        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public boolean ifExists(BankAccount bankAccount) {
        try(Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)){
            ResultSet rs = stm.executeQuery("Select accountHoldersName from Accounts");

            while (rs.next()){

                if(
                bankAccount.getAccountHoldersName() != rs.getString("accountHoldersName")){
                    return true;
                }
                break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
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
