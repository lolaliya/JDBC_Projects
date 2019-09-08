package com.user.batchexecution;


import java.sql.*;
import java.util.Scanner;
import java.io.*;  
public class ProfileBatchExecution
{
	public static void main(String args[])
	{
		
		// batched execution for create or insert user data
		
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs= null;
		try{  

			Class.forName("com.mysql.cj.jdbc.Driver");  
			String dbUrl="jdbc:mysql://localhost:3306/cleveridiots_db?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);
			pstmt=con.prepareStatement("insert into users_info values(?,?,?,?)");  

			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
			while(true){  

				System.out.println("Enter the user id");
				Scanner in = new Scanner(System.in);
				int uid = Integer.parseInt(in.nextLine());
				System.out.println("Enter the first name");
				String fname = in.nextLine();
				System.out.println("Enter the last name");
				String lname = in.nextLine();
				System.out.println("Enter the password");
				String passwd = in.nextLine();

				String sql = "Insert into users_info values(?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, uid);
				pstmt.setString(2, fname);
				pstmt.setString(3, lname);
				pstmt.setString(4, passwd);
				int count = pstmt.executeUpdate();


				pstmt.addBatch();  
				System.out.println("Want to add more records y/n");  
				String ans=br.readLine();  
				if(ans.equals("n"))
				{  
					System.out.println("record successfully saved");
					break;  
				}  
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			// 5. close all the JDBC objects
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
		
		
		
		//batched execution for delete user data
		
		

	}  

}