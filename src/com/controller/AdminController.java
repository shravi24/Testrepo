package com.controller;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


import com.database.AdminDao;
import com.registration.Food;
import com.registration.Login;
import com.registration.Register;
import com.registration.Restaurant;
import com.service.AdminService;

public class AdminController {
	AdminService adm = new AdminService();
	AdminDao adao = new AdminDao();
	static CustomerController c1=new CustomerController();
	Food[] f;
	Food f1;
	static int id = 0;
	Restaurant r1 = new Restaurant();

	private String Address;
	private String type;
	private String category;
	private String Name;
	private int price;
	private int res_id;
	
	private String emailid;
	private String password;
	private long phone_no;

	private String confirmpassword ;

	Register customer;
	Register c2 = new Register();
	
	Scanner sc = new Scanner(System.in);

	public boolean loginContoller(Login ld) {
		boolean b1 = adm.validLogin(ld);
		if (b1 == true) {
			System.out.println("Successfully login");
			return true;
		} else {
			System.out.println("login failed");
			return false;
		}
	}

	
	  public int addItems() { 
	  System.out.println("Add a Food");
	  
	  f1=new Food(); //f1.setId(id);
	  System.out.println("Enter the Details of Food \n:");
	  
	  System.out.println("Enter the Food Name: "); Name=sc.next();
	  System.out.println("Enter the Category: (veg/non-veg)"); category=sc.next();
	  
	  System.out.println("Enter the price: "); price=sc.nextInt(); //f1.setId(id);
	  f1.setName(Name); f1.setCategory(category);
	  
	  //f1.setPrice(price); int i =adm.validAdd(f1); if(i!=0)
	 // System.out.println("Total rows inserted: "+i); else
	  System.out.println("Object can not be null"); return 0; }
	 
	public int showallFoodItems() {
		//System.out.println("Show All Pizza");
		ArrayList<Food> foodList = adm.validDisplay();
		for (int i = 0; i < foodList.size(); i++) {
			Food food = foodList.get(i);
			
			System.out.println(food);
		}
		return 0;
	}

	public int addCustomer() {
		System.out.println("Add Customer");

		customer = new Register();
		customer.setId(id);
		System.out.println("Enter the Details of Customer \n:");
		System.out.println("Enter  Name: ");
		Name = sc.next();
		System.out.println("Enter Email Id");
		emailid = sc.next();
		System.out.println("Enter Password:");
		password = sc.next();
		System.out.println("Enter Password Again:");
		confirmpassword = sc.next();
		System.out.println("Enter the Phone Number: ");
		phone_no = sc.nextLong();
		customer.setUsername(Name);
		customer.setEmailid(emailid);
		customer.setPassword(password);
		customer.setPhone_no(phone_no);
		
		boolean b1=c1.registerContoller(customer);
		if(b1==true)
		{
			System.out.println("Customer added succesfully.......!!");
		}
		else 
		{
			System.out.println("Password doesn't matches ConfirmPassword");
		}
		
		return 0;
	}
	
	public int deleteCustomer()
	{
	  System.out.println("Delete a Customer");
	  System.out.println("Enter the customer Id you want to Delete: ");
	  int cId=sc.nextInt();
	  c2.setId(cId);
	  int b2 = adao.deleteCustomerDetails(c2);
	  System.out.println(b2+" Rows deleted");
		
	return 0;
	}
	public int showAllCustomer()
	{
	//System.out.println("Show All Customers");
	ArrayList<Register> custList=adm.validDisplay1();
		for(int i=0;i<custList.size();i++)
		{
			Register c1=custList.get(i);
		    System.out.println(c1);
		}
	return 0;	
	}

	
	public int addRestaurant()
	{
	  //System.out.println("Add a Restaurant");
	
	  r1 = new Restaurant();
	  r1.setId(id);
	  System.out.println("Enter the Details of Restaurant \n:");
	
	  System.out.println("Enter the Restaurant Name: ");
	  Name=sc.next();
	  System.out.println("Enter the Restaurant Type: (Indian/Chinese)");
	  type=sc.next();
	 
	  System.out.println("Enter the Address: ");
	  Address=sc.next();
	  System.out.println("Enter the phone no.: ");
	  phone_no=sc.nextLong();
	  
	
	  r1.setName(Name);
	  r1.setType(type);
	  r1.setAddress(Address);

	  r1.setPhone_no(phone_no);
	 
	  int i =adm.validAddRes(r1);
	  if(i!=0)
	  System.out.println("Total rows inserted: "+i);
	  else
	  System.out.println("Object can not be null");	
	
	  return 0;
	}

	
	
	public int deleteRestaurant()
	{
		//System.out.println("Delete a Restaurant");
		System.out.println("Enter the Restaurant Id you want to Delete");
	    int rId=sc.nextInt();
	    r1.setId(rId);
	    int b2 = adao.deleteRestaurantDetails(r1);
	    System.out.println(b2+" Rows deleted");
		
	return 0;
	}
	
	
	public int showRestaurant()
	{
	  //System.out.println("Show All Restaurants");
	  ArrayList<Restaurant> restList=adm.validDisplayRest();
		for(int i=0;i<restList.size();i++)
		{
			Restaurant r1=restList.get(i);
		    System.out.println(r1);
		}
	return 0;	
	}


}
