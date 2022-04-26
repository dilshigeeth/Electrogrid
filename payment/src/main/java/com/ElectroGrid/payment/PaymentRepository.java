package com.ElectroGrid.payment;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.sql.PreparedStatement;

public class PaymentRepository 
{
	//List<Payment> Payments;
	
	Connection con = null;
	
	public PaymentRepository() 
	{
		/*Payments = new ArrayList<>();
		Payment p1 = new Payment();
		p1.setId(11);
		p1.setName("Kamal");
		p1.setAccNo(987876767);
		p1.setDueDate("30/04/2022");
		p1.setAmount("2000.00");
		
		Payment p2 = new Payment();
		p2.setId(12);
		p2.setName("Samal");
		p2.setAccNo(887876767);
		p2.setDueDate("29/04/2022");
		p2.setAmount("3000.00");
		
		Payments.add(p1);
		Payments.add(p2);*/
		
		
		String url = "jdbc:mysql://localhost:3306/electrogrid";
		//String driverName = "com.mysql.jdbc.Driver";
		String username = "root";
		String password = "achala";
		
		try 
		{
			  
			  Class.forName("com.mysql.jdbc.Driver");
			  con=DriverManager.getConnection(url,username,password);
			  
		  }
		catch(Exception e) {
			  System.out.print(e);
		  }
		
		 
		
	}
	
	public List<Payment> getPayments()
	{
		List<Payment> Payments = new ArrayList<>();
		String sql = "select * from payment";
		try {
			
			Statement st=con.createStatement();
			ResultSet rs= st.executeQuery(sql);
			while(rs.next()) {
				
				Payment p=new Payment();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setAccNo(rs.getInt(3));;
				p.setDueDate(rs.getString(4));
				p.setAmount(rs.getString(5));
				
				
				Payments.add(p);
			}
			
		}catch(Exception e) {
			System.out.print(e);
		}
		
		
		return Payments;
	}
	
	public Payment getPayment(int id) 
	{
		String sql = "select * from payment where id="+id;
		Payment p=new Payment();
		try {
			
			Statement st=con.createStatement();
			ResultSet rs= st.executeQuery(sql);
			if(rs.next()) {
				
				
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setAccNo(rs.getInt(3));;
				p.setDueDate(rs.getString(4));
				p.setAmount(rs.getString(5));
				
				
				
			}
			
		}catch(Exception e) {
			System.out.print(e);
		}
		return p;
		
		/*for(Payment p : Payments)
		{
			if(p.getId()==id)
				return p;
		}
		return new Payment();*/
		
		
		
	}


	public void create(Payment p1) {
		String sql = "insert into payment values(?,?,?,?,?)";
		
try {
			
	PreparedStatement st=con.prepareStatement(sql);
			
	st.setInt(1,p1.getId());
	st.setString(2, p1.getName());
	st.setInt(3, p1.getAccNo());
	st.setString(4, p1.getDueDate());
	st.setString(5,p1.getAmount());
	st.executeUpdate();
	
			
		}catch(Exception e) {
			System.out.print(e);
		}
		
	}
	
	public void update(Payment p1) {
		String sql = "update payment set name =?,accNo=?,dueDate =?,amount=?  where id=? ";
		
try {
			
	PreparedStatement st=con.prepareStatement(sql);
			
	st.setString(1, p1.getName());
	st.setInt(2,p1.getAccNo());
	st.setString(3,p1.getDueDate());
	st.setString(4, p1.getAmount());
	st.setInt(5, p1.getId());
	
	st.executeUpdate();
	
			
		}catch(Exception e) {
			System.out.print(e);
		}
		
	}
	
	public void delete(int id) {
		String sql = "delete from payment where id=? ";
		try {
					PreparedStatement st=con.prepareStatement(sql);
					st.setInt(1,id);
					st.executeUpdate();
					
				}catch(Exception e) {
					System.out.print(e);
				}	
	}

	
	
	
	

}