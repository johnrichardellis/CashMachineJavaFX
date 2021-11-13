package rocks.zipcode.atm.bank;

/**
 * @author ZipCodeWilmington
 */
public final class AccountData {

    private final int id;
    private final String name;
    private final String email;

    private final Float balance;

    AccountData(int id, String name, String email, Float balance) {               // constructor for new account
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }                                              // getter ID

    public String getName() {
        return name;
    }                                       // getter name

    public String getEmail() {
        return email;
    }                                   // getter email

    public Float getBalance() {
        return balance;
    }                                   // getter balance

    @Override
    public String toString() {                                                      // converting account set up to strings
       String overDraftWarning = balance + "";
       if (balance < 0) {
           return  "You need more money! Your account is overdrawn." + '\n' +
                   "Account id: " + id + '\n' +
                   "Name: " + name + '\n' +
                   "Email: " + email + '\n' +
                   "Balance: " + overDraftWarning;
       }

        return "Account id: " + id + '\n' +
                "Name: " + name + '\n' +
                "Email: " + email + '\n' +
                "Balance: " + overDraftWarning;
    }
}
