package com.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.database.CustomerDao;
import com.database.RestaurantDao;
import com.registration.Food;
import com.registration.Login;
import com.registration.Register;

public class CustomerController {
	 private String category;
	  private String Name;
	  private int price;
	  private int res_id;
	  private int Quantity;
	int count=0;
	//static int id=0;
	Food[] f2;
	Food f3;
	Scanner sc = new Scanner(System.in);
	public static CustomerDao cus=new CustomerDao(); 
	public static RestaurantController  rest_bill=new RestaurantController (); 
	
	public boolean loginContoller(Login ld)
	{
		boolean b1=cus.isLogin(ld);
		if(b1==true)
		{	 
		System.out.println("Successfully login");
		return true;	
		}
		else
		{ 	
		System.out.println("login failed");
		return false;	 
		}
	}
	
	public boolean registerContoller(Register rd)
	{
		boolean b1=cus.isRegister(rd);
		if(b1==true)
		{	 
		System.out.println("Successfully Registered");
		return true;	
		}
		else
		{ 	
		System.out.println("Registration failed");
		return false;	 
		}
	}
	
	//Place order 
	public void placeOrder(Login ld)
	{
		String Name;
		String st;
		ArrayList <String> foodorder=new ArrayList<String>();
		ArrayList<Integer> fooditems = new ArrayList<Integer>();
		
		System.out.println("Place Your Order: ");
		do
		{
		System.out.println("Enter the food items  you want to Order: ");
		Name=sc.next();
		foodorder.add(Name);
		
		System.out.println("Enter the quantity ");
		Quantity=sc.nextInt();
		fooditems.add(Quantity);
		
		System.out.println("DO you Want more items : (Y/N)");
		st=sc.next();
		}while(st.equals("Y") || st.equals("y"));	
		boolean b1=cus.makeOrder(foodorder, fooditems, ld);
		if(b1==true)
		{
			System.out.println("Order is Placed");
			rest_bill.genBill(ld);
		}
		else
		{
			System.out.println("Order not Placed");
		}
	}
	
	public int showFoodItems()
	{
	System.out.println("Menu's  list:");
	ArrayList <Food> FoodList=cus.getAllFoodItems();
		for(int i=0;i<FoodList.size();i++)
		{
		Food list=FoodList.get(i);
		System.out.println(list);
		}
	return 0;	
	}
	

}//class
