package rocks.zipcode.atm.bank;

import rocks.zipcode.atm.CashMachineApp;

import java.util.Random;

public class CreateAccount {
    private Bank bank;
    private String name;
    private String email;
    private String password;
    private Integer id;

    public Integer getId() {
        return id;
    }



    public CreateAccount(Bank bank, String name, String email, String password) {
        this.bank = bank;
        this.name = name;
        this.email = email;
        this.password = password;

    }

    public Integer getNewAccountID() {

        Random random = new Random();
        Integer accountId = (random.nextInt(100) + 3) * 1000;
        while (bank.getAccountById(accountId) != null) accountId = (random.nextInt(100) + 3) * 1000;
        return accountId;

    }

    public Boolean isValidInput() {
        if (name.isEmpty() | name.split(" ").length < 2) {
            return false;
        }
        if (email.isEmpty()) {
            return false;
        }
        if (!email.contains("@") | !email.contains(".")) {
            return false;
        }
        if (password.length() == 0) {
            return false;
        }
        return true;
    }

    public String makeAccount() {
        Integer id = getNewAccountID();
        this.id = id;
        bank.addAccount(id, name, email, password);
        return String.format("%s your account has been successfully created with id %d", name, id);
    }


}
