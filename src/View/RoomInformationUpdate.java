/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Service.RoomService;
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
import javafx.stage.Stage;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RoomInformationUpdate extends Application{
	VBox root;
	Label buildingLabel;
	Label roomNoLabel;
	Label roomTypeLabel;
	Label noOfBedLabel;
	Label priceLabel;
	Label statusLabel;
	TextField buildingTextField;
	TextField roomNoTextField;
	TextField roomTypeTextField;
	TextField noOfBedTextField;
	TextField priceTextField;
	TextField statusTextField;
	Separator separator;
	Button updateButton;
	Button menuButton;
	Scene scene;
	Stage stage;
	
public void start(Stage stage){
	buildingLabel = new Label("Building                     ");
	roomNoLabel = new Label ("Room No                   ");
	roomTypeLabel = new Label("Room Type                ");
	noOfBedLabel=new Label    ("No of Bed                  ");
	priceLabel=new Label("Price                          ");
	statusLabel=new Label("Status                        ");  
	buildingTextField=new TextField();
	roomNoTextField=new TextField();
	roomTypeTextField=new TextField();
	noOfBedTextField=new TextField();
	priceTextField=new TextField();
	statusTextField=new TextField();
	separator = new Separator();
	separator.setPadding(new Insets(10, 0, 0, 0));
	updateButton=new Button("Update");
	updateButton.setStyle("-fx-font: 15 arial; -fx-base: BLUE;");
	updateButton.setOnAction(e->{
				
try{
		String building = buildingTextField.getText();
		String roomNo = roomNoTextField.getText();
		String roomType = roomTypeTextField.getText();
		String noOfBed = noOfBedTextField.getText();
		String price = priceTextField.getText();
		String status = statusTextField.getText();
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
			    Statement stmt=con.createStatement();
			    RoomService roomService = new RoomService();
				ResultSet rs = stmt.executeQuery(roomService.getByRoomNo(roomNo));
			    if(rs.next()==true){
					if((rs.getString(2).equals(roomNo))){
						roomService.update(building,roomNo,roomType,noOfBed,price,status);
						Alert alert=new Alert(AlertType.WARNING);
						alert.setTitle("Message");
						alert.setHeaderText("Successfully Updated");
						alert.showAndWait().ifPresent(response->{
						RoomInformation roomInformation=new RoomInformation();
						roomInformation.start(stage);
						
					});
			
					}
					else{
						Alert alert=new Alert(AlertType.WARNING);
						alert.setTitle("Message");
						alert.setHeaderText("Room Does not Exist");
						alert.showAndWait().ifPresent(response->{
						RoomInformation roomInformation=new RoomInformation();
						roomInformation.start(stage);
						});
							
				    }
			}
			else{
						Alert alert=new Alert(AlertType.WARNING);
						alert.setTitle("Message");
						alert.setHeaderText("Room Does not Exist");
						alert.showAndWait().ifPresent(response->{
						RoomInformation roomInformation=new RoomInformation();
						roomInformation.start(stage);
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
	
	HBox h1=new HBox(10,buildingLabel,buildingTextField);
	HBox h2=new HBox(10,roomNoLabel,roomNoTextField);
	HBox h3=new HBox(10,roomTypeLabel,roomTypeTextField);
	HBox h4=new HBox(10,noOfBedLabel,noOfBedTextField);
	HBox h5=new HBox(10,priceLabel,priceTextField);
	HBox h6=new HBox(10,statusLabel,statusTextField);
	HBox h7=new HBox(10,updateButton,menuButton);
	
	VBox root = new VBox(10);
	root.setPadding(new Insets(30,10,10,10));
	root.getChildren().addAll(h1,h2,h3,h4,h5,h6,separator,h7);
	Scene scene = new Scene(root, 350,400);
	stage.setScene (scene);
    stage.setTitle ("Room Information Update");
     stage.show();
	}
}










