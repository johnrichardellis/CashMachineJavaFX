package rocks.zipcode.atm.bank;

import rocks.zipcode.atm.ActionResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    public Bank() {                                                                                 // bank pulls all this account data
        accounts.put(1, new BasicAccount(new AccountData(
                1, "Example 1", "example1@gmail.com", 500F                // id1000 has 500bal basic user
        )));

        accounts.put(2, new PremiumAccount(new AccountData(
                2, "Example 2", "example2@gmail.com", 200F                //is2000 has 200bal prem user
        )));

        accounts.put(3, new BasicAccount(new AccountData(
                3, "Lou the Dog", "iAmADog@gmail.com", 50F                // lou dog basic user
        )));

        accounts.put(4, new BasicAccount(new AccountData(
                4, "Henry the Cat", "iAmACat@gmail.com", 20F                // henry the cat basic user
        )));

        accounts.put(5, new PremiumAccount(new AccountData(
                5, "James Franklin", "jimmyPSUprem@psu.edu", 88000000F          // James Franklin prem user
        )));

        accounts.put(6, new PremiumAccount(new AccountData(
                6, "AI", "theAnswerPrem@gmail.com", -50F                      // Allen Iverson prem user
        )));

    }

    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("No account with id: " + id + "\nTry account 1000 or 2000");
        }
    }

    public ActionResult<AccountData> deposit(AccountData accountData, Float amount) {
        Account account = accounts.get(accountData.getId());
        account.deposit(amount);

        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, Float amount) {
        Account account = accounts.get(accountData.getId());
        boolean ok = account.withdraw(amount);

        if (ok) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());
        }
    }

    public Map<Integer, Account> getAccounts() {
        return this.accounts;
    }
}
