//package com;
//
//import model.Item;
////For REST Service
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
////For JSON
//import com.google.gson.*;
////For XML
//import org.jsoup.*;
//import org.jsoup.parser.*;
//import org.jsoup.nodes.Document;
//@Path("/Items") 
//
//public class ItemService {
//
//	Item itemObj = new Item();
////	@GET
////	@Path("/")
////	@Produces(MediaType.TEXT_HTML)
////	public String readItems()
////	 {
////	 return "Hello";
////	 } 
//	
//	@GET
//	@Path("/")
//	@Produces(MediaType.TEXT_HTML)
//	public String readItems()
//	 {
//	 return itemObj.readItems();
//	}
//
//	@POST
//	@Path("/")
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.TEXT_PLAIN)
//	public String insertItem(@FormParam("itemCode") String itemCode,
//	 @FormParam("itemName") String itemName,
//	 @FormParam("itemPrice") String itemPrice,
//	 @FormParam("itemDesc") String itemDesc)
//	{
//	 String output = itemObj.insertItem(itemCode, itemName, itemPrice, itemDesc);
//	return output;
//	}
//	
//	
//	@PUT
//	@Path("/")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.TEXT_PLAIN)
//	public String updateItem(String itemData)
//	{
//	//Convert the input string to a JSON object
//	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
//	//Read the values from the JSON object
//	 String itemID = itemObject.get("itemID").getAsString();
//	 String itemCode = itemObject.get("itemCode").getAsString();
//	 String itemName = itemObject.get("itemName").getAsString();
//	 String itemPrice = itemObject.get("itemPrice").getAsString();
//	 String itemDesc = itemObject.get("itemDesc").getAsString();
//	 String output = itemObj.updateItem(itemID, itemCode, itemName, itemPrice, itemDesc);
//	return output;
//	}
//	
//	
//	@DELETE
//	@Path("/")
//	@Consumes(MediaType.APPLICATION_XML)
//	@Produces(MediaType.TEXT_PLAIN)
//	public String deleteItem(String itemData)
//	{
//	//Convert the input string to an XML document
//	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
//
//	//Read the value from the element <itemID>
//	 String itemID = doc.select("itemID").text();
//	 String output = itemObj.deleteItem(itemID);
//	return output;
//	}
//
//}













package com;

import model.Item;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Items")
public class ItemService
{
	Item noticeObj = new Item();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readNotices()
	{
	 return noticeObj.readNotices();
	}
	
	@GET
	@Path("/{regionID}")
	@Produces(MediaType.TEXT_PLAIN)
	public String readNoticesInRegion(@PathParam("regionID") String regionID)
	{
		return noticeObj.readNoticesInRegion(regionID); 
	}
	
		
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertNotice(@FormParam("regionID") String regionID,
	 @FormParam("group") String group,
	 @FormParam("Description") String Description,
	 @FormParam("outageStartTime") String outageStartTime,
	 @FormParam("outageEndTime") String outageEndTime,
     @FormParam("informerID") String informerID)
	{
	 String output = noticeObj.insertNotice(regionID, group, Description, outageStartTime, outageEndTime, informerID);
	 return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateNotice(String noticeData)
	{
		//Convert the input string to a JSON object
		JsonObject noticeObject = new JsonParser().parse(noticeData).getAsJsonObject();
		//Read the values from the JSON object
		String noticeID = noticeObject.get("noticeID").getAsString();
		String regionID = noticeObject.get("regionID").getAsString();
		String group = noticeObject.get("group").getAsString();
		String Description = noticeObject.get("Description").getAsString();
		String outageStartTime = noticeObject.get("outageStartTime").getAsString();
		String outageEndTime = noticeObject.get("outageEndTime").getAsString();
		String informerID = noticeObject.get("informerID").getAsString();
		
		String output = noticeObj.updateNotice(noticeID, regionID, group, Description, outageStartTime, outageEndTime, informerID);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteNotice(String noticeData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(noticeData, "", Parser.xmlParser());

		//Read the value from the element <itemID>
		String noticeID = doc.select("noticeID").text();
		String output = noticeObj.deleteNotice(noticeID);
		return output;
	}

}




