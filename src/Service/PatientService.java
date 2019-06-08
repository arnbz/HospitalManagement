/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.PatientModel;
import View.PatientRegistration;

import java.sql.DriverManager;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;

import javafx.stage.Stage;

import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Iterator;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PatientService {
    Stage stage;
    ArrayList<PatientModel> patientList;
    PatientModel patientModel = new PatientModel();

    public boolean add(PatientModel patient) {

        if (patient.getId().equals("") || patient.getName().equals("") || patient.getGender().equals("") || patient.getAge().equals("") || patient.getPhoneNo().equals("") || patient.getAddress().equals("") || patient.getDisease().equals("")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Message");
            alert.setHeaderText("Insufficient Information");
            alert.showAndWait().ifPresent(response -> {
                PatientRegistration patientRegistration = new PatientRegistration();
                patientRegistration.start(stage);

            });
        }

        try {
            String sql = " ";
            sql += "INSERT INTO ";
            sql += "PATIENTS(ID, NAME, GENDER, AGE, PHONE, ADDRESS, DISEASE, ADMISSIONDATE, CABINNO)";
            sql += "values('" + patient.getId() + "','" + patient.getName() + "','" + patient.getGender() + "','" + patient.getAge() + "','" + patient.getPhoneNo() + "','" + patient.getAddress() + "','" + patient.getDisease() + "','" + patient.getAdmissionDate() + "','" + patient.getCabinNo() + "')";

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
            Statement stmt = con.createStatement();
            int r = stmt.executeUpdate(sql);
            System.out.println(r);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public List<PatientModel> getAll() {
        patientList = new ArrayList<PatientModel>();
        try {
            String sql2 = "select ID, NAME, GENDER, AGE, PHONE, ADDRESS, DISEASE, ADMISSIONDATE, CABINNO from PATIENTS";

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql2);

            while (rs.next()) {
                PatientModel patient = new PatientModel();
                System.out.println(rs.getString("ID"));
                patient.setId(rs.getString("ID"));
                System.out.println(rs.getString("NAME"));
                patient.setName(rs.getString("NAME"));
                System.out.println(rs.getString("GENDER"));
                patient.setGender(rs.getString("GENDER"));
                System.out.println(rs.getString("AGE"));
                patient.setAge(rs.getString("AGE"));
                System.out.println(rs.getString("PHONE"));
                patient.setPhoneNo(rs.getString("PHONE"));
                System.out.println(rs.getString("ADDRESS"));
                patient.setAddress(rs.getString("ADDRESS"));
                System.out.println(rs.getString("DISEASE"));
                patient.setDisease(rs.getString("DISEASE"));
                System.out.println(rs.getString("ADMISSIONDATE"));
                patient.setAdmissionDate(rs.getString("ADMISSIONDATE"));
                System.out.println(rs.getString("CABINNO"));
                patient.setCabinNo(rs.getString("CABINNO"));

                patientList.add(patient);
                //patientList.add(new PatientModel(patient.GetId(),patient.GetName(),patient.GetGender(),patient.GetAge(),patient.GetPhoneNo(),patient.GetAddress(),patient.GetDisease(),patient.GetAdmissionDate(),patient.GetCabinNo()));
                //System.out.println(patient);
                patient = null;

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return patientList;
    }

    public boolean delete(String id) {

        try {
            String sql3 = "";
            sql3 = "delete from PATIENTS  where ID = '" + id + "' ";

            // step2 create  the connection object  s
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
            // step3 create the statement object
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql3);
            //System.out.println(r);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
    }

    public String getById(String id) {
        String sql1 = " ";
        sql1 += "Select ID,NAME from PATIENTS Where ID = '" + id + "'";
        return (sql1);
    }

    public boolean update(String id, String name, String gender, String age, String phone, String address, String disease, String admissionDate, String cabinNo) {

        try {

            String sql = "update PATIENTS set NAME = '" + name + "', Gender='" + gender + "', AGE='" + age + "', PHONE='" + phone + "', ADDRESS='" + address + "',DISEASE ='" + disease + "',ADMISSIONDATE='" + admissionDate + "',CABINNO='" + cabinNo + "' where ID = '" + id + "'";

            // step2 create  the connection object  s
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
            // step3 create the statement object
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
    }


    public int updateprescription(String id, String Prescription) {

        try {
            String sql = "";
            sql += "update PATIENTS set PRESCRIPTION = '" + Prescription + "' Where ID = '" + id + "'";

            // step2 create  the connection object  s
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmgmt", "arnab", "Apple_123@");
            // step3 create the statement object
            Statement stmt = con.createStatement();
            int rs = stmt.executeUpdate(sql);
            return 1;

        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }

    }
	
	

	
	/*public String update(PatientModel patient) throws Exception{
		String sql1="update Patient set ID='"+patient.GetId()+"',NAME='"+patient.GetName()+"' where Id='"+patient.GetId()+"'";
        return sql1;	}

	
	public String delete(PatientModel patient) throws Exception{
        String sql2=" ";
		sql2+="DELETE FROM ";
		sql2+="PATIENT ";
		sql2+="WHERE ID='"+patient.GetId()+"'";
		return sql2;
	}
	
	public String getAll(){
		String sql3 = "SELECT ID, NAME FROM Patient";
        return sql3;
        } 
		
	public String getById(PatientModel patient){
		String sql4 = "SELECT ID, NAME FROM Patient WHERE ID='"+patient.GetId()+"'";
        return sql4;
        } 
	
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		String id,name;
		System.out.print("Enter Your ID:");
		id=sc.next();
		System.out.print("Enter Your Name:");
		name=sc.next();
		
		PatientService ob=new PatientService();
		Patient patient1=new Patient(id,name);
		
		
		try{
            //System.out.println(ob.add(patient1)); 
            //System.out.println(ob.update(patient1)); 
            //System.out.println(ob.delete(patient1)); 			
			Class.forName("oracle.jdbc.driver.OracleDriver");    
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");    
			Statement stmt=con.createStatement();
            //int r = stmt.executeUpdate(ob.add(patient1));
           // System.out.println(r);
			
			//int k = stmt.executeUpdate(ob.update(patient1));
            //System.out.println(k);
			
            //int d = stmt.executeUpdate(ob.delete(patient1));
            //System.out.println(d);
			
			ResultSet rs = stmt.executeQuery(ob.getAll());
            while(rs.next()){
                System.out.println(rs.getString(1)+" "+rs.getString(2));
            
			
			 ResultSet rt = stmt.executeQuery(ob.getById(patient1));
            while(rt.next()){
                System.out.println(rt.getString(1)+" "+rt.getString(2));
            }
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}*/

}


