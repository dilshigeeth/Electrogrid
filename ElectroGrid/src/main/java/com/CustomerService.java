package com;

import model.Customer;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Customers")

public class CustomerService {
	
	Customer customerObj = new Customer();
	
	//read
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCustomers()
	 {
		return customerObj.readCustomers(); 
	 }
	
	//insert
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomer(@FormParam("accountNo") String accountNo,
	 @FormParam("customerName") String customerName,
	 @FormParam("customerAddress") String customerAddress,
	 @FormParam("customerPhone") String customerPhone)
	{
	 String output = customerObj.insertCustomer(accountNo, customerName, customerAddress, customerPhone);
	return output;
	}
	
	//update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomer(String customerData)
	{
	//Convert the input string to a JSON object
	 JsonObject customerObject = new JsonParser().parse(customerData).getAsJsonObject();
	//Read the values from the JSON object
	 String customerID = customerObject.get("customerID").getAsString();
	 String accountNo = customerObject.get("accountNo").getAsString();
	 String customerName = customerObject.get("customerName").getAsString();
	 String customerAddress = customerObject.get("customerAddress").getAsString();
	 String customerPhone = customerObject.get("customerPhone").getAsString();
	 String output = customerObj.updateCustomer(customerID, accountNo, customerName, customerAddress, customerPhone);
	return output;
	}
	
	//delete
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(String customerData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(customerData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String customerID = doc.select("customerID").text();
	 String output = customerObj.deleteCustomer(customerID);
	return output;
	}


}
