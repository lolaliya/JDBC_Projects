package com.caps.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
public class DeleteProfile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter the user id");
		Scanner in = new Scanner(System.in);
		int uid = Integer.parseInt(in.nextLine());
;
		System.out.println("Enter the password");
		String passwd = in.nextLine();

		Connection con = null;
		PreparedStatement  pstmt = null;
		try
		{
			//1. load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			//2. get the db connection
			String dbUrl="jdbc:mysql://localhost:3306/cleveridiots_db";
			con = DriverManager.getConnection(dbUrl,"root","root");
			//3. Issue the SQL query via connection
			String sql = "delete from users_info where user_id = ? and password = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setString(2, passwd);
			int count = pstmt.executeUpdate();


			//4.Process the output obtained by SQL Query
			if(count>0) 
			{
				System.out.println("Profile Deleted");
			}
			else 
			{
				System.out.println("Profile deletion failed");
			}
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}

		finally
		{
			if(con != null ) {
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		in.close();
	}
}
