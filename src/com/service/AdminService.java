package com.service;

import java.util.ArrayList;

import com.FoodOrderException.InvalidCategoryException;
import com.FoodOrderException.InvalidNameException;
import com.database.AdminDao;
import com.registration.Food;
import com.registration.Login;
import com.registration.Register;
import com.registration.Restaurant;


public class AdminService {
private static AdminDao adao=new AdminDao();
private static Register arao= new Register();

	
	public boolean validLogin(Login ld)
	{
		return adao.isLogin(ld);
	}


//	public int validAdd(Food f1) 
//	{	
//		int i=0;
//		try
//		{
//			if(f1==null)
//			{
//			throw new NullPointerException();
//			}	
//			
//			else if(f1.getName().equals(null) || f1.getName().equals(" ") || f1.getName().matches(".*\\d.*"))
//			{
//				throw new InvalidNameException();
//			}
//			else if(!(f1.getCategory().equals("veg")) && !(f1.getCategory().equals("non-veg")))
//			{
//				throw new InvalidCategoryException();
//			}
//			
//			else
//				System.out.println("");	
//		}
//		catch(NullPointerException npe)
//		{
//			return (0);
//		}
//		
//		catch(InvalidNameException ine)
//		{
//			ine.nameInvalid();
//		}
//		catch(InvalidCategoryException ine)
//		{
//			ine.typeInvalid();
//		}
//		
//		return i;
//	}
	
	public ArrayList validDisplay()
	{
		return adao.getAllFoodItems();
		 
	}
	
	
	public int checkId(int custId)
	{
		return adao.checkcustId(custId);
		 
	}
	
	//Validations for customer
	public ArrayList validDisplay1()
	{
		return adao.getAllCustomer();
		 
	}
	
//	public int validDelete(int custId)
//	{
//		
//		try
//		{
//			//PizzaBean pz=adao.getPizza(pzId);
//			Register rid=arao.getId();
//			if(rid==null)
//			{
//			throw new NullPointerException();
//			}	
//			return adao.deletePizzaCountDB(rId);
//			 
//		}
//		catch(NullPointerException npe)
//		{
//			return 0;
//		}
//	
//	}


	public int validAddRes(Restaurant r1) 
	{	
		int i=0;
		try
		{
			if(r1==null)
			{
			throw new NullPointerException();
			}	
			
			else if(r1.getName().equals(null) || r1.getName().equals(" ") || r1.getName().matches(".\\d."))
			{
				throw new InvalidNameException();
			}
//			else if(!(r1.getType().equals("indian")) && !(r1.getType().equals("chinese")))
//			{
//				throw new InvalidCategoryException();
//			}
			
			else
				i=adao.addRestaurantDB(r1);	
		}
		catch(NullPointerException npe)
		{
			return (0);
		}
		
		catch(InvalidNameException ine)
		{
			ine.nameInvalid();
		}
//		catch(InvalidCategoryException ine)
//		{
//			ine.typeInvalid();
//		}
		return i;
	}


	public ArrayList validDisplayRest()
	{
		return adao.getAllRestaurant();
		 
	}




}



	

