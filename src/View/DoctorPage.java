/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class DoctorPage extends Application{
	VBox root;
	Text text;
	Button addPatientButton;
	Button patientInformationButton;
	Button prescribeButton;
	Button prescriptionButton;
	Button logoutButton;
	Scene scene;
	Stage stage;
	
	public void start(Stage stage){
	    DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setColor(Color.color(0.4, 0.4, 0.4));
	    text = new Text();
		text.setEffect(ds);
        text.setX(10.0);
        text.setY(50.0);
        text.setFill(Color.BLACK);
        text.setText("Menu");
        text.setFont(Font.font(null, FontWeight.BOLD, 20));
		addPatientButton = new Button("  Add New Patient  ");
		addPatientButton.setStyle("-fx-font: 15 arial; -fx-base: #0097a3;");
		addPatientButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				AddNewPatient addNewPatient = new AddNewPatient();
				addNewPatient.start(stage);
				}
			});
		
		
		patientInformationButton=new Button("Patient Information ");
		patientInformationButton.setStyle("-fx-font: 15 arial; -fx-base: #0097a3;");
		patientInformationButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				DoctorInformation doctorInformation = new DoctorInformation();
				doctorInformation.start(stage);	
				}
			});
			
		
		prescribeButton=new Button("        Prescribe        ");
		prescribeButton.setStyle("-fx-font: 15 arial; -fx-base: #0097a3;");
		prescribeButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				Prescribe prescribe = new Prescribe();
				prescribe.start(stage);
				}
			});
			
			
	    prescriptionButton=new Button("      Prescription      ");
		prescriptionButton.setStyle("-fx-font: 15 arial; -fx-base: #0097a3;");
		prescriptionButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				PrescriptionScript prescriptionScript = new PrescriptionScript();
				prescriptionScript.start(stage);
				}
			});
			
			
			
		logoutButton=new Button("Logout");
		logoutButton.setStyle("-fx-font: 15 arial; -fx-base: BLUE;");
		logoutButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				UsersLogIn usersLogIn = new UsersLogIn();
				usersLogIn.start(stage);
				}
			});
		
		HBox h1=new HBox(20,logoutButton);
		VBox root = new VBox(10);
		root.setPadding(new Insets(40,0,0,110));
		root.getChildren().addAll(text, addPatientButton, patientInformationButton,prescribeButton,prescriptionButton,h1 );
		Scene scene = new Scene(root, 380,420);
		stage.setScene (scene);
        stage.setTitle ("Doctor Page");
        stage.show();
	}
}




