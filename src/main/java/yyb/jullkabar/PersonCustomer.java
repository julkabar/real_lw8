package yyb.jullkabar;

public class PersonCustomer extends Customer {

    public PersonCustomer(String name, String surname,
                          String email, Account account) {
        super(name, surname, email, account);
    }

    @Override
    public double overdraftFee(double sum) {
        return sum * account.overdraftFee();
    }
}
