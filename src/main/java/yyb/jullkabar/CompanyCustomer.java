package yyb.jullkabar;

public class CompanyCustomer extends Customer {

    private final double companyOverdraftDiscount;

    public CompanyCustomer(String name, String email,
                           Account account, double companyOverdraftDiscount) {
        super(name, null, email, account);
        this.companyOverdraftDiscount = companyOverdraftDiscount;
    }

    @Override
    public double overdraftFee(double sum) {
        if (account.isPremium()) {
            return sum * account.overdraftFee() * companyOverdraftDiscount / 2;
        }
        return sum * account.overdraftFee() * companyOverdraftDiscount;
    }
}
