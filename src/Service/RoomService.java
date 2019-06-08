/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.RoomModel;
import View.AddNewWard;
import java.sql.ResultSet;
import java.util.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RoomService{
	Stage stage;
	    static ArrayList <RoomModel> roomList;
	    RoomModel  roomModel = new RoomModel();
	    public boolean add(RoomModel room){
			if(room.getBuilding().equals("") || room.getRoomNo().equals("") || room.getRoomType().equals("") || room.getNoOfBed().equals("") || room.getPrice().equals("") || room.getStatus().equals("")){
			Alert alert=new Alert(AlertType.WARNING);
						alert.setTitle("Message");
						alert.setHeaderText("Insufficient Information");
						alert.showAndWait().ifPresent(response->{
							AddNewWard addNewWard = new AddNewWard();
							addNewWard.start(stage);
			
		});
		}
    else{
		try{
		String sql=" ";
		sql+="INSERT INTO ";
        sql+="ROOMINFO(BUILDING, ROOMNO,ROOMTYPE, NOOFBED, PRICE,STATUS)";

        sql+="values('"+room.getBuilding()+"','"+room.getRoomNo()+"','"+room.getRoomType()+"','"+room.getNoOfBed()+"','"+room.getPrice()+"','"+room.getStatus()+"')";
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
		Statement stmt=con.createStatement();
		int r = stmt.executeUpdate(sql);
        System.out.println(r);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
		return true;
}

    public List <RoomModel> getAll(){
		roomList=new ArrayList<RoomModel>();
		try{
			String sql2 = "SELECT BUILDING, ROOMNO, ROOMTYPE, NOOFBED, PRICE, STATUS FROM ROOMINFO";

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
			Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery(sql2);
			
            while(rs.next()){
				RoomModel roomModel=new RoomModel();
				System.out.println(rs.getString("BUILDING"));
				roomModel.setBuilding(rs.getString("BUILDING"));
				System.out.println(rs.getString("ROOMNO"));
				roomModel.setRoomNo(rs.getString("ROOMNO"));
				System.out.println(rs.getString("ROOMTYPE"));
				roomModel.setRoomType(rs.getString("ROOMTYPE"));
				System.out.println(rs.getString("NOOFBED"));
				roomModel.setNoOfBed(rs.getString("NOOFBED"));
				System.out.println(rs.getString("PRICE"));
				roomModel.setPrice(rs.getString("PRICE"));
				System.out.println(rs.getString("STATUS"));
				roomModel.setStatus(rs.getString("STATUS"));
				roomList.add(roomModel);
				roomModel = null;
			} 
		}
         catch(Exception ex){
			ex.printStackTrace();
		}
		return roomList;
		
	}
		
		
		public boolean delete(String roomNo){

             try{
                String sql3 = "";
                 sql3 = "delete from ROOMINFO  where ROOMNO = '"+roomNo+"' ";

                // step2 create  the connection object  s
				 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
                // step3 create the statement object
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery(sql3);
                     //System.out.println(r);
            }
            catch(Exception ex){
                    ex.printStackTrace();
                        }

            return true;
        }
		
		public String getByRoomNo(String roomNo){
		String sql1 = " ";
        sql1+="Select BUILDING,ROOMNO from ROOMINFO Where ROOMNO = '"+roomNo+"'";
		return(sql1);
	}
	public boolean update(String building,String roomNo,String roomType,String noOfBed,String price,String status){

             try{

                String sql = "update ROOMINFO set BUILDING = '"+building+"', ROOMTYPE='"+roomType+"', NOOFBED='"+noOfBed+"', PRICE='"+price+"',STATUS ='"+status+"' where ROOMNO = '"+roomNo+"'";

                // step2 create  the connection object  s
				 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
                // step3 create the statement object
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);


            }
            catch(Exception ex){
                    ex.printStackTrace();
                        }

            return true;
        }
}


	

