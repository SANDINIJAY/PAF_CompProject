package model;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
//comment
//commect2
public class Productman {
	//A common method to connect to the DB
		private Connection connect()
		{
		Connection con = null;
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		//Provide the correct details: DBServer/DBName, username, password
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/productman", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
		}
		public String insertProductman(String ProID, String ProName, String ProGrade, String ProPrice)
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for inserting."; }
		// create a prepared statement
		String query = " INSERT INTO `productman`(`ID`, `ProID`, `ProName`, `ProGrade`, `ProPrice`)"
		+ " values (?, ?, ?, ?, ?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, ProID);
		preparedStmt.setString(3, ProName);
		preparedStmt.setString(4, ProGrade);
		preparedStmt.setString(5, ProPrice);
		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Inserted successfully";
		}
		catch (Exception e)
		{
		output = "Error while inserting the productman.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		public String readProductman()
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for reading."; }
		// Prepare the html table to be displayed
		output = "<table border='1'><tr><th>Product ID</th>" +
		"<th>Product Name</th>" +
		"<th>Product Category</th>"+
		"<th>Product Price</th>";
		
		String query = "SELECT * FROM productman";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		// iterate through the rows in the result set
		while (rs.next())
		{
		String ID = Integer.toString(rs.getInt("ID"));
		String ProID = rs.getString("ProID");
		String ProName = rs.getString("ProName");
		String ProGrade = rs.getString("ProGrade");
		String ProPrice = rs.getString("ProPrice");
		// Add into the html table
		output += "<tr><td>" + ProID + "</td>";
		output += "<td>" + ProName + "</td>";
		output += "<td>" + ProGrade + "</td>";
		output += "<td>" + ProPrice + "</td>";
		
		}
		con.close();
		// Complete the html table
		output += "</table>";
		}
		catch (Exception e)
		{
		output = "Error while reading the productman.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		public String updateProductman(String ID,String ProID, String ProName, String ProGrade, String ProPrice)
		{
			String output = "";
			try
			{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			// create a prepared statement
			String query = "UPDATE productman SET ProID=?,ProName=?,ProGrade=?,ProPrice=? WHERE ID=? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, ProID);
			preparedStmt.setString(2, ProName);
			preparedStmt.setString(3, ProGrade);
			preparedStmt.setString(4, ProPrice);
			preparedStmt.setInt(5, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
			}
			catch (Exception e)
			{
			output = "Error while updating the productman.";
			System.err.println(e.getMessage());
			}
			return output;
			}
		
		
			public String deleteProductman(String ID)
			{
			String output = "";
			try
			{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			// create a prepared statement
			String query = "delete from productman where ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
			}
			catch (Exception e)
			{
			output = "Error while deleting the productman.";
			System.err.println(e.getMessage());
			}
			return output;
			}

}