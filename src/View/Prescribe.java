/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Service.DoctorService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Separator;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class Prescribe extends Application{
	Text prescriptionText;
	TextField prescriptionTextField,idTextField;
	Button doneButton,backButton;
	Label idLabel,descriptionLabel;
	Separator separator;
	VBox root;
	DoctorService doctorService;
    public void start(Stage stage){
		doctorService=new DoctorService();
		prescriptionText=new Text("Prescription");
		idLabel=new Label("Enter Patient Id :");
		descriptionLabel=new Label("Write Prescription");
		prescriptionTextField=new TextField();
		idTextField=new TextField();
		backButton=new Button("Menu");
		backButton.setStyle("-fx-font: 15 arial; -fx-base: SKYBLUE;");
		doneButton=new Button("Prescribe");
		doneButton.setStyle("-fx-font: 15 arial; -fx-base: BLUE;");
		doneButton.setOnAction(e->{
			String ID=idTextField.getText();
			String Description=prescriptionTextField.getText();
			int i=doctorService.updateprescription(ID,Description);
			if(i==1){
				Alert alert=new Alert(AlertType.WARNING);
						alert.setTitle("Message");
						alert.setHeaderText("Successfully Updated");
						alert.showAndWait().ifPresent(response->{
						DoctorInformation doctorInformation=new DoctorInformation();
						doctorInformation.start(stage);
			});
			}	
			
		});
		backButton.setOnAction(e->{
			DoctorPage doctorPage = new DoctorPage();
			doctorPage.start(stage);
		});
		
		separator = new Separator();
	    separator.setPadding(new Insets(10, 0, 0, 0));
		
		HBox h1 = new HBox(10,doneButton,backButton);
		
	root = new VBox(10);
	root.setPadding(new Insets(30,10,10,10));
	root.getChildren().addAll(prescriptionText,idLabel,idTextField, descriptionLabel,prescriptionTextField,separator,h1);
	Scene scene = new Scene(root, 350,400);
	stage.setScene (scene);
    stage.setTitle ("Prescribe");
    stage.show();
	
   }
}
