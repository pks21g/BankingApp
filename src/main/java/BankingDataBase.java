import Backend.AccountDAO;
import Backend.BankAccount;

public class BankingDataBase {


    public static void main(String[] args) {
        AccountDAO account = new AccountDAO();
        BankAccount bankAccount = new BankAccount();
        account.deposit(1000, 4000);
    }
}


