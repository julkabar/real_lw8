import org.junit.Test;
import yyb.jullkabar.Account;
import yyb.jullkabar.CompanyCustomer;
import yyb.jullkabar.Customer;
import yyb.jullkabar.PersonCustomer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CustomerTest {

    @Test
    public void testWithdrawPersonWithNormalAccount() {
        Account account = getAccount(false, 34.0);
        Customer customer = getPersonCustomer(account);

        customer.withdraw(10, "EUR");
        assertThat(account.getBalance().getMoney().getAmount(), is(24.0));
    }

    @Test
    public void testWithdrawPersonWithNormalAccountAndOverdraft() {
        Account account = getAccount(false, -10.0);
        Customer customer = getPersonCustomer(account);

        customer.withdraw(10, "EUR");

        assertThat(account.getBalance().getMoney().getAmount(), is(-22.0));
    }

    @Test
    public void testWithdrawCompanyWithPremiumAccountAndOverdraft() {
        Account account = getAccount(true, -10.0);
        Customer customer = getCompanyCustomer(account);

        customer.withdraw(10, "EUR");

        assertThat(account.getBalance().getMoney().getAmount(), is(-20.0));
    }

    @Test
    public void testPrintCustomerDaysOverdrawn() {
        Customer customer = getPersonWithAccount(false);

        assertThat(
                customer.printCustomerDaysOverdrawn(),
                is("danix dan Account: IBAN: RO023INGB434321431241, Days Overdrawn: 9")
        );
    }

    @Test
    public void testPrintCustomerAccountPremium() {
        Customer customer = getPersonWithAccount(true);
        assertThat(
                customer.printCustomerAccount(),
                is("Account: IBAN: RO023INGB434321431241, Money: 34.0 EUR, Account type: premium")
        );
    }

    // ---------- helpers ----------

    private Customer getPersonWithAccount(boolean premium) {
        Account account = getAccount(premium, 34.0);
        return getPersonCustomer(account);
    }

    private Account getAccount(boolean premium, double amount) {
        Account account = new Account(premium, 9);
        account.setIban("RO023INGB434321431241");
        account.setBalance(amount, "EUR");
        return account;
    }

    private Customer getPersonCustomer(Account account) {
        Customer customer = new PersonCustomer(
                "danix", "dan", "dan@mail.com", account
        );
        account.setCustomer(customer);
        return customer;
    }

    private Customer getCompanyCustomer(Account account) {
        Customer customer = new CompanyCustomer(
                "company", "company@mail.com", account, 0
        );
        account.setCustomer(customer);
        return customer;
    }
}
