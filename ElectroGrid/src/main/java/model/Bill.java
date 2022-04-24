package model;

import java.sql.*; 


public class Bill {
	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogrid", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	public String insertBill(String no, String name, String price, String month)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into bills (`billID`,`billNo`,`billName`,`billPrice`,`billMonth`)" + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, no);
	 preparedStmt.setString(3, name);
	 preparedStmt.setDouble(4, Double.parseDouble(price));
	 preparedStmt.setString(5, month);
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the bill.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String readBills()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Bill No</th><th>Bill Name</th>" + "<th>Bill Price</th>" + "<th>Bill Month</th>" + "<th>Update</th><th>Remove</th></tr>";
	 String query = "select * from bills";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String billID = Integer.toString(rs.getInt("billID"));
	 String billNo = rs.getString("billNo");
	 String billName = rs.getString("billName");
	 String billPrice = Double.toString(rs.getDouble("billPrice"));
	 String billMonth = rs.getString("billMonth");
	 // Add into the html table
	 output += "<tr><td>" + billNo + "</td>";
	 output += "<td>" + billName + "</td>";
	 output += "<td>" + billPrice + "</td>";
	 output += "<td>" + billMonth + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='bills.jsp'>" + "<input name='btnRemove' type='submit' value='Remove'	 class='btn btn-danger'>"+ "<input name='itemID' type='hidden' value='" + billID+ "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the bills.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String updateBill(String ID, String no, String name, String price, String month) 
	{
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE bills SET billNo=?,billName=?,billPrice=?,billMonth=? WHERE billID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, no);
		 preparedStmt.setString(2, name);
		 preparedStmt.setDouble(3, Double.parseDouble(price));
		 preparedStmt.setString(4, month);
		 preparedStmt.setInt(5, Integer.parseInt(ID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the bill.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String deleteBill(String billID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from bills where billID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(billID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the bill.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
}
