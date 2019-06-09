package rocks.zipcode.atm;

import com.sun.tools.corba.se.idl.constExpr.Or;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import rocks.zipcode.atm.bank.*;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image ;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    private TextArea areaInfo = new TextArea();
    private Stage loginWindow = new Stage();
    private Stage stage;

    private TextField field = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());

    private Parent createContent() throws FileNotFoundException {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);
        vbox.setStyle("-fx-background-color: #104D63");
        vbox.setPadding(new Insets(30));

        ImageView logo = new ImageView("zipcodeLogo.png");
        areaInfo.setStyle("-fx-font-size: 30, Arial");

        TextField depositTF = new TextField("0");
        depositTF.setMaxWidth(300);
        TextField withdrawTF = new TextField("0");
        depositTF.setMaxWidth(300);
        TextField loanTF = new TextField("0");
        depositTF.setMaxWidth(300);


        Button btnDeposit = new Button("Deposit");
        btnDeposit.setPrefWidth(120);
        btnDeposit.setOnAction(e -> {
            int amount = Integer.parseInt(depositTF.getText());
            cashMachine.deposit(amount);
            areaInfo.setText(cashMachine.toString());
            depositTF.setText("0");
        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setPrefWidth(120);
        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(withdrawTF.getText());
            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());
            withdrawTF.setText("0");
        });

        Button btnExit = new Button("Log Out");
        btnExit.setPrefWidth(120);
        btnExit.setOnAction(e -> {
            cashMachine.exit();
            stage.close();
            loginWindow.show();


            areaInfo.setText(cashMachine.toString());
        });
        Button btnLoan = new Button("Loan");
        btnLoan.setOnAction(e -> {

            Loan loanTemp = Loan.LP();
            int tmp2 = loanTemp.getLnAmount().intValue();
            cashMachine.loanUpdate(tmp2);
            cashMachine.deposit(tmp2);

        });

        VBox textFields = new VBox();
        textFields.getChildren().addAll(depositTF,withdrawTF,loanTF);
        textFields.setPadding(new Insets(10,0,0,50));
        textFields.setSpacing(20);

        VBox buttons = new VBox();
        buttons.getChildren().addAll(btnDeposit,btnWithdraw,btnLoan,btnExit);
        buttons.setPadding(new Insets(10));
        buttons.setSpacing(20);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(buttons, textFields);
        hBox.setPadding(new Insets(10,10,10,10));
        vbox.getChildren().addAll(logo,hBox, areaInfo);
        return vbox;
    }

    private Scene createLoginWindow() {

        Label inforLabel = new Label("Provide login information");
        inforLabel.setFont(Font.font("Areal", FontWeight.BOLD, 24));
        Label loginLabel = new Label("login: ");
        inforLabel.setFont(Font.font("Areal", FontWeight.BOLD, 20));
        Label passLabel = new Label("password: ");
        inforLabel.setFont(Font.font("Areal", FontWeight.BOLD, 20));

        TextField loginTF = new TextField();
        loginTF.setMaxWidth(250);
        PasswordField passTF = new PasswordField();
        passTF.setMaxWidth(250);

        Button btnCancel = new Button("CANCEL");
        btnCancel.setOnAction(event -> {
            stage.close();
            loginWindow.close();
        });
        btnCancel.setPrefWidth(250);
        Button btnNewAcc = new Button("create new account");
        btnNewAcc.setPrefWidth(250);
        btnNewAcc.setPadding(new Insets(3));
        btnNewAcc.setStyle("-fx-text-fill: #0000ff");
        btnNewAcc.setOnAction(event -> {
            RegistrationForm newForm = new RegistrationForm();
            try {
                newForm.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        String wrongCredentials = "Provided information doesn't match our system, \nPlease try again or press cancel to quit";
        String errorHeader = "Login/Password doesn't match";

        Button btnLogIn = new Button("LOG IN");
        btnLogIn.setPrefWidth(250);
        btnLogIn.setDefaultButton(true);
        btnLogIn.setOnAction(event -> {
            try {
                Integer loginId = Integer.valueOf(loginTF.getText());
                ActionResult<AccountData> account = cashMachine.getBank().getAccountById(loginId);
                String password = account.getData().getPassword();
                if (password.equals(passTF.getText())) {
                    cashMachine.login(loginId);
                    areaInfo.setText(cashMachine.toString());
                    loginWindow.close();
                    stage.show();
                } else {
                    printAlert(errorHeader, wrongCredentials);
                }

            } catch (NumberFormatException | NullPointerException ex) {
                printAlert("That is not a valid ID", wrongCredentials);
            }

        });

        VBox vboxLogin = new VBox(10, inforLabel,loginLabel, loginTF, passLabel, passTF,btnLogIn,btnCancel,btnNewAcc);
        vboxLogin.setPadding(new Insets(20));
        vboxLogin.setMargin(btnNewAcc, new Insets(30, 0,0,0));

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().addAll(vboxLogin);
        Scene secondScene = new Scene(secondaryLayout, 300, 330);


        return secondScene;
    }

    public void printAlert(String header, String prompt) {
        Alert invalid = new Alert(Alert.AlertType.ERROR);
        invalid.setTitle("Attention!");
        invalid.setHeaderText(header);
        invalid.setContentText(prompt);
        invalid.showAndWait();
    }
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setScene(new Scene(createContent()));
        stage.close();

        //login window
        loginWindow.setTitle("Login Window");
        loginWindow.setScene(createLoginWindow());
        loginWindow.show();

        //create new account
    }

    public static void main(String[] args) {
        launch(args);
    }
}