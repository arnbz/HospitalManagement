/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Service.UsersService;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Statement;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Separator;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class UsersInformationUpdate extends Application{
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
    Button updateButton;
	Button menuButton;
	Button logoutButton;
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
	updateButton=new Button("Update");
	updateButton.setStyle("-fx-font: 15 arial; -fx-base: SKYBLUE;");
	updateButton.setOnAction(e->{
				
try{
		String id = userIdTextField.getText();
		String name = nameTextField.getText();
		String position = positionTextField.getText();
		String address = addressTextField.getText();
		String phoneNo = phoneNoTextField.getText();
		String dateOfBirth = dateOfBirthTextField.getText();
		String joinDate = joinDateTextField.getText();
		String password = passwordField.getText();

	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
			    Statement stmt=con.createStatement();
			    UsersService usersService = new UsersService();
				ResultSet rs = stmt.executeQuery(usersService.getById(id));
			    if(rs.next()==true){
					if((rs.getString(1).equals(id))){
						usersService.update(id,name,position,address,phoneNo,dateOfBirth,joinDate,password);
						Alert alert=new Alert(AlertType.WARNING);
						alert.setTitle("Message");
						alert.setHeaderText("Successfully Updated");
						alert.showAndWait().ifPresent(response->{
						UserInformation userInformation=new UserInformation();
						userInformation.start(stage);
						
					});
			
					}
					else{
						Alert alert=new Alert(AlertType.WARNING);
						alert.setTitle("Message");
						alert.setHeaderText("ID does not Match");
						alert.showAndWait().ifPresent(response->{
						UserInformation userInformation=new UserInformation();
						userInformation.start(stage);
						});
							
				    }
			}
			else{
						Alert alert=new Alert(AlertType.WARNING);
						alert.setTitle("Message");
						alert.setHeaderText("ID does not Exist");
						alert.showAndWait().ifPresent(response->{
						UserInformation userInformation=new UserInformation();
						userInformation.start(stage);
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
	
	logoutButton=new Button("Logout");
	logoutButton.setStyle("-fx-font: 15 arial; -fx-base: BLUE;");
	logoutButton.setOnAction(new EventHandler<ActionEvent>(){
    public void handle(ActionEvent e){
	        UsersLogIn usersLogIn = new UsersLogIn();
			usersLogIn.start(stage);
			}
		});
	
	HBox h1=new HBox(20,userIdLabel,userIdTextField);
	HBox h2=new HBox(20,nameLabel,nameTextField);
	HBox h3=new HBox(20,positionLabel,positionTextField);
	HBox h4=new HBox(20,addressLabel,addressTextField);
	HBox h5=new HBox(20,phoneNoLabel,phoneNoTextField);
	HBox h6=new HBox(20,dateOfBirthLabel,dateOfBirthTextField);
	HBox h7=new HBox(20,joinDateLabel,joinDateTextField);
	HBox h8=new HBox(20,passwordLabel,passwordField);
	HBox h9=new HBox(20,updateButton,menuButton,logoutButton);
	
	VBox root = new VBox(10);
	root.setPadding(new Insets(10));
	root.getChildren().addAll(h1,h2,h3,h4,h5,h6,h7,h8,separator,h9);
	Scene scene = new Scene(root, 350,450);
	stage.setScene (scene);
    stage.setTitle ("User Information Update");
     stage.show();
	}
}










