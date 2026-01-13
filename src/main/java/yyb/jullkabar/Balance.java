package yyb.jullkabar;

public class Balance {

    private Money money;
    private String currency;

    public Balance(Money money, String currency) {
        this.money = money;
        this.currency = currency;
    }

    public Money getMoney() {
        return money;
    }

    public String getCurrency() {
        return currency;
    }

    public boolean isNegative() {
        return money.isNegative();
    }

    public void subtract(double amount) {
        money.subtract(amount);
    }

    @Override
    public String toString() {
        return money + " " + currency;
    }
}
