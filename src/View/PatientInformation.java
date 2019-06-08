/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.PatientModel;
import Service.PatientService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PatientInformation extends Application {

    VBox tableRoot;
    TableView<PatientModel> patients;
    Scene scene;
    Group root;
    Label patientId;
    TextField patientIdTextField;
    Button deleteButton;
    Button updateButton;
    Separator separator;


    Text text;
    Button patientRegistrationButton;
    Button patientInformationButton;
    Button roomInformationButton;
    Button addNewWardButton;
    Button userInformationButton;
    Button addNewUserButton;
    Button logoutButton;

    public void start(Stage stage) {

        //menu

        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setColor(Color.color(0.4, 0.4, 0.4));
        text = new Text();
        text.setEffect(ds);
        text.setCache(true);
        text.setX(10.0);
        text.setY(50.0);
        text.setFill(Color.BLACK);
        text.setText("Menu");
        text.setFont(Font.font(null, FontWeight.BOLD, 20));
        patientRegistrationButton = new Button("Patient Registration");
        patientRegistrationButton.setStyle("-fx-font: 15 arial; -fx-base: #0097a3;");
        patientRegistrationButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                PatientRegistration patientRegistration = new PatientRegistration();
                patientRegistration.start(stage);
            }
        });


        patientInformationButton = new Button("Patient Information ");
        patientInformationButton.setStyle("-fx-font: 15 arial; -fx-base: #0097a3;");
        patientInformationButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                PatientInformation patientInformation = new PatientInformation();
                patientInformation.start(stage);
            }
        });


        roomInformationButton = new Button("Room Information   ");
        roomInformationButton.setStyle("-fx-font: 15 arial; -fx-base: #0097a3;");
        roomInformationButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                RoomInformation roomInformation = new RoomInformation();
                roomInformation.start(stage);
            }
        });


        addNewWardButton = new Button("Add New Ward       ");
        addNewWardButton.setStyle("-fx-font: 15 arial; -fx-base: #0097a3;");
        addNewWardButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                AddNewWard addNewWard = new AddNewWard();
                addNewWard.start(stage);
            }
        });


        userInformationButton = new Button("User Information     ");
        userInformationButton.setStyle("-fx-font: 15 arial; -fx-base: #0097a3;");
        userInformationButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                UserInformation userInformation = new UserInformation();
                userInformation.start(stage);
            }
        });


        addNewUserButton = new Button("Add New User        ");
        addNewUserButton.setStyle("-fx-font: 15 arial; -fx-base: #0097a3;");
        addNewUserButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                AddNewUser addNewUser = new AddNewUser();
                addNewUser.start(stage);
            }
        });


        logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-font: 15 arial; -fx-base: BLUE;");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                UsersLogIn usersLogIn = new UsersLogIn();
                usersLogIn.start(stage);
            }
        });


        //PatientInformation


        separator = new Separator();
        separator.setPadding(new Insets(10, 0, 0, 0));
        patientId = new Label("ID");
        patientIdTextField = new TextField();
        deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-font: 15 arial; -fx-base: RED;");
        deleteButton.setOnAction(e -> {

                    String id = patientIdTextField.getText();

                    try {
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
                        Statement stmt = con.createStatement();
                        PatientService patientService = new PatientService();
                        ResultSet rs = stmt.executeQuery(patientService.getById(id));
                        if (rs.next()) {
                            if ((rs.getString(1).equals(id))) {
                                patientService.delete(id);
                                Alert alert = new Alert(AlertType.WARNING);
                                alert.setTitle("Message");
                                alert.setHeaderText("Deleted");
                                alert.showAndWait().ifPresent(response -> {
                                    PatientInformation patientInformation = new PatientInformation();
                                    patientInformation.start(stage);

                                });
                            } else {
                                Alert alert = new Alert(AlertType.WARNING);
                                alert.setTitle("Message");
                                alert.setHeaderText("ID does not Match");
                                alert.showAndWait().ifPresent(response -> {
                                    PatientInformation patientInformation = new PatientInformation();
                                    patientInformation.start(stage);
                                });

                            }
                        } else {
                            Alert alert = new Alert(AlertType.WARNING);
                            alert.setTitle("Message");
                            alert.setHeaderText("ID does not Exist");
                            alert.showAndWait().ifPresent(response -> {
                                PatientInformation patientInformation = new PatientInformation();
                                patientInformation.start(stage);
                            });
                        }
                        ;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
        );


        updateButton = new Button("Update");
        updateButton.setStyle("-fx-font: 15 arial; -fx-base: SKYBLUE;");
        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                PatientInformationUpdate patientInformationUpdate = new PatientInformationUpdate();
                patientInformationUpdate.start(stage);
            }
        });


        //patient Table 
        patients = new <PatientModel>TableView();


        //creating Column
        TableColumn idColumn = new TableColumn("ID");
        TableColumn nameColumn = new TableColumn("NAME");
        TableColumn genderColumn = new TableColumn("GENDER");
        TableColumn ageColumn = new TableColumn("AGE");
        TableColumn phoneNoColumn = new TableColumn("PHONE");
        TableColumn addressColumn = new TableColumn("ADDRESS");
        TableColumn diseaseColumn = new TableColumn("DISEASE");
        TableColumn admissionDateColumn = new TableColumn("ADMISSIONDATE");
        TableColumn cabinNoColumn = new TableColumn("CABINNO");
        //TableColumn prescriptionColumn = new TableColumn("PRESCRIPTION");

        //PatientModel patientmodel=new PatientModel();
        //Mapping columns
        idColumn.setCellValueFactory(new PropertyValueFactory<PatientModel, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<PatientModel, String>("name"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<PatientModel, String>("gender"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<PatientModel, String>("age"));
        phoneNoColumn.setCellValueFactory(new PropertyValueFactory<PatientModel, String>("phoneNo"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<PatientModel, String>("address"));
        diseaseColumn.setCellValueFactory(new PropertyValueFactory<PatientModel, String>("disease"));
        admissionDateColumn.setCellValueFactory(new PropertyValueFactory<PatientModel, String>("admissionDate"));
        cabinNoColumn.setCellValueFactory(new PropertyValueFactory<PatientModel, String>("cabinNo"));
        //prescriptionColumn.setCellValueFactory(new PropertyValueFactory<PatientModel,String>("prescription"));

        //Adding the Columns
        patients.getColumns().addAll(idColumn, nameColumn, genderColumn, ageColumn, phoneNoColumn, addressColumn, diseaseColumn, admissionDateColumn, cabinNoColumn);


        try {
            PatientService patientService = new PatientService();
            // Setting contactList to table as a observable list
            patients.setItems(FXCollections.observableArrayList(patientService.getAll()));
        } catch (Exception ex) {
            System.out.println(ex);

        }

        patients.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<PatientModel>() {
                    @Override
                    public void changed(ObservableValue observable, PatientModel oldValue, PatientModel newValue) {
                        patientIdTextField.setText(newValue.getId());

                    }
                }
        );

        //menuButton
        HBox h1 = new HBox(20, logoutButton);
        VBox menuRoot = new VBox(10);
        menuRoot.setPadding(new Insets(70, 0, 0, 10));
        menuRoot.getChildren().addAll(text, patientRegistrationButton, patientInformationButton, roomInformationButton, addNewWardButton, userInformationButton, addNewUserButton, h1);


        //table
        HBox h2 = new HBox(10, patientId, patientIdTextField, deleteButton, updateButton);
        tableRoot = new VBox(10, h2, separator, patients);
        tableRoot.setPadding(new Insets(10));
        tableRoot.setPrefWidth(720);
        HBox h3 = new HBox(10, menuRoot, tableRoot);
        root = new Group(h3);

        scene = new Scene(root, 900, 550, Color.MEDIUMTURQUOISE);
        stage.setTitle("Patient Information");
        stage.setScene(scene);

        stage.show();
    }
}
