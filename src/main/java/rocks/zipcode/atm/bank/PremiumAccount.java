package rocks.zipcode.atm.bank;

/**
 * @author ZipCodeWilmington
 */
public class PremiumAccount extends Account {

    private static final int OVERDRAFT_LIMIT = 100;                                 // makes a overdraft limit of 100

    public PremiumAccount(AccountData accountData) {
        super(accountData);
    }

    @Override
    protected boolean canWithdraw(Float amount) {
        return getBalance() + OVERDRAFT_LIMIT >= amount;
    }
}


// PREMIUM ACCT is the same as a basic acct but it has overdraft protection up to $100.00
