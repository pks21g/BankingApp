import Backend.AccountDAO;
import Backend.BankAccount;

public class BankingDataBase {


    public static void main(String[] args) {
        AccountDAO account = new AccountDAO();
        BankAccount bankAccount = new BankAccount("Derrik", "C", 2000);
        boolean exists = account.ifExists(bankAccount);
        if (exists){
            account.saveAccount(bankAccount);
        }
        else{
            System.out.println("Account already exists");
        }

        account.deposit(27, -5000);

    }
}


