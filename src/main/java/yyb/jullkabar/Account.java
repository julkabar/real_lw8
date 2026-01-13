package yyb.jullkabar;

public class Account {

    private String iban;
    private boolean premium;
    private int daysOverdrawn;
    private Balance balance;
    private Customer customer;

    public Account(boolean premium, int daysOverdrawn) {
        super();
        this.premium = premium;
        this.daysOverdrawn = daysOverdrawn;
    }

    public double bankcharge() {
        double result = 4.5;
        result += overdraftCharge();
        return result;
    }

    private double overdraftCharge() {
        if (premium) {
            double result = 10;
            if (getDaysOverdrawn() > 7)
                result += (getDaysOverdrawn() - 7) * 1.0;
            return result;
        } else
            return getDaysOverdrawn() * 1.75;
    }

    public double overdraftFee() {
        if (premium) {
            return 0.10;
        } else {
            return 0.20;
        }
    }

    public String printAccountInfo() {
        return "Account: IBAN: " + iban + ", , Balance: " + balance.toString()
                + ", Account type: " + getAccountType();
    }

    public String printDaysOverdrawn() {
        return "Account: IBAN: " + iban
                + ", Days Overdrawn: " + daysOverdrawn;
    }

    public int getDaysOverdrawn() {
        return daysOverdrawn;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(double amount, String currency) {
        this.balance = new Balance(new Money(amount), currency);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isPremium() {
        return premium;
    }

    public String getAccountType() {
        return premium ? "premium" : "normal";
    }
}
