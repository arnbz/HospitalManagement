/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.UsersModel;
import Service.UsersService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class UserInformation extends Application {
    VBox tableRoot;
    TableView users;
    Scene scene;
    Group root;
    Label usersId;
    TextField usersIdTextField;
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
        //Menu

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


        //UserInformation

        separator = new Separator();
        separator.setPadding(new Insets(10, 0, 0, 0));
        usersId = new Label("ID");
        usersIdTextField = new TextField();
        deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-font: 15 arial; -fx-base: RED;");
        deleteButton.setOnAction(e -> {

                    String id = usersIdTextField.getText();

                    try {
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
                        Statement stmt = con.createStatement();
                        UsersService usersService = new UsersService();
                        ResultSet rs = stmt.executeQuery(usersService.getById(id));
                        if (rs.next() == true) {
                            if ((rs.getString(1).equals(id))) {
                                usersService.delete(id);
                                Alert alert = new Alert(AlertType.WARNING);
                                alert.setTitle("Message");
                                alert.setHeaderText("Deleted");
                                alert.showAndWait().ifPresent(response -> {
                                    UserInformation userInformation = new UserInformation();
                                    userInformation.start(stage);

                                });
                            } else {
                                Alert alert = new Alert(AlertType.WARNING);
                                alert.setTitle("Message");
                                alert.setHeaderText("ID does not Match");
                                alert.showAndWait().ifPresent(response -> {
                                    UserInformation userInformation = new UserInformation();
                                    userInformation.start(stage);
                                });

                            }
                        } else {
                            Alert alert = new Alert(AlertType.WARNING);
                            alert.setTitle("Message");
                            alert.setHeaderText("ID does not Exist");
                            alert.showAndWait().ifPresent(response -> {
                                UserInformation userInformation = new UserInformation();
                                userInformation.start(stage);
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
                UsersInformationUpdate usersInformationUpdate = new UsersInformationUpdate();
                usersInformationUpdate.start(stage);
            }
        });


        users = new TableView();

        TableColumn idColumn = new TableColumn("ID");
        TableColumn nameColumn = new TableColumn("NAME");
        TableColumn positionColumn = new TableColumn("POSITION");
        TableColumn addressColumn = new TableColumn("ADDRESS");
        TableColumn phoneNoColumn = new TableColumn("PHONENO");
        TableColumn dateOfBirthColumn = new TableColumn("DOB");
        TableColumn joinDateColumn = new TableColumn("JOINDATE");
        TableColumn passwordColumn = new TableColumn("PASSWORD");

        idColumn.setCellValueFactory(new PropertyValueFactory<UsersModel, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<UsersModel, String>("name"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<UsersModel, String>("position"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<UsersModel, String>("address"));
        phoneNoColumn.setCellValueFactory(new PropertyValueFactory<UsersModel, String>("phoneNo"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<UsersModel, String>("dateOfBirth"));
        joinDateColumn.setCellValueFactory(new PropertyValueFactory<UsersModel, String>("joinDate"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<UsersModel, String>("password"));

        users.getColumns().addAll(idColumn, nameColumn, positionColumn, addressColumn, phoneNoColumn, dateOfBirthColumn, joinDateColumn, passwordColumn);
        try {
            UsersService usersService = new UsersService();
            users.setItems(FXCollections.observableArrayList(usersService.getAll()));
        } catch (Exception ex) {

        }

        users.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<UsersModel>() {
                    @Override
                    public void changed(ObservableValue observable, UsersModel oldValue, UsersModel newValue) {
                        usersIdTextField.setText(newValue.getId());

                    }
                }
        );

        //menuButton
        HBox h1 = new HBox(20, logoutButton);
        VBox menuRoot = new VBox(10);
        menuRoot.setPadding(new Insets(70, 0, 0, 10));
        menuRoot.getChildren().addAll(text, patientRegistrationButton, patientInformationButton, roomInformationButton, addNewWardButton, userInformationButton, addNewUserButton, h1);


        //table
        HBox h2 = new HBox(10, usersId, usersIdTextField, deleteButton, updateButton);
        tableRoot = new VBox(10, h2, separator, users);
        tableRoot.setPadding(new Insets(10));
        tableRoot.setPrefWidth(700);
        HBox h3 = new HBox(10, menuRoot, tableRoot);
        root = new Group(h3);

        scene = new Scene(root, 880, 550, Color.MEDIUMSLATEBLUE);
        stage.setTitle("User Information");
        stage.setScene(scene);

        stage.show();

    }
}