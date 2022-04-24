package com;

import model.Bill;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Bills")
public class BillService {
	Bill billObj = new Bill();
	
	//read
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBills()
	 {
		return billObj.readBills();
	 }
	
	//insert
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBill(@FormParam("billNo") String billNo,
	 @FormParam("billName") String billName,
	 @FormParam("billPrice") String billPrice,
	 @FormParam("billMonth") String billMonth)
	{
	 String output = billObj.insertBill(billNo, billName, billPrice, billMonth);
	return output;
	}
	
	//update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBill(String billData)
	{
	//Convert the input string to a JSON object
	 JsonObject billObject = new JsonParser().parse(billData).getAsJsonObject();
	//Read the values from the JSON object
	 String billID = billObject.get("billID").getAsString();
	 String billNo = billObject.get("billNo").getAsString();
	 String billName = billObject.get("billName").getAsString();
	 String billPrice = billObject.get("billPrice").getAsString();
	 String billMonth = billObject.get("billMonth").getAsString();
	 String output = billObj.updateBill(billID, billNo, billName, billPrice, billMonth);
	return output;
	}
	
	//delete
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(String billData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(billData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String billID = doc.select("billID").text();
	 String output = billObj.deleteBill(billID);
	return output;
	}

}
