package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.registration.Food;
import com.registration.Login;
import com.registration.Register;
import com.registration.Restaurant;

public class AdminDao extends BaseDao {
	private int FoodId;
	private String Name;
	private int id;

	private float price;
	private String category;

	private String username;
	private String emailid;
	private String password;
	private long phone_no;
	private String confirmpass;
	private String Address;
	private String type;
	Scanner sc = new Scanner(System.in);

	public Boolean isLogin(Login data) {
		String user = data.getUsername();
		String pass = data.getPassword();
		String connectionpass = "root";
		PreparedStatement pstmt = null;
		try {
			Connection conn1 = conn;
			pstmt = conn1.prepareStatement("select password from Admin where username = ?");
			pstmt.setString(1, user);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				connectionpass = rs.getString(1);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pass.equals(connectionpass)) {
			// System.out.println(tablePassword);
			//System.out.println("Match found");
			return true;
		} else {
			// System.out.println(tablePassword);
			//System.out.println("Match not Found");
			return false;
		}
	}

	

	public ArrayList getAllFoodItems() {
		PreparedStatement pstmt = null;
		ArrayList<Food> foodList;
		try {
			foodList = new ArrayList<Food>();
			Connection conn1 = conn;
			pstmt = conn1.prepareStatement("select * from food order by id");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Food food = new Food();
				food.setId(rs.getInt(1));
				food.setName(rs.getString(3));
				food.setCategory(rs.getString(5));
				food.setPrice(rs.getFloat(4));
				foodList.add(food);
			}
			return foodList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public Food getFood(int fId) {
		PreparedStatement pstmt = null;
		try {
			Connection conn1 = conn;
			pstmt = conn1.prepareStatement("select * from food");
			ResultSet rs = pstmt.executeQuery();
			Food food = new Food();
			while (rs.next()) {
				Food f = new Food();
				f.setId(rs.getInt(1));
				f.setName(rs.getString(3));
				f.setCategory(rs.getString(5));
				f.setPrice(rs.getFloat(4));
			}
			return food;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public int addCustomer(Register c1) {
		PreparedStatement pstmt = null;
		
		try {
			Connection conn1 = conn;
			pstmt = conn1.prepareStatement("insert into customer(username,emailid,phoneno,password,confirmpass) values(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			username = c1.getUsername();
			emailid = c1.getEmailid();
			phone_no = c1.getPhone_no();
			password = c1.getPassword();
			confirmpass=c1.getConnpassword();
			pstmt.setString(1, username);
			pstmt.setString(2,emailid);
			pstmt.setLong(3,phone_no);
			pstmt.setString(4, password);
			pstmt.setString(5, confirmpass);
			

			int i = pstmt.executeUpdate();			
			return i;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	public int deleteCustomer(int custId)
	{
		PreparedStatement pstmt=null;
		try 
		{
			Connection conn1=conn;
			pstmt=conn1.prepareStatement("delete from customer where id=?");
			
			pstmt.setInt(1, id);
			int i = pstmt.executeUpdate();
			return i;
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
			return 0;	
		
	}
	
	public int checkcustId(int custId)
	{	
		PreparedStatement pstmt=null;
		try 
		{
			Connection conn1=conn;
			pstmt=conn1.prepareStatement("select username from customer where id='"+custId+"'");
			
			pstmt.setInt(1, id);
			int i = pstmt.executeUpdate();
			return i;
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
			return 0;
	}
	
public int deleteCustomerDetails(Register c1) {
		
		int count = 0;
		PreparedStatement ps = null;
		
		String q1 = "delete from customer where id = ?";
		
		try {
			Connection conn1=conn;
			ps = conn1.prepareStatement(q1);
			ps.setInt(1, c1.getId());
			count = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}



	public ArrayList getAllCustomer()
{
PreparedStatement pstmt=null;
ArrayList<Register> custList;
try {
	custList = new ArrayList<Register>();
	Connection conn1=conn;
	pstmt=conn1.prepareStatement("select * from customer");
	ResultSet rs=pstmt.executeQuery();
	
		while(rs.next())
		{
			Register cust1=new Register();
			cust1.setId(rs.getInt(1));
			cust1.setUsername(rs.getString(2));
			cust1.setEmailid(rs.getString(3));
			cust1.setPhone_no(rs.getLong(4));
			cust1.setPassword(rs.getString(5));
			cust1.setConnpassword(rs.getString(6));
			custList.add(cust1);
		}
		return custList;
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

	public int addRestaurantDB(Restaurant r1)
	  {
		PreparedStatement pstmt=null;
		try 
		{
			Connection conn1=conn;
			pstmt=conn1.prepareStatement("insert into restaurant(Name,Address,phone_no,Type) values(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			
			Name=r1.getName();
			Address = r1.getAddress();
			phone_no = r1.getPhone_no();
		    type=r1.getType();
			
			pstmt.setString(1, Name);
			pstmt.setString(2, Address);
			pstmt.setFloat(3, phone_no);
			pstmt.setString(4, type);
			
			
			int i = pstmt.executeUpdate();
			return i;
			
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
			return 0;
	}
	
	
	
	
	
public int deleteRestaurantDetails(Restaurant r1) {
		
		int count = 0;
		PreparedStatement ps = null;
		
		String q1 = "delete from restaurant where id = ?";
		
		try {
			Connection conn1=conn;
			ps = conn1.prepareStatement(q1);
			ps.setInt(1, r1.getId());
			count = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

public ArrayList getAllRestaurant()
{
PreparedStatement pstmt=null;
ArrayList<Restaurant> restList;
try {
	restList = new ArrayList<Restaurant>();
	Connection conn1=conn;
	pstmt=conn1.prepareStatement("select * from restaurant");
	ResultSet rs=pstmt.executeQuery();
	
		while(rs.next())
		{
			Restaurant rest1=new Restaurant();
			rest1.setId(rs.getInt(1));
			rest1.setName(rs.getString(2));
			rest1.setAddress(rs.getString(3));
			rest1.setPhone_no(rs.getLong(4));
			rest1.setType(rs.getString(5));
			
			restList.add(rest1);
		}
		return restList;
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


//public int addfooditems(Food item) {
//	PreparedStatement pstmt = null;
//	try {
//		Connection conn1 = conn;
//		pstmt = conn1.prepareStatement("insert into food(id,name,price,category) values(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
//
//		 FoodId=item.getId();
//		Name = item.getName();
//		id=item.getId();
//		category = item.getCategory();
//
//		price = item.getPrice();
//		pstmt.setInt(1, id);
//		pstmt.setString(2, Name);
//		pstmt.setString(3, category);
//		pstmt.setFloat(2, price);
//
//		int i = pstmt.executeUpdate();
//		return i;
//
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} finally {
//		try {
//			pstmt.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	return 0;

}
