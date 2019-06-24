/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.UsersModel;
import View.AddNewUser;

import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UsersService {

    Stage stage;
    static ArrayList<UsersModel> usersList;
    UsersModel usersModel = new UsersModel();

    public boolean add(UsersModel users) {
        if (users.getId().equals("") || users.getName().equals("") || users.getPosition().equals("") || users.getAddress().equals("") || users.getPhoneNo().equals("") || users.getDateOfBirth().equals("") || users.getJoinDate().equals("") || users.getPassword().equals("")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Message");
            alert.setHeaderText("Insufficient Information");
            alert.showAndWait().ifPresent(response -> {
                AddNewUser addNewUser = new AddNewUser();
                addNewUser.start(stage);

            });
        } else {
            try {
                String sql = " ";
                sql += "INSERT INTO ";
                sql += "USERS(ID, NAME, POSITION, ADDRESS, PHONENO,DOB,JOINDATE,PASSWORD)";
                sql += "values('" + users.getId() + "','" + users.getName() + "','" + users.getPosition() + "','" +
                        users.getAddress() + "','" + users.getPhoneNo() + "','" + users.getDateOfBirth() + "','" +
                        users.getJoinDate() + "','" + users.getPassword() + "')";

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
                Statement stmt = con.createStatement();
                int r = stmt.executeUpdate(sql);
                System.out.println(r);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        return true;

    }

    public String getById(String Id) {
        return ("Select ID,PASSWORD ,POSITION from USERS Where ID = '" + Id + "'");
    }

    public List<UsersModel> getAll() {
        usersList = new ArrayList<UsersModel>();
        try {
            String sql2 = "SELECT ID, NAME, POSITION, ADDRESS, PHONENO, DOB, JOINDATE, PASSWORD FROM USERS";

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql2);

            while (rs.next()) {
                UsersModel usersModel = new UsersModel();
                System.out.println(rs.getString("ID"));
                usersModel.setId(rs.getString("ID"));
                System.out.println(rs.getString("NAME"));
                usersModel.setName(rs.getString("NAME"));
                System.out.println(rs.getString("POSITION"));
                usersModel.setPosition(rs.getString("POSITION"));
                System.out.println(rs.getString("ADDRESS"));
                usersModel.setAddress(rs.getString("ADDRESS"));
                System.out.println(rs.getString("PHONENO"));
                usersModel.setPhoneNo(rs.getString("PHONENO"));
                System.out.println(rs.getString("DOB"));
                usersModel.setDateOfBirth(rs.getString("DOB"));
                System.out.println(rs.getString("JOINDATE"));
                usersModel.setJoinDate(rs.getString("JOINDATE"));
                System.out.println(rs.getString("PASSWORD"));
                usersModel.setPassword(rs.getString("PASSWORD"));
                usersList.add(usersModel);
                usersModel = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return usersList;

    }

    public boolean delete(String id) {

        try {
            String sql3 = "";
            sql3 = "delete from USERS  where ID = '" + id + "' ";

            // step2 create  the connection object  s
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
            // step3 create the statement object
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql3);
            //System.out.println(r);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
    }

    public boolean update(String id, String name, String position, String address, String phoneNo, String dateOfBirth, String joinDate, String password) {

        try {

            String sql4 = "update USERS set ID = '" + id + "', NAME='" + name + "', POSITION='" + position + "', ADDRESS='" + address + "', PHONENO='" + phoneNo + "',DOB ='" + dateOfBirth + "',JOINDATE='" + joinDate + "',PASSWORD='" + password + "' where ID = '" + id + "'";

            // step2 create  the connection object  s
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
            // step3 create the statement object
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql4);


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
    }
}	
		


	
	
	
	
	/*---------------------------------------------------------------------------
	public String update(UsersModel users) throws Exception{
		String sql1="update USERS set ID='"+users.GetId()+"',NAME='"+users.GetName()+"' where Id='"+users.GetId()+"'";
        return sql1;
	}
	
	public String delete(UsersModel users) throws Exception{
        String sql2=" ";
		sql2+="DELETE FROM ";
		sql2+="USERS ";
		sql2+="WHERE ID='"+users.GetId()+"'";
		return sql2;
	}
	
	public String getAll(){
		String sql3 = "SELECT ID, NAME FROM USERS";
        return sql3;
        } 
		
	public String getById(String id){
		String sql4 = "SELECT ID, PASSWORD FROM USERS WHERE ID='"+id+"'";
        return sql4;
        } 
	
	public static void main(String[] args){		
		UsersService ob=new UsersService();
		UsersModel users1=new UsersModel();
		
		
		try{
            //System.out.println(ob.add(users1)); 
            //System.out.println(ob.update(users1)); 
            //System.out.println(ob.delete(users1)); 			
			Class.forName("oracle.jdbc.driver.OracleDriver");    
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");    
			Statement stmt=con.createStatement();
            //int r = stmt.executeUpdate(ob.add(users1));
           // System.out.println(r);
			
			//int k = stmt.executeUpdate(ob.update(users1));
            //System.out.println(k);
			
            //int d = stmt.executeUpdate(ob.delete(users1));
            //System.out.println(d);
			
			ResultSet rs = stmt.executeQuery(ob.getAll());
            while(rs.next()){
                System.out.println(rs.getString(1)+" "+rs.getString(2));
            
			
			 ResultSet rt = stmt.executeQuery(ob.getById(id));
            while(rt.next()){
                System.out.println(rt.getString(1)+" "+rt.getString(2));
            }
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}*/

