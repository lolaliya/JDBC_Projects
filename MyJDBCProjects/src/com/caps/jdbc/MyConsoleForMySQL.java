package com.caps.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MyConsoleForMySQL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			/*
			 * 
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
			Scanner in = new Scanner(System.in);
			System.out.println("MYSQL>");
			String sql = in.nextLine();
			stmt = con.prepareCall(sql);
			in.close();

			boolean state = stmt.execute(sql);
			/*
			 * 4. Process the results
			 */
			if(state)
			{

				rs= stmt.getResultSet();
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

				int count = stmt.getUpdateCount();
				System.out.println("query ok "+count+" rows effected" );
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
	}

}
