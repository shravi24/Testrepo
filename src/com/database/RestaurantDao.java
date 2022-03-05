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

public class RestaurantDao extends BaseDao {
	private int FoodId;
	private String Name;
	private int id;

	private float price;
	private String category;

	private String username;
	private String emailid;
	private String password;
	private long phoneno;
	private String confirmpass;
	private float amt1=0;
	private int count1=0;
	
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
			System.out.println("Match found");
			return true;
		} else {
			// System.out.println(tablePassword);
			System.out.println("Match not Found");
			return false;
		}
	}

	

	public ArrayList getAllFoodItems() {
		PreparedStatement pstmt = null;
		ArrayList<Food> foodList;
		Restaurant robj = new Restaurant();
		String name = robj.getName();
		try {
			foodList = new ArrayList<Food>();
			Connection conn1 = conn;
			//pstmt = conn1.prepareStatement("select * from food order by id where res_id");
			pstmt = conn1.prepareStatement("select * from food order by id where res_id=(select id from  restaurant where name=name )");
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
			phoneno = c1.getPhone_no();
			password = c1.getPassword();
			confirmpass=c1.getConnpassword();
			pstmt.setString(1, username);
			pstmt.setString(2,emailid);
			pstmt.setLong(3,phoneno);
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

	public float getBillAmount(Login ld) 
	{
		String user=ld.getUsername();
		int tamt = 0, tcount = 0;
		int orderid, foodid;
		PreparedStatement pstmt=null,pstmt1=null,stmt;
		try 
		{
			count1=0;
			Connection conn1=conn;
		//	pstmt=conn1.prepareStatement("select count(pizzaid), sum(pizzaprice) from placedorder");
			stmt = conn1.prepareStatement("select * from order_details where cust_id = (select id from customer where username='"+user+"')");
			
			//pstmt=conn1.prepareStatement("select count(q), sum(pizzaprice) from placedorder");
			
			ResultSet rs=stmt.executeQuery();
			//rs.next();
			
				while(rs.next())
				{
					orderid = rs.getInt(1);
					count1 =rs.getInt(4);
					// amt1=rs.getInt(2);
					foodid = rs.getInt(3);
					
					pstmt=conn1.prepareStatement("select price from food where id ="+foodid);
					ResultSet pr = pstmt.executeQuery();
					pr.next();
					int price = pr.getInt(1);
					
					
					//System.out.println("Total price of food is: "+price);
					
					int grand_total =  price * count1;
					
					
					pstmt1=conn1.prepareStatement("insert into order_master(order_id,Grand_total) values(?,?)",Statement.RETURN_GENERATED_KEYS);
					
					pstmt1.setInt(1, orderid);
					pstmt1.setInt(2, grand_total);
					
					pstmt1.executeUpdate();
					
					tcount += count1;
					tamt += grand_total;
					
					
				}
				//System.out.println("Total Cost of the order is: "+tamt);
				System.out.println("Total items in the order are: "+tcount);
				
				return tamt;

				
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
	

	

}