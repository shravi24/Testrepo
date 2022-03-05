package com.registration;
public class Restaurant {

	  private int id;
	  private int resown;
	  private String Name;
	  private String Address;
	  private long Phone_no;
	  private String Type;  
	  private int res_id;
	  
	  public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public int getRes_id() {
		return res_id;
	}



	public void setRes_id(int res_id) {
		this.res_id = res_id;
	}



	public Restaurant(int id, int resown, String name, String address, long phone_no, String type) {
		super();
		this.id = id;
		this.resown = resown;
		Name = name;
		Address = address;
		Phone_no = phone_no;
		Type = type;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public int getResown() {
		return resown;
	}

	public void setResown(int resown) {
		this.resown = resown;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}


	public long getPhone_no() {
		return Phone_no;
	}

	public void setPhone_no(long phone_no) {
		Phone_no = phone_no;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

//	@Override
//	public String toString() {
//		return "Restaurant [id=" + id + ", Name=" + Name + ", Address=" + Address + ", Phone_no=" + Phone_no + ", Type="
//				+ Type + "]";
//	}
//	  
	@Override
	public String toString() {
		return "\t" + id + " \t" + Name + " \t" + Address + " \t" + Phone_no + " \t"
				+ Type + "";
	}
	  
	  
	
}