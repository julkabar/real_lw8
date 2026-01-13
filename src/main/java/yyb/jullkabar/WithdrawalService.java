package yyb.jullkabar;

public class WithdrawalService {

    public void withdraw(Customer customer,
                         Account account,
                         double sum,
                         String currency,
                         double companyOverdraftOverdraftFee) {
        if (!account.getCurrency().equals(currency)) {
            throw new RuntimeException("Can't extract withdraw " + currency);
        }
        double overdraftFee = calculateOverdraftFee(customer.getCustomerType(),
                account,
                sum,
                companyOverdraftOverdraftFee);
        applyWithdrawal(account, sum, overdraftFee);
    }

    private double calculateOverdraftFee(CustomerType customerType, Account account,
                                     double sum, double companyOverdraftDiscount) {
        if (account.getType().isPremium()) {
            return switch (customerType) {
                case COMPANY ->
                        sum * account.overdraftFee() * companyOverdraftDiscount / 2;
                case PERSON ->
                        sum * account.overdraftFee();
            };
        } else {
            return switch (customerType) {
                case COMPANY ->
                        sum * account.overdraftFee() * companyOverdraftDiscount;
                case PERSON ->
                        sum * account.overdraftFee();
            };
        }
    }

    private void applyWithdrawal(Account account, double sum, double discount) {
        if (account.getMoney() < 0) {
            account.setMoney((account.getMoney() - sum) - discount);
        } else {
            account.setMoney(account.getMoney() - sum);
        }
    }
}
