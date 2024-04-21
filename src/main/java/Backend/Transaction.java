package Backend;

import java.util.Date;

public class Transaction {
    private String description;
    private double amount;
    private Date date;

    public Transaction(String description, double amount) {
        this.description = description;
        this.amount = amount;
        this.date = new Date();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction: description: " + description +
                 " amount: " + amount + "date: " + date;
    }
}
