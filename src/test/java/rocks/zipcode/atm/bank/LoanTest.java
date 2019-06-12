package rocks.zipcode.atm.bank;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoanTest {
    Loan test = new Loan(1000.0,750.0);
    Loan test2 = new Loan(55000.0,660.0);
    @Test
    public void getLnAmount1() {

        Double expected = 1000.0;
        Double actual = test.getLnAmount();
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void getLnAmount2() {

        Double expected = 55000.0;
        Double actual = test2.getLnAmount();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void setLnAmount() {
        test.setLnAmount(100000.0);
        Double expected = 100000.0;
        Double actual = test.getLnAmount();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getCreditScore() {

        Double expected = 750.0;
        Double actual = test.getCreditScore();
        Assert.assertEquals(expected,actual);

    }
    @Test
    public void getCreditScore2() {

        Double expected = 660.0;
        Double actual = test2.getCreditScore();
        Assert.assertEquals(expected,actual);

    }

    @Test
    public void setCreditScore() {
        test.setCreditScore(500.0);
        Double expected = 500.0;
        Double actual = test.getCreditScore();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void loanYes() {
        Boolean expected = true;
        Boolean actual = test.loanYes(test.getLnAmount(),test.getCreditScore());
        Assert.assertEquals(expected,actual);

    }
    @Test
    public void loanYesFail() {
        test.setCreditScore(500.0);
        Boolean expected = false;
        Boolean actual = test.loanYes(test.getLnAmount(),test.getCreditScore());
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void loanYes2fail() {
        Boolean expected = false;
        Boolean actual = test2.loanYes(test2.getLnAmount(),test2.getCreditScore());
        Assert.assertEquals(expected,actual);

    }
    @Test
    public void loanYes2() {
        test2.setCreditScore(740.0);
        Boolean expected = false;
        Boolean actual = test2.loanYes(test2.getLnAmount(),test2.getCreditScore());
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void setIntRate() {

        test.setIntRate(.25);
        Double expected =.25;
        Double actual = test.getIntRate();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getIntRate() {
        
        Double expected =.25;
        Double actual = test.getIntRate();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void setLoanStatus() {
    }

    @Test
    public void getLoanStatus() {
    }
}