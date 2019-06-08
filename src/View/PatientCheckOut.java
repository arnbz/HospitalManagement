package View;
import java.sql.*;
import java.util.*;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Statement;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Insets;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.ArrayList;
import javafx.stage.Stage;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.*;
import java.util.Date;
import java.lang.Enum;
import java.text.*;

public class PatientCheckOut extends Application{
	Text patientIdtext;
	Text nametext;
	Text agetext;
	Text gendertext;
	Text phoneNotext;
	Text addresstext;
	Text diseasetext;
	Text cabinNotext;
	Text roomTypetext;
	Text pricetext;
	Text dateIntext;
	Text dateOuttext;
	Separator separator;
	Button logoutButton;
	//Button menuButton;


	VBox root;
	Scene scene;
	Stage stage;
	
	
	//Rigt side
	
	Text rpatientIdtext;
	Text rnametext;
	Text ragetext;
	Text rgendertext;
	Text rphoneNotext;
	Text raddresstext;
	Text rdiseasetext;
	Text rcabinNotext;
	Text rroomTypetext;
	Text rpricetext;
	Text rdateIntext;
	Text rdateOuttext;
	VBox rightRoot;
	public  static int duration(String d1,String d2 ){
		int daysDiff=0;
	SimpleDateFormat myFormat= new SimpleDateFormat("dd-mm-yyyy");
		

		try{
				Date d3=myFormat.parse(d1);
			Date d4=myFormat.parse(d2);
			int diff = (int)(d3.getTime() -  d4.getTime());
			 daysDiff = diff / (24 * 60 * 60*1000);
			
		}
		catch (	ParseException e){
			
		e.printStackTrace();
		}
	
		return daysDiff;
		}
	
	
public void start(Stage stage){
	patientIdtext = new Text("PID                     :");
	nametext = new Text("Name                 :");
	agetext = new Text("Age                    :");
	gendertext = new Text("Gender               :");
	phoneNotext = new Text("Phone No           :");
	addresstext = new Text("Address              :");
	diseasetext = new Text("Disease               :");
	cabinNotext = new Text("Cabin No            :");
    roomTypetext = new Text("Room Type         :");
	pricetext = new Text("Total Amount     :");
	dateIntext = new Text("Date In               :");
	dateOuttext = new Text("Date Out            :");
	separator = new Separator();
	separator.setPadding(new Insets(10, 0, 0, 0));
	/*menuButton=new Button("Menu");
	menuButton.setStyle("-fx-font: 15 arial; -fx-base: SKYBLUE;");
	
	menuButton.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent e){
			AdminPage adminPage = new AdminPage();
			adminPage.start(stage);
			}
		});*/
		
		
		//rightSide
		
    rpatientIdtext = new Text();
	rnametext = new Text();
	ragetext = new Text();
	rgendertext = new Text();
	rphoneNotext = new Text();
	raddresstext = new Text();
	rdiseasetext = new Text();
	rcabinNotext = new Text();
    rroomTypetext = new Text();
	rpricetext = new Text();
	rdateIntext = new Text();
	rdateOuttext = new Text();
	ResultSet rs1;
	ResultSet rs2;
    String tprice;
	
	
	
	try{
		
	
	String id=CashScript.patientId;
	String date=CashScript.currentDate;
			System.out.println(id);


		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
	    Statement stmt=con.createStatement(); 
				String sql4 ="";
			sql4 = "select PATIENTS.ID ,PATIENTS.NAME,PATIENTS.AGE,PATIENTS.GENDER,PATIENTS.PHONE,PATIENTS.ADDRESS,PATIENTS.DISEASE,PATIENTS.CABINNO from PATIENTS where ID='"+id+"'";
		
			 rs1 = stmt.executeQuery(sql4);
			 
			 
			 
			 if(rs1.next()==true){
			
			 rpatientIdtext.setText(rs1.getString("ID"));
			 rnametext.setText(rs1.getString("NAME"));
			 ragetext.setText(rs1.getString("AGE"));
			 rgendertext.setText(rs1.getString("GENDER"));
			 rphoneNotext.setText(rs1.getString("PHONE"));
			 raddresstext.setText(rs1.getString("ADDRESS"));
			 rdiseasetext.setText(rs1.getString("DISEASE"));
			 rcabinNotext.setText(rs1.getString("CABINNO"));
			
			 }
			 
			 String sql5="";
			 sql5="select PATIENTS.ADMISSIONDATE,ROOMINFO.ROOMTYPE,ROOMINFO.PRICE FROM PATIENTS ,ROOMINFO WHERE PATIENTS.ID='"+id+"' and  PATIENTS.CABINNO=ROOMINFO.ROOMNO";
			 rs2 = stmt.executeQuery(sql5);
			 if(rs2.next()==true){
			 String date1=rs2.getString(1);
			 int dura=duration(date,date1);
			 String price=rs2.getString(3);
			 	int pr;
			 pr = Integer.parseInt(price);
			 System.out.println(dura);
			 System.out.println(price);
			 
		    int total=pr*dura;
	    tprice=Long.toString(total);
	     rpricetext.setText(tprice);
		 rdateIntext.setText(rs2.getString(1));
		 rdateOuttext.setText(date);
		 rroomTypetext .setText(rs2.getString(2));
		 
		 
		 }
			 
	  }
	  
	   
		catch(Exception ex){
             ex.printStackTrace();
        }
				    
					
	    logoutButton=new Button("Finish");
		logoutButton.setStyle("-fx-font: 15 arial; -fx-base: BLUE;");
		logoutButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				UsersLogIn usersLogIn = new UsersLogIn();
				usersLogIn.start(stage);
				}
			});                            
	   

		VBox root = new VBox(15);
		root.setPadding(new Insets(60));
		root.getChildren().addAll(patientIdtext,nametext, agetext,gendertext,  phoneNotext, addresstext, diseasetext, cabinNotext,roomTypetext,dateIntext,dateOuttext,pricetext);
		
		//rightRoot
		VBox rightRoot = new VBox(15);
		rightRoot.setPadding(new Insets(60,10,10,10));
		rightRoot.getChildren().addAll(rpatientIdtext,rnametext, ragetext,rgendertext,  rphoneNotext, raddresstext, rdiseasetext, rcabinNotext,rroomTypetext,rdateIntext,rdateOuttext,rpricetext,logoutButton);
		
		HBox h1 = new HBox(5, root, rightRoot);
		
		
		Scene scene = new Scene(h1,400,560);
		stage.setScene (scene);
        stage.setTitle ("Patient Checkout");
        stage.show();
			
     }
 
 
		
}



