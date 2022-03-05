package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.registration.Food;
import com.registration.Login;
import com.registration.Register;
import com.registration.Restaurant;

public class CustomerDao extends BaseDao {
	
	private int id;
	private String Name;
	private String Food_Items;
	private float price;
	private String category;
	private int Quantity;
	
	Register r1;
	private String custName;
     private float amt=0;
     private int count=0;
	
	public boolean isLogin(Login data)
	{  
		String user=data.getUsername();
		String pass=data.getPassword();
		String connectionpass="root";
		PreparedStatement pstmt=null;
			try {
				Connection conn1=conn;
				pstmt=conn1.prepareStatement("select password from Customer where username = ?");
				pstmt.setString(1,user);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next())
				{
					connectionpass=rs.getString(1);	
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		finally
		{
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			if(pass.equals(connectionpass))
			{
				//System.out.println(tablePassword);
				System.out.println("Match found");
				return true;
			}
			else
			{
				//System.out.println(tablePassword);
				System.out.println("Match not Found");
				return false;
			}
	}
	
	
	
	 public boolean isRegister(Register rd)
	    {
	     
	        String user=rd.getUsername();
	        long phone_no=rd.getPhone_no();
	        String pass=rd.getPassword();
	        String emailid=rd.getEmailid();
	        String confirmpass=rd.getConnpassword();
	        
	       
	        PreparedStatement pstmt=null;
	        
	        try {
				Connection conn1=conn;
				pstmt=conn1.prepareStatement("insert into Customer(username,emailid,phone_no,password,confirmpass) values(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, user);
				pstmt.setString(4, pass);
				pstmt.setLong(3, phone_no);
				pstmt.setString(2, emailid);
				pstmt.setString(5,confirmpass);
				
				int i = pstmt.executeUpdate();
				if(i==1)
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	finally
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	        return false;
	    }
	 
	 //Make order 
	 public boolean makeOrder(ArrayList ar, ArrayList ar1, Login ld)
	 {
		 PreparedStatement pstmt=null,pstmt1=null;
		  Food items=new Food();
		
		 int id=0,j=0;
		 
		 for(int i=0;i<ar.size();i++)
			{
				 Name=(String)ar.get(i);
				 Quantity =(int)ar1.get(i);
				 
				// System.out.println(pizzaName);
				 Connection conn1=conn;
				 try {
					pstmt=conn1.prepareStatement("select * from Food where Name=?");
					 pstmt.setString(1,Name);
					 ResultSet rs=pstmt.executeQuery();
					 if(rs.next()) 
					 {
						 id=(rs.getInt(1));
						 //System.out.println(id);
					 }
					 
				
					if(id!=0)
					{
						String user=ld.getUsername();
					
				   // custName=r1.getUsername();		
					pstmt1=conn1.prepareStatement("insert into Order_details(cust_id,food_id,quantity) values((select id from customer where username='"+user+"'),?,?)",Statement.RETURN_GENERATED_KEYS);
				
				
					pstmt1.setInt(1, id);
					pstmt1.setInt(2, Quantity);
				
					
					
					j = pstmt1.executeUpdate();
					j++;
					}	 
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
					finally
					{
						try 
						{
							pstmt.close();
							//pstmt1.close();
						} catch (SQLException e) 
						{
							
							e.printStackTrace();
						}
					}
			}
		 if(j!=0)
		 {
			 return true;
		 }
		 else
			 return false;
	 }
	 

	 
	 
	//getfoodid 
	 public Food getFoodId(int fid)
		{
			PreparedStatement pstmt=null;
			try 
			{
				Connection conn1=conn;
				pstmt=conn1.prepareStatement("select * from food where id=?");
				Food fitem=null;
				pstmt.setInt(1, fid);
				ResultSet rs=pstmt.executeQuery();
					if(rs.next())
					{
						fitem=new Food();
						fitem.setId(rs.getInt(1));
						fitem.setName(rs.getString(2));
						fitem.setCategory(rs.getString(3));
						
						fitem.setPrice(rs.getFloat(5));
					}
					return fitem;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			finally
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				return null;
		}
	 public ArrayList getAllFoodItems()
		{
		PreparedStatement pstmt=null;
		ArrayList<Food> foodList;
		try {
			foodList = new ArrayList<Food>();
			Connection conn1=conn;
			pstmt=conn1.prepareStatement("select * from Food",Statement.RETURN_GENERATED_KEYS);
			ResultSet rs=pstmt.executeQuery();
			
				while(rs.next())
				{
					Food items=new Food();
					Restaurant obj = new Restaurant();
					
					
					items.setId(rs.getInt(1));
					//obj.setName(rs.getInt(2));
					items.setName(rs.getString(3));
					items.setCategory(rs.getString(5));
					
					items.setPrice(rs.getFloat(4));
				    foodList.add(items);
				}
				return foodList;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			finally
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				return null;
		}
	 
	 

}//class
