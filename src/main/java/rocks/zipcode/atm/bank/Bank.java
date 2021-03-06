package rocks.zipcode.atm.bank;

import rocks.zipcode.atm.ActionResult;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    public Bank() {
        accounts.put(1000, new BasicAccount(new AccountData(
                1000, "Example 1", "example1@gmail.com", "pass", 500,0
        )));

        accounts.put(2000, new PremiumAccount(new AccountData(
                2000, "Example 2", "example2@gmail.com", "pass", 200,0
        )));
    }

//    public ActionResult<AccountData> addAccountById

    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            return null;
        }
    }
    public ActionResult<AccountData> loanDep(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        account.loanUpdate(amount);

        return ActionResult.success(account.getAccountData());
    }
    public ActionResult<AccountData> deposit(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        account.deposit(amount);

        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        boolean ok = account.withdraw(amount);

        if (ok) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());
        }
    }

    public void addAccount(Integer accountId, String name, String email, String password) {
        accounts.put(accountId, new BasicAccount(new AccountData(accountId, name, email, password, 0, 0 )));

    }
}
