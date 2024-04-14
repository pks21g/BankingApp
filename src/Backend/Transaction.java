package Backend;

import java.util.Date;

public class Transaction {

        private double amount;
        private String description;
        private Date date;

        public Transaction(String description, double amount){
            this.description = description;
            this.amount = amount;
            this.date = new Date();

        }

        public String toString(){
            return "Transaction date: " + date + " Amount: " + amount + " Description: " + description;
        }


    }
