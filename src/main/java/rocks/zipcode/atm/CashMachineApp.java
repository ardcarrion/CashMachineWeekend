package rocks.zipcode.atm;

import javafx.geometry.Insets;
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
import rocks.zipcode.atm.bank.Account;
import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;
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
        vbox.setStyle("-fx-background-color: #B3F239");
        vbox.setPadding(new Insets(30));
        //vbox.setStyle("-fx-background-image: moneylove.jpg");



//        // create a input stream
//        FileInputStream input = new FileInputStream("/Users/alexilinykh/projects/CashMachineWeekend/src/main/resources/zipcodeLogo.png");
//        // create a image
//        Image image = new Image(input);



        Button btnSubmit = new Button("Set Account ID");
        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            cashMachine.login(id);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnDeposit = new Button("Deposit");
        btnDeposit.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            cashMachine.deposit(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> {
            cashMachine.exit();
            stage.close();
            loginWindow.show();

            areaInfo.setText(cashMachine.toString());
        });
//        Button btnLoan = new Button("Loan");
//        btnSubmit.setOnAction(e -> {
//            int id = Integer.parseInt(field.getText());
//            cashMachine.login(id);
//
//            areaInfo.setText(cashMachine.toString());
//        });

        FlowPane flowpane = new FlowPane();

        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
        //flowpane.getChildren().add(btnLoan);
       // flowpane.getChildren().add(new ImageView(image));
        vbox.getChildren().addAll(field, flowpane, areaInfo);
        return vbox;
    }

    private Scene createLoginWindow() {
        //Label secondLabel = new Label("I'm a Label on new Window");

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


        Button btnLogIn = new Button("LOG IN");
        btnLogIn.setDefaultButton(true);
        btnLogIn.setOnAction(event -> {

            //int id = Integer.parseInt(loginTF.getText());
            //cashMachine.login(id);
            Bank bank = new Bank();
            Integer loginID = Integer.valueOf(loginTF.getText());
            ActionResult<AccountData> account = bank.getAccountById(loginID);
            if (!account.equals(null) && account.getPassword().passTF.getText()) {
//            if (loginTF.getText().equals("")&&passTF.getText().equals("")) {
                areaInfo.setText("Welcome to the ATM");

                loginWindow.close();
                stage.show();
            }
            else {

                Alert wrongCredentials = new Alert(Alert.AlertType.ERROR);
                wrongCredentials.setTitle("Attention!");
                wrongCredentials.setHeaderText("Login/Password doesn't match");
                wrongCredentials.setContentText("Provided information doesn't match our system, \nplease, try again or press cancel to exit");
                wrongCredentials.showAndWait();
            }
        });

        btnLogIn.setPrefWidth(250);
        VBox vbox = new VBox(10, inforLabel,loginLabel, loginTF, passLabel, passTF,btnLogIn,btnCancel,btnNewAcc);
        vbox.setPadding(new Insets(20));


        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().addAll(vbox);
        Scene secondScene = new Scene(secondaryLayout, 300, 330);


        return secondScene;
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
//Add option when customer tries to withdraw more then they have offer a loan.
// Buy stocks use random to set stock prices
//implment tooltips