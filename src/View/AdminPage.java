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

public class AdminPage extends Application{
	VBox root;
	Text text;
	Button patientRegistrationButton;
	Button patientInformationButton;
	
	Button roomInformationButton;
	Button addNewWardButton;
	Button userInformationButton;
	Button addNewUserButton;
	Button logoutButton;
	Scene scene;
	Stage stage;
	
	public void start(Stage stage){
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
		patientRegistrationButton=new Button("Patient Registration");
		patientRegistrationButton.setStyle("-fx-font: 15 arial; -fx-base: #0097a3;");
		patientRegistrationButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				PatientRegistration patientRegistration = new PatientRegistration();
				patientRegistration.start(stage);
				}
			});
		
		
		patientInformationButton=new Button("Patient Information ");
		patientInformationButton.setStyle("-fx-font: 15 arial; -fx-base: #0097a3;");
		patientInformationButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				PatientInformation patientInformation = new PatientInformation();
				patientInformation.start(stage);	
				}
			});
			
			
			
			
		roomInformationButton=new Button("Room Information   ");
		roomInformationButton.setStyle("-fx-font: 15 arial; -fx-base: #0097a3;");
		roomInformationButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				RoomInformation roomInformation = new RoomInformation();
				roomInformation.start(stage);
				}
			});
			
			
		addNewWardButton=new Button("Add New Word       ");
		addNewWardButton.setStyle("-fx-font: 15 arial; -fx-base: #0097a3;");
		addNewWardButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				AddNewWard addNewWard = new AddNewWard();
				addNewWard.start(stage);
				}
			});
			
		
			
			
		userInformationButton=new Button("User Information     ");
		userInformationButton.setStyle("-fx-font: 15 arial; -fx-base: #0097a3;");
		userInformationButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				UserInformation userInformation = new UserInformation();
				userInformation.start(stage);
				}
			});
			
			
			
		addNewUserButton=new Button("Add New User        ");
		addNewUserButton.setStyle("-fx-font: 15 arial; -fx-base: #0097a3;");
		addNewUserButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				AddNewUser addNewUser = new AddNewUser();
				addNewUser.start(stage);
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
		root.getChildren().addAll(text, patientRegistrationButton, patientInformationButton, roomInformationButton, addNewWardButton, userInformationButton, addNewUserButton,h1 );
		Scene scene = new Scene(root, 380,420);
		stage.setScene (scene);
        stage.setTitle ("Admin Page");
        stage.show();
	}
}




