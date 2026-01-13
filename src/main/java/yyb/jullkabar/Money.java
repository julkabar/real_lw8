package yyb.jullkabar;

public class Money {

    private double amount;

    public Money(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void add(double value) {
        this.amount += value;
    }

    public void subtract(double value) {
        this.amount -= value;
    }

    public boolean isNegative() {
        return amount < 0;
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}
