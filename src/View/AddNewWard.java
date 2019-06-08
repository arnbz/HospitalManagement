/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.RoomModel;
import Service.RoomService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AddNewWard extends Application{
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
	Button addNewButton;
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
	addNewButton=new Button("Add New");
	addNewButton.setStyle("-fx-font: 15 arial; -fx-base: BLUE;");
	addNewButton.setOnAction(e->{
		
		String building = buildingTextField.getText();
		String roomNo = roomNoTextField.getText();
		String roomType = roomTypeTextField.getText();
		String noOfBed = noOfBedTextField.getText();
		String price = priceTextField.getText();
		String status = statusTextField.getText();
		
		RoomModel roomModel = new RoomModel(building, roomNo, roomType, noOfBed, price, status);
		
		RoomService roomService = new RoomService();
		roomService.add(roomModel);
		RoomInformation roomInformation = new RoomInformation();
		roomInformation.start(stage);
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
	HBox h7=new HBox(10,addNewButton,menuButton);
	
	VBox root = new VBox(10);
	root.setPadding(new Insets(30,10,10,10));
	root.getChildren().addAll(h1,h2,h3,h4,h5,h6,separator,h7);
	Scene scene = new Scene(root, 350,400);
	stage.setScene (scene);
    stage.setTitle ("Add New Ward");
     stage.show();
	}
}










