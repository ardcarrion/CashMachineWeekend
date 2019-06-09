package rocks.zipcode.atm.bank;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class Loan {

    public static void main(){
        TextInputDialog loanAmt = new TextInputDialog("");
        TextInputDialog creditScore = new TextInputDialog("");
        loanAmt.setTitle("Loan Application");
        loanAmt.setHeaderText("All information must be filled in!");
        loanAmt.setContentText("Please enter loan amount:");


        Optional<String> loanAmtTemp = loanAmt.showAndWait();
        Optional<String> creditScoreTemp = creditScore.showAndWait();



    }


}
