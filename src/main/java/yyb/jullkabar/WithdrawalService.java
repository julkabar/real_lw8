package yyb.jullkabar;

public class WithdrawalService {

    public void withdraw(Customer customer,
                         Account account,
                         double sum,
                         String currency,
                         double companyOverdraftDiscount) {
        if (!account.getCurrency().equals(currency)) {
            throw new RuntimeException("Can't extract withdraw " + currency);
        }
        double discount = calculateDiscount(customer.getCustomerType(),
                account,
                sum,
                companyOverdraftDiscount);
        withdrawAmount(account, sum, discount);
    }

    private double calculateDiscount(CustomerType customerType, Account account,
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

    private void withdrawAmount(Account account, double sum, double discount) {
        if (account.getMoney() < 0) {
            account.setMoney((account.getMoney() - sum) - discount);
        } else {
            account.setMoney(account.getMoney() - sum);
        }
    }
}
