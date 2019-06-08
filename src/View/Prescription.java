package View;

import java.sql.*;
import java.util.*;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Statement;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Insets;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import java.util.ArrayList;

import javafx.stage.Stage;

import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.*;
import java.util.Date;
import java.lang.Enum;
import java.text.*;

public class Prescription extends Application {
    String id = null;

    Text nametext;
    Text agetext;
    Text gendertext;
    Text diseasetext;
    Text prescriptiontext;
    Separator separator;
    Button menuButton;
    VBox root;
    Scene scene;
    Stage stage;


    //Right side

    Text rnametext;
    Text ragetext;
    Text rgendertext;
    Text rdiseasetext;
    Text rprescriptiontext;
    VBox rightRoot;

    public Prescription() {
    }

    public Prescription(String id) {
        this.id = id;
    }

    public void start(Stage stage) {
        nametext = new Text("Name                 :");
        agetext = new Text("Age                    :");
        gendertext = new Text("Gender               :");
        diseasetext = new Text("Disease               :");
        prescriptiontext = new Text("Prescription       :");
        separator = new Separator();
        separator.setPadding(new Insets(10, 0, 0, 0));
        menuButton = new Button("Finish");
        menuButton.setStyle("-fx-font: 15 arial; -fx-base: SKYBLUE;");

        menuButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                DoctorPage doctorPage = new DoctorPage();
                doctorPage.start(stage);
            }
        });


        //rightSide

        rnametext = new Text();
        ragetext = new Text();
        rgendertext = new Text();
        rdiseasetext = new Text();
        rprescriptiontext = new Text();

        ResultSet rs1;
        //ResultSet rs2;
        //String tprice;


        try {


            String id;

            if (this.id == null)
                id = PrescriptionScript.patientId;
            else
                id = this.id;

            System.out.println(id);

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
            Statement stmt = con.createStatement();
            String sql4 = "select NAME,AGE,GENDER,DISEASE,PRESCRIPTION from PATIENTS where ID='" + id + "'";

            rs1 = stmt.executeQuery(sql4);


            if (rs1.next()) {


                rnametext.setText(rs1.getString("NAME"));
                ragetext.setText(rs1.getString("AGE"));
                rgendertext.setText(rs1.getString("GENDER"));
                rdiseasetext.setText(rs1.getString("DISEASE"));
                rprescriptiontext.setText(rs1.getString("PRESCRIPTION"));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        VBox root = new VBox(15);
        root.setPadding(new Insets(60));
        root.getChildren().addAll(nametext, agetext, gendertext, diseasetext, prescriptiontext, menuButton);

        //rightRoot
        VBox rightRoot = new VBox(15);
        rightRoot.setPadding(new Insets(60, 10, 10, 10));
        rightRoot.getChildren().addAll(rnametext, ragetext, rgendertext, rdiseasetext, rprescriptiontext);

        HBox h1 = new HBox(5, root, rightRoot);


        Scene scene = new Scene(h1, 650, 350);
        stage.setScene(scene);
        stage.setTitle("PRESCRIPTION");
        stage.show();

    }


}



