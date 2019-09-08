package com.caps.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CallableStatementExample {

	public static void main(String[] args) {
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		try {
			
			/*
			 * 1. Load the Driver
			 */
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			/*
			 * 2. Get the DB Connection via Driver
			 */
			String dbUrl="jdbc:mysql://localhost:3306/cleveridiots_db?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);
			System.out.println("Connected...");
			
			/*
			 * 3. Issue the SQL query via connection
			 */
			String sql = "call GetAllUsersInfo()";
			cstmt = con.prepareCall(sql);
			boolean state = cstmt.execute();
			
			/*
			 * 4. Process the results
			 */
			if(state)
			{
				System.out.println("Got rows and columns");
				rs= cstmt.getResultSet();
				while(rs.next()){
					int userid = rs.getInt(1);				// giving the column position as 1 for user id
					String firstname = rs.getString(2);		// giving the column position as 2 for fname
					String lastname = rs.getString(3);		// giving the column position as 3 for lname
					String passwd = rs.getString(4);		// giving the column position as 4 for psswd
					//can give column name also within the "   "

					System.out.println(userid);
					System.out.println(firstname);
					System.out.println(lastname);
					System.out.println(passwd);
					System.out.println("*********************");
				}
			}
			else
			{
				System.out.println("Got int count");
				int count = cstmt.getUpdateCount();
				if(count>0)
				{

					System.out.println("deletion complete");

				}
				else
				{
					System.out.println("deletion unsuccesful");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
			/*
			 * 5. Close all the JDBC Objects
			 */
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(cstmt != null){
				try {
					cstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
