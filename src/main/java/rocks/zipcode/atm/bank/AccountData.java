package rocks.zipcode.atm.bank;

/**
 * @author ZipCodeWilmington
 */
public final class AccountData {

    private final int id;
    private final String name;
    private final String email;
    private  Integer loan;
    private final int balance;
    private final String password;


    AccountData(int id, String name, String email, String password, int balance,Integer loan) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.loan = loan;
        this.password = password;

    }
    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getBalance() {
        return balance;
    }
    public Integer getLoanAmount(){



        return loan;
    }
    public void setloanAmount(Integer loan){
        this.loan = loan;
    }

    @Override
    public String toString() {
        return "Account id: " + id + '\n' +
                "Name: " + name + '\n' +
                "Email: " + email + '\n' +
                "Balance: " + balance +
                "\nLoan Amount Due " + loan;
    }


}
