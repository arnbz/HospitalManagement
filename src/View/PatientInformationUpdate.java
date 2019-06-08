/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Service.PatientService;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Statement;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class PatientInformationUpdate extends Application{
	VBox root;
	Label patientIdLabel;
	Label nameLabel;
	Label genderLabel;
	Label ageLabel;
	Label phoneNoLabel;
	Label addressLabel;
	Label diseaseLabel;
    Label admissionDateLabel;
	Label cabinNoLabel;
	TextField patientIdTextField;
	TextField nameTextField;
	TextField genderTextField;
	TextField ageTextField;
	TextField phoneNoTextField;
	TextField addressTextField;
	TextField diseaseTextField;
	TextField admissionDateTextField;
	TextField cabinNoTextField;
	Separator separator;
    Button updateButton;
	Button menuButton;
	Scene scene;
	Stage stage;
	
public void start(Stage stage){
	patientIdLabel = new Label("Patient Id              ");
	nameLabel = new Label ("Name                   ");
	genderLabel = new Label("Gender                 ");
	ageLabel=new Label    ("Age                      ");
	phoneNoLabel=new Label("Phone No            ");
	addressLabel=new Label("Address               ");
	diseaseLabel=new Label("Disease                ");
	admissionDateLabel=new Label("Admission Date   ");
	cabinNoLabel=new Label("Cabin No             ");
	patientIdTextField=new TextField();
	nameTextField=new TextField();
	genderTextField=new TextField();
	ageTextField=new TextField();
	phoneNoTextField=new TextField();
	addressTextField=new TextField();
	diseaseTextField=new  TextField();
	admissionDateTextField=new TextField();
	cabinNoTextField=new  TextField();
	separator = new Separator();
	separator.setPadding(new Insets(10, 0, 0, 0));
	updateButton = new Button("Update");
	updateButton.setStyle("-fx-font: 15 arial; -fx-base: BLUE;");
	updateButton.setOnAction(e->{
		
try{
	    String id= patientIdTextField.getText();
		String name = nameTextField.getText();
		String gender = genderTextField.getText();
		String age = ageTextField.getText();
		String phoneNo = phoneNoTextField.getText();
		String address = addressTextField.getText();
		String disease = diseaseTextField.getText();
		String admissionDate = admissionDateTextField.getText();
		String cabinNo = cabinNoTextField.getText();

	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
			    Statement stmt=con.createStatement();
			    PatientService patientService = new PatientService();
				ResultSet rs = stmt.executeQuery(patientService.getById(id));
			    if(rs.next()==true){
					if((rs.getString(1).equals(id))){
						patientService.update(id,name,gender,age,phoneNo,address,disease,admissionDate,cabinNo);
						Alert alert=new Alert(AlertType.WARNING);
						alert.setTitle("Message");
						alert.setHeaderText("Successfully Updated");
						alert.showAndWait().ifPresent(response->{
						PatientInformation patientInformation=new PatientInformation();
						patientInformation.start(stage);
						
					});
			
					}
					else{
						Alert alert=new Alert(AlertType.WARNING);
						alert.setTitle("Message");
						alert.setHeaderText("ID does not Match");
						alert.showAndWait().ifPresent(response->{
							PatientInformation patientInformation=new PatientInformation();
							patientInformation.start(stage);
						});
							
				    }
			}
			else{
						Alert alert=new Alert(AlertType.WARNING);
						alert.setTitle("Message");
						alert.setHeaderText("ID does not Exist");
						alert.showAndWait().ifPresent(response->{
							PatientInformation patientInformation=new PatientInformation();
							patientInformation.start(stage);
						});
						};
		}
		
		catch(Exception ex){
				ex.printStackTrace();
			}
		});
			
		menuButton=new Button("Menu");
		menuButton.setStyle("-fx-font: 15 arial; -fx-base: SKYBLUE;");
	    menuButton.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent e){
			AdminPage adminPage = new AdminPage();
			adminPage.start(stage);
			}
		});	

	
	
	
	HBox h1=new HBox(10,patientIdLabel,patientIdTextField);
	HBox h2=new HBox(10,nameLabel,nameTextField);
	HBox h3=new HBox(10,genderLabel,genderTextField);
	HBox h4=new HBox(10,ageLabel,ageTextField);
	HBox h5=new HBox(10,phoneNoLabel,phoneNoTextField);
	HBox h6=new HBox(10,addressLabel,addressTextField);
	HBox h7=new HBox(10,diseaseLabel,diseaseTextField);
	HBox h8=new HBox(10,admissionDateLabel,admissionDateTextField);
	HBox h9=new HBox(10,cabinNoLabel,cabinNoTextField);
	HBox h10=new HBox(10,updateButton,menuButton);
	
	VBox root = new VBox(10);
	root.setPadding(new Insets(10));
	root.getChildren().addAll(h1,h2,h3,h4,h5,h6,h7,h8,h9,separator,h10);
	Scene scene = new Scene(root, 350,450);
	stage.setScene (scene);
    stage.setTitle ("Update Patient");
     stage.show();
	}
}











