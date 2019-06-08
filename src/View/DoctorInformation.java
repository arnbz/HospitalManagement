/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.DoctorModel;
import Service.DoctorService;
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

public class DoctorInformation extends Application {

    VBox tableRoot;
    TableView<DoctorModel> patients;
    Scene scene;
    Group root;
    Label patientId;
    TextField patientIdTextField;
    Button deleteButton;
    //Button updateButton;
    Separator separator;


    Text text;
    Button patientRegistrationButton;
    Button patientInformationButton;
    Button prescribeButton;
    Button prescriptionButton;
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
        patientRegistrationButton = new Button("  Add New Patient  ");
        patientRegistrationButton.setStyle("-fx-font: 15 arial; -fx-base: #0097a3;");
        patientRegistrationButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                AddNewPatient addNewPatient = new AddNewPatient();
                addNewPatient.start(stage);
            }
        });


        patientInformationButton = new Button("Patient Information ");
        patientInformationButton.setStyle("-fx-font: 15 arial; -fx-base: #0097a3;");
        patientInformationButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                DoctorInformation doctorInformation = new DoctorInformation();
                doctorInformation.start(stage);
            }
        });


        prescribeButton = new Button("        Prescribe        ");
        prescribeButton.setStyle("-fx-font: 15 arial; -fx-base: #0097a3;");
        prescribeButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Prescribe prescribe = new Prescribe();
                prescribe.start(stage);
            }
        });


        prescriptionButton = new Button("      Prescription      ");
        prescriptionButton.setStyle("-fx-font: 15 arial; -fx-base: #0097a3;");
        prescriptionButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Prescription prescription = new Prescription(patientIdTextField.getText());
                prescription.start(stage);
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


        //DoctorInformation


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
                        DoctorService doctorService = new DoctorService();
                        ResultSet rs = stmt.executeQuery(doctorService.getById(id));
                        if (rs.next()) {
                            if ((rs.getString(1).equals(id))) {
                                doctorService.delete(id);
                                Alert alert = new Alert(AlertType.WARNING);
                                alert.setTitle("Message");
                                alert.setHeaderText("Deleted");
                                alert.showAndWait().ifPresent(response -> {
                                    DoctorInformation doctorInformation = new DoctorInformation();
                                    doctorInformation.start(stage);

                                });
                            } else {
                                Alert alert = new Alert(AlertType.WARNING);
                                alert.setTitle("Message");
                                alert.setHeaderText("ID does not Match");
                                alert.showAndWait().ifPresent(response -> {
                                    DoctorInformation doctorInformation = new DoctorInformation();
                                    doctorInformation.start(stage);
                                });

                            }
                        } else {
                            Alert alert = new Alert(AlertType.WARNING);
                            alert.setTitle("Message");
                            alert.setHeaderText("ID does not Exist");
                            alert.showAndWait().ifPresent(response -> {
                                DoctorInformation doctorInformation = new DoctorInformation();
                                doctorInformation.start(stage);
                            });
                        }
                        ;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
        );
		
		
		/*updateButton = new Button("Update");
		updateButton.setStyle("-fx-font: 15 arial; -fx-base: SKYBLUE;");
		updateButton.setOnAction(new EventHandler<ActionEvent>(){
        public void handle(ActionEvent e){
				DoctorInformation doctorInformation=new DoctorInformation();
			    doctorInformation.start(stage);
			}
		});*/


        //patient Table 
        patients = new <DoctorModel>TableView();


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
        TableColumn prescriptionColumn = new TableColumn("PRESCRIPTION");


        //PatientModel patientmodel=new PatientModel();
        //Mapping columns
        idColumn.setCellValueFactory(new PropertyValueFactory<DoctorModel, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<DoctorModel, String>("name"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<DoctorModel, String>("gender"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<DoctorModel, String>("age"));
        phoneNoColumn.setCellValueFactory(new PropertyValueFactory<DoctorModel, String>("phoneNo"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<DoctorModel, String>("address"));
        diseaseColumn.setCellValueFactory(new PropertyValueFactory<DoctorModel, String>("disease"));
        admissionDateColumn.setCellValueFactory(new PropertyValueFactory<DoctorModel, String>("admissionDate"));
        cabinNoColumn.setCellValueFactory(new PropertyValueFactory<DoctorModel, String>("cabinNo"));
        prescriptionColumn.setCellValueFactory(new PropertyValueFactory<DoctorModel, String>("prescription"));

        //Adding the Columns
        patients.getColumns().addAll(idColumn, nameColumn, genderColumn, ageColumn, phoneNoColumn, addressColumn, diseaseColumn, admissionDateColumn, cabinNoColumn, prescriptionColumn);


        try {
            DoctorService doctorService = new DoctorService();
            // Setting contactList to table as a observable list
            patients.setItems(FXCollections.observableArrayList(doctorService.getAll()));
        } catch (Exception ex) {
            System.out.println(ex);

        }

        patients.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<DoctorModel>() {
                    @Override
                    public void changed(ObservableValue observable, DoctorModel oldValue, DoctorModel newValue) {
                        patientIdTextField.setText(newValue.getId());

                    }
                }
        );

        //menuButton
        HBox h1 = new HBox(20, logoutButton);
        VBox menuRoot = new VBox(10);
        menuRoot.setPadding(new Insets(70, 0, 0, 10));
        menuRoot.getChildren().addAll(text, patientRegistrationButton, patientInformationButton, prescribeButton, prescriptionButton, h1);


        //table
        HBox h2 = new HBox(10, patientId, patientIdTextField, deleteButton);
        tableRoot = new VBox(10, h2, separator, patients);
        tableRoot.setPadding(new Insets(10));
        tableRoot.setPrefWidth(720);
        HBox h3 = new HBox(10, menuRoot, tableRoot);
        root = new Group(h3);

        scene = new Scene(root, 900, 550, Color.MEDIUMTURQUOISE);
        stage.setTitle("Doctor Information");
        stage.setScene(scene);

        stage.show();
    }
}
