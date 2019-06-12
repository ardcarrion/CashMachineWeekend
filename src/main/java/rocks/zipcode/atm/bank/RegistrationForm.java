package rocks.zipcode.atm.bank;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import rocks.zipcode.atm.CashMachineApp;

import java.util.Random;

public class RegistrationForm extends CashMachineApp {
    private Bank bank;
    private Stage stage;

    public RegistrationForm(Bank bank, Stage newForm) {
        this.bank = bank;
        stage = newForm;
    }


    public Scene run() throws Exception {

        GridPane gridPane = createRegistrationFormPane();
        addUIControls(gridPane);
        return new Scene(gridPane, 540, 400);

    }


    private GridPane createRegistrationFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();


        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {


        Image image = new Image("zipcodeLogo.png");
        Node imageNode = new ImageView(image);
        gridPane.getChildren().add(imageNode);
        GridPane.setHalignment(imageNode, HPos.LEFT);
        GridPane.setMargin(imageNode, new Insets(0, 0, 0, 0));


        // Add Name Label
        Label nameLabel = new Label("Full Name : ");
        gridPane.add(nameLabel, 0, 1);

        // Add Name Text Field
        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        gridPane.add(nameField, 1, 1);


        // Add Email Label
        Label emailLabel = new Label("Email Address : ");
        gridPane.add(emailLabel, 0, 2);

        // Add Email Text Field
        TextField emailField = new TextField();
        emailField.setPrefHeight(40);
        gridPane.add(emailField, 1, 2);

        // Add Password Label
        Label passwordLabel = new Label("Password : ");
        gridPane.add(passwordLabel, 0, 3);

        // Add Password Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 3);

        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 4, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));

        submitButton.setOnAction(event -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            CreateAccount newAccount = new CreateAccount(bank, name, email, password);
            if (!newAccount.isValidInput()) {
                CashMachineApp.printAlert("Field error", "Please fill out the fields with valid information");
            } else {

                nameField.clear();
                emailField.clear();
                passwordField.clear();
                stage.close();
                String prompt = newAccount.makeAccount();
                Alert accountName = new Alert(Alert.AlertType.INFORMATION);
                accountName.setTitle("Account Created");
                accountName.setHeaderText("Account Creation Successful");
                accountName.setContentText(prompt);
                accountName.showAndWait();

            }
        });

    }


}

