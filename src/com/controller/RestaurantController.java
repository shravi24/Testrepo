package com.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.database.RestaurantDao;
import com.registration.Food;
import com.registration.Login;
import com.service.AdminService;



public class RestaurantController{
	 private String category;
	  private String Name;
	  private int price;
	  private int res_id;
	  private int Quantity;
	int count=0;
	//static int id=0;
	//Food[] f2;
	//Food f3;
	Food f1;
	Scanner sc = new Scanner(System.in);
	AdminService adm = new AdminService();
	
	public static RestaurantDao  cus=new RestaurantDao (); 
	
	
	
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
	
	
	public void genBill(Login ld)
	{
		
		float amount=cus.getBillAmount(ld);
		if(amount!=0)
			
			System.out.println("Total Amount to be paid: "+amount);
		else
			System.out.println("No bill generated");
	}
	


}//class
