package View;

import Service.UsersService;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Insets;

import javafx.stage.Stage;

public class UsersLogIn extends Application {
    Text text;
    Label idLabel;
    Label passwordLabel;
    Label positionLabel;
    PasswordField passwordField;
    TextField idTextField;
    ChoiceBox<String> userTypeChoiceBox;
    Button loginButton;
    Button signUpButton;
    VBox root;
    Scene scene;
    Stage stage;


    public void start(Stage stage) {
        //{@code DropShadow} has been used for 'header' text
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setColor(Color.color(0.4, 0.4, 0.4));
        text = new Text();
        text.setEffect(ds);
        text.setX(10.0);
        text.setY(50.0);
        text.setFill(Color.BLACK);
        text.setText("Welcome to Hospital Management System");
        text.setFont(Font.font(null, FontWeight.BOLD, 32));

        idLabel = new Label("ID");
        idTextField = new TextField();

        passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();

        positionLabel = new Label("User Type");
        userTypeChoiceBox = new ChoiceBox<>();

        //add items to userTypeChoiceBox
        userTypeChoiceBox.getItems().addAll("ADMIN", "DOCTOR", "CASHIER");

        loginButton = new Button("Login");
        loginButton.setStyle("-fx-font: 15 arial; -fx-base: BLUE;");

        loginButton.setOnAction(e -> {
            UsersService usersService = new UsersService();
            String id = idTextField.getText();
            String password = passwordField.getText();
            String userType = userTypeChoiceBox.getValue();

            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(usersService.getById(id));

                if (rs.next()) {
                    if ((rs.getString(1).equals(id)) && (rs.getString(2).equals(password)) && (rs.getString(3).equals(userType))) {
                        if (userType.equals("ADMIN")) {
                            AdminPage adminPage = new AdminPage();
                            adminPage.start(stage);
                        } else if (userType.equals("CASHIER")) {
                            CashScript cashScript = new CashScript();
                            cashScript.start(stage);
                        } else if (userType.equals("DOCTOR")) {
                            DoctorPage doctorPage = new DoctorPage();
                            doctorPage.start(stage);
                        }

                    } else {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("View Information");
                        alert.setHeaderText("Invalid Login");
                        alert.showAndWait().ifPresent(response -> {
                            UsersLogIn usersLogIn = new UsersLogIn();
                            usersLogIn.start(stage);
                        });

                    }
                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("View Information");
                    alert.setHeaderText("Invalid Login");
                    alert.showAndWait().ifPresent(response -> {
                        UsersLogIn usersLogIn = new UsersLogIn();
                        usersLogIn.start(stage);
                    });

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        signUpButton = new Button("Signup");
        signUpButton.setStyle("-fx-font: 15 arial; -fx-base: BLUE;");
        signUpButton.setOnAction(e -> {
            SignUp signUp = new SignUp();
            signUp.start(stage);
        });

        HBox h1 = new HBox(15, loginButton, signUpButton);
        VBox root = new VBox(15);
        root.setPadding(new Insets(60));
        root.getChildren().addAll(text, idLabel, idTextField, passwordLabel, passwordField, positionLabel, userTypeChoiceBox, h1);

        Scene scene = new Scene(root, 750, 450);
        stage.setScene(scene);
        stage.setTitle("Log In");
        stage.show();

    }


}



