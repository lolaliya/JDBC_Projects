package com.caps.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class SearchUser 
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		System.out.println("Enter the user id");
		Scanner in = new Scanner(System.in);
		int uid = Integer.parseInt(in.nextLine());
		in.close();

		Connection con = null;
		PreparedStatement pstmt = null;
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
			String sql = "select * from users_info where user_id = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, uid);

			rs = pstmt.executeQuery();

			/*
			 * 4. Process the results
			 */

			if(rs.next()){
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
			else
			{
				System.out.println("Data not found");
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
			if(pstmt != null){
				try {
					pstmt.close();
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
