/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

public class DoctorModel{
	public String id;
	public String name;
	public String gender;
	public String age;
	public String phoneNo;
	public String address;
	public String disease;
	public String admissionDate;
	public String cabinNo;
	public String prescription;

	
	
	public DoctorModel(){
		this.id="";
		this.name="";
		this.gender="";
		this.age="";
		this.phoneNo="";
		this.address="";
		this.disease="";
		this.admissionDate="";
		this.cabinNo="";
		this.prescription="";
	}
	
	public DoctorModel(String id, String name, String gender, String age, String phoneNo, String address, String disease, String admissionDate, String cabinNo, String prescription){
        this.id=id;
		this.name=name;
		this.gender=gender;
		this.age=age;
		this.phoneNo=phoneNo;
		this.address=address;
		this.disease=disease;
		this.admissionDate=admissionDate;
		this.cabinNo=cabinNo;
		this.prescription=prescription;
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
	public void setPrescription(String prescription){
		this.prescription=prescription;
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
		public String getPrescription(){
		return this.prescription;
	}

}