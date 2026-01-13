package yyb.jullkabar;

public class WithdrawalService {

    public void withdraw(Customer customer,
                         Account account,
                         double sum,
                         String currency) {
        if (!account.getCurrency().equals(currency)) {
            throw new RuntimeException("Can't extract withdraw " + currency);
        }
        double overdraftFee = customer.overdraftFee(sum);
        applyWithdrawal(account, sum, overdraftFee);
    }

    private void applyWithdrawal(Account account, double sum, double discount) {
        if (account.getMoney() < 0) {
            account.setMoney((account.getMoney() - sum) - discount);
        } else {
            account.setMoney(account.getMoney() - sum);
        }
    }
}
