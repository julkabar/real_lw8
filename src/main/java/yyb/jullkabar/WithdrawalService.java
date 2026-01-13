package yyb.jullkabar;

public class WithdrawalService {

    public void withdraw(Customer customer,
                         Account account,
                         double sum,
                         String currency) {
        Balance balance = account.getBalance();
        if (!balance.getCurrency().equals(currency)) {
            throw new RuntimeException("Can't extract withdraw " + currency);
        }
        double overdraftFee = customer.overdraftFee(sum);
        applyWithdrawal(account, sum, overdraftFee);
    }

    private void applyWithdrawal(Account account, double sum, double discount) {
        Balance balance = account.getBalance();
        if (balance.isNegative()) {
            balance.subtract(sum + discount);
        } else {
            balance.subtract(sum);
        }
    }
}
