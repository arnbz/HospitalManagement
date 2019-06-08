package View;

import Service.PatientService;

import java.sql.*;
import java.util.*;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Statement;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import java.util.ArrayList;

import javafx.stage.Stage;

import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CashScript extends Application {

    VBox root;
    Label patientIdLabel;
    Label currentDateLabel;
    TextField patientIdTextField;
    TextField currentDateTextField;
    Button confirmButton;
    Scene scene;
    Stage stage;
    public static String currentDate;
    public static String patientId;

    public void start(Stage stage) {
        patientIdLabel = new Label("Patient ID");
        currentDateLabel = new Label("Current Date");
        patientIdTextField = new TextField();
        currentDateTextField = new TextField();


        confirmButton = new Button("Check Out");
        confirmButton.setStyle("-fx-font: 15 arial; -fx-base: SKYBLUE;");
        confirmButton.setOnAction(e -> {
            try {
                patientId = patientIdTextField.getText();
                currentDate = currentDateTextField.getText();
                System.out.println(patientId);


                PatientService patientService = new PatientService();

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(patientService.getById(patientId));
                if (rs.next()) {
                    if ((rs.getString(1).equals(patientId))) {
                        PatientCheckOut patientCheckOut = new PatientCheckOut();
                        patientCheckOut.start(stage);
                    } else {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("View Information");
                        alert.setHeaderText("Invalid Patient");
                        alert.showAndWait().ifPresent(response -> {
                            CashScript cashScript = new CashScript();
                            cashScript.start(stage);
                        });

                    }
                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("View Information");
                    alert.setHeaderText("Invalid Patient");
                    alert.showAndWait().ifPresent(response -> {
                        CashScript cashScript = new CashScript();
                        cashScript.start(stage);
                    });

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        root = new VBox(10);
        root.setPadding(new Insets(30, 10, 10, 10));
        root.getChildren().addAll(patientIdLabel, patientIdTextField, currentDateLabel, currentDateTextField, confirmButton);
        Scene scene = new Scene(root, 350, 400);
        stage.setScene(scene);
        stage.setTitle("Cashier Script");
        stage.show();
    }

}	
	