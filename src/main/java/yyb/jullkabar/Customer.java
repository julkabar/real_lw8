package yyb.jullkabar;

public abstract class Customer {

    protected String name;
    protected String surname;
    protected String email;
    protected Account account;

    public Customer(String name, String surname, String email, Account account) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.account = account;
    }

    public abstract double overdraftFee(double sum);

    public void withdraw(double sum, String currency) {
        WithdrawalService service = new WithdrawalService();
        service.withdraw(
                this,
                account,
                sum,
                currency
        );
    }

    public Account getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String printCustomerDaysOverdrawn() {
        String fullName = name + " " + surname + " ";
        String accountDescription = "Account: IBAN: " + account.getIban() + ", Days Overdrawn: " + account.getDaysOverdrawn();
        return fullName + accountDescription;
    }

    public String printCustomerMoney() {
        String fullName = name + " " + surname + " ";
        String accountDescription = "";
        accountDescription += "Account: IBAN: " + account.getIban() + ", Money: " + account.getMoney();
        return fullName + accountDescription;
    }

    public String printCustomerAccount() {
        return account.printAccountInfo();
    }
}
