package rocks.zipcode.atm.bank;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class Loan {


    private Double lnAmount;
    private Double creditScore;
    private Double intRate;
    private Boolean loanStatus=true;
    private Boolean shouldRun = true;

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
    public void setLoanStatus(Boolean loanStatus){
        this.loanStatus = loanStatus;
    }
    public Boolean getLoanStatus(){
        return loanStatus;
    }
    public static void main(){

        TextInputDialog dialog = new TextInputDialog("");
        TextInputDialog creditScore = new TextInputDialog("");
        dialog.setTitle("Loan Application");
        dialog.setHeaderText("");
        dialog.setContentText("Enter loan amount:");
        creditScore.setContentText("Enter Credit Score:");
        creditScore.setTitle("Credit Score");
        creditScore.setHeaderText("");
        Optional<String> result = dialog.showAndWait();
        Optional<String> result2 = creditScore.showAndWait();
        Double answer = Double.valueOf(result.get());
        Double answer2 = Double.valueOf(result.get());
        Loan temp = new Loan(answer,answer2);
        temp.setIntRate(answer2);
        if(temp.loanYes(answer,answer2)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Loan Approval Status");
            alert.setHeaderText(null);
            alert.setContentText("Congratulations you have been approved for a loan of $"+ temp.getLnAmount() +" at a rate of "+temp.getIntRate()*100+"%"+" Funds have been deposited in your account");

            alert.showAndWait();
            temp.setLoanStatus(false);

        }else{ Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Loan Approval Status");
            alert.setHeaderText("Denied");
            alert.setContentText("Well this is awkward you have been denied");

            alert.showAndWait();

        }
        System.out.println(temp.getLnAmount());
        System.out.println(temp.getIntRate());
        System.out.println(temp.loanStatus);



    }


}
