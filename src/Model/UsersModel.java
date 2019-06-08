/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

public class UsersModel{
	private static int count=0;
	private String id;
	private String name;
	private String position;
	private String address;
	private String phoneNo;
	private String joinDate;
	private String dateOfBirth;
	private String password;
	
	public UsersModel(){
		this.id=id;
		this.name = "";
		this.position= "";
		this.address = "";
		this.phoneNo = "";
		this.dateOfBirth = "";
		this.joinDate = "";
		this.password="";
	}
	
	
	public UsersModel(String id, String name,String position,String address,String phoneNo,String dateOfBirth,String joinDate,String password){
		
		this.id=id;
		this.name = name;
		this.position=position;
		this.address =address;
		this.phoneNo =phoneNo;
		this.dateOfBirth =dateOfBirth;
		this.joinDate =joinDate;
		this.password=password;
	}
	
//setter

    public void setId(String id){
		this.id=id;
	}
	public void setName(String name){
		this.name = name;
    }
	public void setPosition(String position){
		this.position = position;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public void setPhoneNo(String phoneNo){
		this.phoneNo = phoneNo;
	}
	public void setDateOfBirth(String dateOfBirth){
		this.dateOfBirth = dateOfBirth;
	}
	public void setJoinDate(String joinDate){
		this.joinDate = joinDate;
	}
	public void setPassword(String password){
		this.password = password;
	}

	
//getter	
    public String getId(){
		return this.id;
	}
    public String getName(){
		return this.name;
	}
	public String getPosition(){
		return this.position;
    }
	public String getAddress(){
		return this.address;
	}
		public String getPhoneNo(){
		return this.phoneNo;
    }
	public String getDateOfBirth(){
		return this.dateOfBirth;
	}
	public String getJoinDate(){
		return this.joinDate;
	}
	public String getPassword(){
		return this.password;
	}
	
}	
