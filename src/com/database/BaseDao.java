package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {
	 protected static Connection conn;
		public void getConn()
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Food_Ordering_System","root","root");				
				//System.out.println("Driver Connected");
				//return conn;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//return conn;
		}
			
		
		public void closeConn()
		{
			try {
				if(conn!=null)
					{
					conn.close();
					System.out.println("Connection Closed");
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}


