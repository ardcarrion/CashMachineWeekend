package rocks.zipcode.atm.bank;

public class Loan {
    private Double lnAmount;
    private Double creditScore;
    private Double intRate;

    public Loan(Double lnAmount,Double creditScore) {
        this.creditScore = creditScore;
        this.lnAmount = lnAmount;

    }
    public Loan(Double intRate){
        this.intRate = intRate;
    }


    public Double getLnAmount() {
        return lnAmount;
    }

    public void setLnAmount(Double lnAmount) {
        this.lnAmount = lnAmount;
    }

    public Double getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Double creditScore) {
        this.creditScore = creditScore;
    }
    public Boolean loanYes(Double creditScore,Double lnAmount){
        boolean approval = false;
        if(creditScore> 750 && lnAmount <100000){
            approval = true;
        }else if(creditScore< 750 && creditScore> 650 && lnAmount <50000){
            approval = true;
        }else if(creditScore < 650 && lnAmount < 10000){
            approval=true;
        }
        return approval;
    }

    public void  setIntRate(Double creditScore) {
        if(creditScore> 750){
            intRate = .05;
        }if(creditScore< 750 && creditScore> 650){
            intRate = .10;
        }if(creditScore < 650){
            intRate=.25;
        }


    }

    public Double getIntRate() {
        return intRate;
    }
}
