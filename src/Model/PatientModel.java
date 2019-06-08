/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

public class PatientModel{
	public String id;
	public String name;
	public String gender;
	public String age;
	public String phoneNo;
	public String address;
	public String disease;
	public String admissionDate;
	public String cabinNo;

	
	
	public PatientModel(){
		this.id="";
		this.name="";
		this.gender="";
		this.age="";
		this.phoneNo="";
		this.address="";
		this.disease="";
		this.admissionDate="";
		this.cabinNo="";

	
	}
	
	public PatientModel(String id, String name, String gender, String age, String phoneNo, String address, String disease, String admissionDate, String cabinNo){
        this.id=id;
		this.name=name;
		this.gender=gender;
		this.age=age;
		this.phoneNo=phoneNo;
		this.address=address;
		this.disease=disease;
		this.admissionDate=admissionDate;
		this.cabinNo=cabinNo;

	}
	
	//setter
	public void setId(String id){
		this.id=id;
	}
	public void setName(String name){
		this.name=name;
	}	
    public void setGender(String gender){
		this.gender=gender;
	}
	public void setAge(String age){
		this.age=age;
	}
	public void setPhoneNo(String phoneNo){
		this.phoneNo=phoneNo;
	}
	public void setAddress(String address){
		this.address=address;
	}
	public void setDisease(String disease){
		this.disease=disease;
	}
	public void setAdmissionDate(String admissionDate){
		this.admissionDate=admissionDate;
	}
		public void setCabinNo(String cabinNo){
		this.cabinNo=cabinNo;
	}

	
	//getter
	public String getId(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
		public String getGender(){
		return this.gender;
	}
		public String getAge(){
		return this.age;
	}
		public String getPhoneNo(){
		return this.phoneNo;
	}
		public String getAddress(){
		return this.address;
	}
		public String getDisease(){
		return this.disease;
	}
		public String getAdmissionDate(){
		return this.admissionDate;
	}
		public String getCabinNo(){
		return this.cabinNo;
	}

}