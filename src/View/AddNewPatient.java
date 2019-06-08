/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.DoctorModel;
import Service.DoctorService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class AddNewPatient extends Application{
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
	Label prescriptionLabel;
	TextField patientIdTextField;
	TextField nameTextField;
	TextField genderTextField;
	TextField ageTextField;
	TextField phoneNoTextField;
	TextField addressTextField;
	TextField diseaseTextField;
	TextField admissionDateTextField;
	TextField cabinNoTextField;
	TextField prescriptionTextField;
	Separator separator;
    Button addButton;
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
	prescriptionLabel=new Label("Prescription         ");
	patientIdTextField=new TextField();
	nameTextField=new TextField();
	genderTextField=new TextField();
	ageTextField=new TextField();
	phoneNoTextField=new TextField();
	addressTextField=new TextField();
	diseaseTextField=new  TextField();
	prescriptionTextField=new TextField();
	admissionDateTextField=new TextField();
	cabinNoTextField=new  TextField();
	separator = new Separator();
	separator.setPadding(new Insets(10, 0, 0, 0));
	addButton=new Button("Add");
	addButton.setStyle("-fx-font: 15 arial; -fx-base: BLUE;");
	
	addButton.setOnAction(e->{
		String id = patientIdTextField.getText();
		String name = nameTextField.getText();
		String gender = genderTextField.getText();
		String age = ageTextField.getText();
		String phoneNo = phoneNoTextField.getText();
		String address = addressTextField.getText();
		String disease = diseaseTextField.getText();
		String admissionDate = admissionDateTextField.getText();
		String cabinNo = cabinNoTextField.getText();
		String prescription=prescriptionTextField.getText();
		
		DoctorModel doctorModel = new DoctorModel(id, name, gender, age, phoneNo, address, disease, admissionDate, cabinNo, prescription);
		DoctorService doctorService = new DoctorService();
		doctorService.add(doctorModel);
		DoctorInformation doctorInformation = new DoctorInformation();
		doctorInformation.start(stage);
	});
	
	menuButton=new Button("Menu");
	menuButton.setStyle("-fx-font: 15 arial; -fx-base: SKYBLUE;");
	menuButton.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent e){
			DoctorPage doctorPage = new DoctorPage();
			doctorPage.start(stage);
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
	HBox h10=new HBox(10,prescriptionLabel,prescriptionTextField);
	HBox h11=new HBox(10,addButton,menuButton);
	
	VBox root = new VBox(10);
	root.setPadding(new Insets(10));
	root.getChildren().addAll(h1,h2,h3,h4,h5,h6,h7,h8,h9,h10,separator,h11);
	Scene scene = new Scene(root, 350,460);
	stage.setScene (scene);
    stage.setTitle ("Add New Patient");
     stage.show();
	}
}











