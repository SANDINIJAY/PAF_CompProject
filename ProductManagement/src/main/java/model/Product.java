package model;

import java.sql.*;


public class Product {
	//A common method to connect to the DB
		private Connection connect()
		{
		Connection con = null;
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		//Provide the correct details: DBServer/DBName, username, password
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/product", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
		}
		public String insertProduct(String ProID, String ProName, String ProGrade, String ProPrice)
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for inserting."; }
		// create a prepared statement
		String query = " insert into product (`ID`, `ProID`, `ProName`, `ProGrade`, `ProPrice`)"
		+ " values (?, ?, ?, ?, ?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, ProID);
		preparedStmt.setString(3, ProName);		
		preparedStmt.setString(4,ProGrade);
		preparedStmt.setDouble(5,  Double.parseDouble(ProPrice));
		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Inserted successfully";
		}
		catch (Exception e)
		{
		output = "Error while inserting the product.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		public String readProduct()
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for reading."; }
		// Prepare the html table to be displayed
		output = "<table border='1'><tr>"+
		"<th>ID</th>" +
		" <th>Product ID</th>" +
		"<th>Product Name</th>" +
		"<th>Product Category</th>"+
		"<th>Product Price</th>"+
		"<th>Update</th><th>Remove</th></tr>"; 
		
		String query = "select * from product";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		// iterate through the rows in the result set
		while (rs.next())
		{
		String ID = Integer.toString(rs.getInt("ID"));
		String ProID = rs.getString("ProID");
		String ProName = rs.getString("ProName");
		String ProGrade = rs.getString("ProGrade");
		String ProPrice =  Double.toString(rs.getDouble("ProPrice"));
		// Add into the html table
		output += "<tr><td>" + ID + "</td>";
		output +="<td> " + ProID + "</td>";
		output += "<td>" + ProName + "</td>";
		output += "<td>" + ProGrade + "</td>";
		output += "<td>" + ProPrice + "</td>";
		
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
				 + "<td><button class='btnRemove btn btn-danger' name='btnRemove' id ='btnRemove' value='"+ ID +"' >Remove</button></td></tr>";
		 
		
		}
		con.close();
		// Complete the html table
		output += "</table>";
		}
		catch (Exception e)
		{
		output = "Error while reading the product.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		public String updateProduct(String ID,String ProID, String ProName, String ProGrade, String ProPrice)
		{
			String output = "";
			try
			{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			// create a prepared statement
			String query = "UPDATE product SET ProID=?,ProName=?,ProGrade=?,ProPrice=? WHERE ID=? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, ProID);
			preparedStmt.setString(2, ProName);
			preparedStmt.setString(3, ProGrade);
			preparedStmt.setDouble(4, Double.parseDouble(ProPrice)); 
			preparedStmt.setInt(5, Integer.parseInt(ID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
			}
			catch (Exception e)
			{
			output = "Error while updating the product.";
			System.err.println(e.getMessage());
			}
			return output;
			}
		
		
			public String deleteProduct(String ID)
			{
			String output = "";
			try
			{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			// create a prepared statement
			String query = "delete from product where ID=?";
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
			output = "Error while deleting the product.";
			System.err.println(e.getMessage());
			}
			return output;
			}

}