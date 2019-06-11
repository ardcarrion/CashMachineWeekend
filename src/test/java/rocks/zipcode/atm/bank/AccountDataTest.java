package rocks.zipcode.atm.bank;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountDataTest {
    AccountData accountData = new AccountData(7777,"name","email","password",5555,2222);

    @Test
    public void getPasswordTest() {
        String expected = "password";
        String actual = accountData.getPassword();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getIdTest() {
        Integer expected = 7777;
        Integer actual = accountData.getId();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getNameTest() {
        String expected = "name";
        String actual = accountData.getName();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getEmailTest() {
        String expected = "email";
        String actual = accountData.getEmail();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getBalanceTest() {
        Integer expected = 5555;
        Integer actual = accountData.getBalance();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getLoanAmountTest() {
        Integer expected = 2222;
        Integer actual = accountData.getLoanAmount();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void setloanAmount() {
        Integer expected = 1111;
        accountData.setloanAmount(1111);
        Integer actual = accountData.getLoanAmount();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void toString1() {
        String actual = accountData.toString();
        String expected = "Account id: " + 7777 + '\n' +
                "Name: " + "name" + '\n' +
                "Email: " + "email" + '\n' +
                "Balance: " + 5555 +
                "\nLoan Amount Due " + 2222;
    }
}