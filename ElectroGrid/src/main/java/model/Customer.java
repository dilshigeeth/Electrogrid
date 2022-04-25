package model;

import java.sql.*;

public class Customer {
	
	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	public String insertCustomer(String no, String name, String address, String phone)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into customers(`customerID`,`accountNo`,`customerName`,`customerAddress`,`customerPhone`)" + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, no);
	 preparedStmt.setString(3, name);
	 preparedStmt.setString(4, address);
	 preparedStmt.setString(5, phone);
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the customer.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String readCustomers()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Account No</th><th>Customer Name</th>" + "<th>Customer Address</th>" + "<th>Customer Phone</th>" + "<th>Update</th><th>Remove</th></tr>";
	 String query = "select * from customers";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String customerID = Integer.toString(rs.getInt("customerID"));
	 String accountNo = rs.getString("accountNo");
	 String customerName = rs.getString("customerName");
	 String customerAddress = rs.getString("customerAddress");
	 String customerPhone = rs.getString("customerPhone");
	 // Add into the html table
	 output += "<tr><td>" + accountNo + "</td>";
	 output += "<td>" + customerName + "</td>";
	 output += "<td>" + customerAddress + "</td>";
	 output += "<td>" + customerPhone + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='customers.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" + "<input name='customerID' type='hidden' value='" + customerID + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the customers.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String updateCustomer(String ID, String no, String name, String address, String phone)
	{
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE customers SET accountNo=?,customerName=?,customerAddress=?,customerPhone=? WHERE customerID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, no);
		 preparedStmt.setString(2, name);
		 preparedStmt.setString(3, address);
		 preparedStmt.setString(4, phone);
		 preparedStmt.setInt(5, Integer.parseInt(ID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String deleteCustomer(String customerID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from customers where customerID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(customerID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the customer.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }

}
