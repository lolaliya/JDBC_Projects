package com.caps.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class MyFirstJDBCProgram2 {


	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			
			/*
			 * 1. Load the Driver
			 */
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			/*
			 * 2. Get the DB Connection via Driver
			 */
			String dbUrl="jdbc:mysql://localhost:3306/cleveridiots_db";

			Scanner in = new Scanner(System.in);
			System.out.println("enter username and password");
			String username = in.nextLine();
			String password = in.nextLine();
			in.close();
			
			con = DriverManager.getConnection(dbUrl,username,password);
		
			System.out.println("Connected...");
			
			/*
			 * 3. Issue the SQL query via connection
			 */
			String sql = "select * from users_info";

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			/*
			 * 4. Process the results
			 */

			while(rs.next()){
				int userid = rs.getInt("user_id");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String passwd = rs.getString("password");

				System.out.println(userid);
				System.out.println(firstname);
				System.out.println(lastname);
				System.out.println(passwd);
				System.out.println("*********************");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
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
			if(stmt != null){
				try {
					stmt.close();
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
	}//end of main

}//End of Class