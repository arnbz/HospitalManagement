/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.UsersModel;
import Service.UsersService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Separator;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class SignUp extends Application{
	VBox root;
	Label userIdLabel;
	Label nameLabel;
	Label positionLabel;
	Label addressLabel;
	Label phoneNoLabel;
	Label dateOfBirthLabel;
	Label joinDateLabel;
	Label passwordLabel;
	PasswordField passwordField;
	TextField userIdTextField;
	TextField nameTextField;
	TextField positionTextField;
	TextField addressTextField;
	TextField phoneNoTextField;
	TextField dateOfBirthTextField;
	TextField joinDateTextField;
	Separator separator;
    Button addButton;
	Scene scene;
	Stage stage;
	
	public void start(Stage stage){
	userIdLabel = new Label("User Id     ");
	nameLabel = new Label("Name       ");
	positionLabel = new Label("Position   ");
	addressLabel=new Label("Address   ");
	phoneNoLabel=new Label("Phone No");
	dateOfBirthLabel=new Label("DOB         ");
	joinDateLabel=new Label("Join Date ");
	passwordLabel=new Label("Password ");
	passwordField=new PasswordField();
	userIdTextField=new TextField();
	nameTextField=new TextField();
	positionTextField=new TextField();
	addressTextField=new TextField();
	phoneNoTextField=new TextField();
	dateOfBirthTextField=new TextField();
	joinDateTextField=new  TextField();
	separator = new Separator();
	separator.setPadding(new Insets(10, 0, 0, 0));
	addButton=new Button("Confirm");
	addButton.setStyle("-fx-font: 15 arial; -fx-base: SKYBLUE;");
	addButton.setOnAction(e->{
		
		String id = userIdTextField.getText();
		String name = nameTextField.getText();
		String position = positionTextField.getText();
		String address = addressTextField.getText();
		String phoneNo = phoneNoTextField.getText();
		String dateOfBirth = dateOfBirthTextField.getText();
		String joinDate = joinDateTextField.getText();
		String password = passwordField.getText();
		
		UsersModel usersModel = new UsersModel(id, name, position, address, phoneNo, dateOfBirth, joinDate, password);
		
		UsersService usersService = new UsersService();
		usersService.add(usersModel);
		UsersLogIn usersLogIn = new UsersLogIn();
		usersLogIn.start(stage);
	});

	
	HBox h1=new HBox(20,userIdLabel,userIdTextField);
	HBox h2=new HBox(20,nameLabel,nameTextField);
	HBox h3=new HBox(20,positionLabel,positionTextField);
	HBox h4=new HBox(20,addressLabel,addressTextField);
	HBox h5=new HBox(20,phoneNoLabel,phoneNoTextField);
	HBox h6=new HBox(20,dateOfBirthLabel,dateOfBirthTextField);
	HBox h7=new HBox(20,joinDateLabel,joinDateTextField);
	HBox h8=new HBox(20,passwordLabel,passwordField);
	HBox h9=new HBox(20,addButton);
	
	VBox root = new VBox(10);
	root.setPadding(new Insets(30,10,10,10));
	root.getChildren().addAll(h1,h2,h3,h4,h5,h6,h7,h8,separator,h9);
	Scene scene = new Scene(root, 350,420);
	stage.setScene (scene);
    stage.setTitle ("Sign Up");
     stage.show();
	}
}











