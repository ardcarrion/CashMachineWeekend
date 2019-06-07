package rocks.zipcode.atm;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {
    boolean isVisible = false;

    private TextArea areaInfo = new TextArea();
    private Stage loginWindow = new Stage();
    private Stage stage;

    private TextField field = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());

    private Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);



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

        FlowPane flowpane = new FlowPane();

        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
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
        btnCancel.setAlignment(Pos.CENTER);

        Button btnLogIn = new Button("LOG IN");
        btnLogIn.setOnAction(event -> {

            if (loginTF.getText().equals("login")&&passTF.getText().equals("pass")) {
                areaInfo.setText("Welcome to the ATM");
                loginWindow.close();
                stage.show();
            }

            Alert wrongCredentials = new Alert(Alert.AlertType.ERROR);
                wrongCredentials.setTitle("Attention!");
                wrongCredentials.setHeaderText("Login/Password doesn't match");
                wrongCredentials.setContentText("Provided information doesn't match our system, \nplease, try again or press cancel to exit");
                wrongCredentials.showAndWait();
        });

        btnLogIn.setPrefWidth(250);
        btnLogIn.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(10, inforLabel,loginLabel, loginTF, passLabel, passTF,btnLogIn,btnCancel);
        vbox.setPadding(new Insets(20));


        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().addAll(vbox);
        Scene secondScene = new Scene(secondaryLayout, 300, 300);


        return secondScene;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setScene(new Scene(createContent()));
        stage.close();



        // New window (Stage)

        loginWindow.setTitle("Second Stage");
        loginWindow.setScene(createLoginWindow());
        loginWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
