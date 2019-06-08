package rocks.zipcode.atm.bank;
import javafx.scene.control.*;
import javafx.scene.control.Alert;

/**
 * @author ZipCodeWilmington
 */
public abstract class Account {

    private AccountData accountData;

    public Account(AccountData accountData) {
        this.accountData = accountData;
    }

    public AccountData getAccountData() {
        return accountData;
    }

    public void deposit(int amount) {
        updateBalance(getBalance() + amount);
    }

    public boolean withdraw(int amount) {
        if (canWithdraw(amount)) {
            updateBalance(getBalance() - amount);
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Insufficent Funds");
            alert.setHeaderText("You do not have the necssary funds to withdraw: "+ amount);
            alert.setContentText("Check out our New Lending options under loans.\n Currently rate are as low as 25%");

            alert.showAndWait();
            return false;
        }
    }

    protected boolean canWithdraw(int amount) {
        return getBalance() >= amount;
    }

    public int getBalance() {
        return accountData.getBalance();
    }

    private void updateBalance(int newBalance) {
        accountData = new AccountData(accountData.getId(), accountData.getName(), accountData.getEmail(),
                newBalance,accountData.getLoanAmount());
    }
}
