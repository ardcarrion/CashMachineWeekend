package rocks.zipcode.atm.bank;

import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegistrationFormTest {

    @Test
    public void getNewAccountIDTest1() {
        String name = "Bob Rob";
        String password = "";
        String email = "default@gmail.com";
        Bank bank = new Bank();
        CreateAccount account = new CreateAccount(bank, name, email, password);
        int id = account.getNewAccountID();
        Assert.assertNotEquals(id, 1000);
        Assert.assertNotEquals(id, 2000);
    }

    @Test
    public void getNewAccountIDTest2() {
        String name = "Bob Rob";
        String password = "";
        String email = "default@gmail.com";
        Bank bank = new Bank();
        CreateAccount account = new CreateAccount(bank, name, email, password);

        int id = account.getNewAccountID();
        Assert.assertTrue(id > 2000);
        Assert.assertTrue(id < 100000);
    }

    @Test
    public void isValidInputTest1() {
        String name = "Bob Rob";
        String password = "";
        String email = "default@gmail.com";
        Bank bank = new Bank();
        CreateAccount account = new CreateAccount(bank, name, email, password);

        Boolean actual = account.isValidInput();
        Assert.assertFalse(actual);
    }
    @Test
    public void isValidInputTest2() {
        String name = "Bob Rob";
        String password = "pass";
        String email = "default@gmail.com";
        Bank bank = new Bank();
        CreateAccount account = new CreateAccount(bank, name, email, password);
        Boolean actual = account.isValidInput();
        Assert.assertTrue(actual);
    }
    @Test
    public void isValidInputTest3() {
        String name = "Bob Rob";
        String password = "";
        String email = "default@gmail.com";
        Bank bank = new Bank();
        CreateAccount account = new CreateAccount(bank, name, email, password);
        Boolean actual = account.isValidInput();
        Assert.assertFalse(actual);
    }
    @Test
    public void isValidInputTest4() {
        String name = "Bob Rob";
        String password = "";
        String email = "default@gmail.com";
        Bank bank = new Bank();
        CreateAccount account = new CreateAccount(bank, name, email, password);

        Boolean actual = account.isValidInput();
        Assert.assertFalse(actual);
    }
    @Test
    public void isValidInputTest5() {
        String name = "Bob Rob";
        String password = "";
        String email = "default@gmail.com";
        Bank bank = new Bank();
        CreateAccount account = new CreateAccount(bank, name, email, password);
        Boolean actual = account.isValidInput();
        Assert.assertFalse(actual);
    }
    @Test
    public void makeAccountTest1() {
        String name = "Bob Rob";
        String password = "password";
        String email = "default@gmail.com";
        Bank bank = new Bank();
        CreateAccount account = new CreateAccount(bank, name, email, password);
        String actual = account.makeAccount();
        int id = account.getId();
        String expected = String.format("%s your account has been successfully created with id %d", name, id);

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void makeAccountTest2() {
        String name = "Bob Rob";
        String password = "password";
        String email = "default@gmail.com";
        Bank bank = new Bank();
        CreateAccount account = new CreateAccount(bank, name, email, password);
        String actual = account.makeAccount();
        int id = account.getId();
        String expected = String.format("%s your account has been successfully created with id %d", name, id);
        Assert.assertEquals(expected, actual);
    }
}