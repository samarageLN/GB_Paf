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
 Item itemObj = new Item(); 
@GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readItems() 
 { 
	return itemObj.readItems(); 
 } 


@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertItem(@FormParam("Catagory") String Catagory, @FormParam("Starting_Date") String Starting_Date , @FormParam("Closing_Date") String Closing_Date) 
{ 
 String output = itemObj.insertItem(Catagory, Starting_Date, Closing_Date ); 
return output; 
}



@PUT
@Path("/") 
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN) 
public String updateItem(String itemData) 
{ 
//Convert the input string to a JSON object 
 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
//Read the values from the JSON object
 String EventID = itemObject.get("EventID").getAsString(); 
 String Catagory = itemObject.get("Catagory").getAsString(); 
 String Starting_Date = itemObject.get("Starting_Date").getAsString();
 String Closing_Date = itemObject.get("Closing_Date").getAsString();
 String output = itemObj.updateItem(EventID, Catagory, Starting_Date,Closing_Date ); 
return output; 
}



@DELETE
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deleteItem(String itemData) 
{ 
//Convert the input string to an XML document
 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
 
//Read the value from the element <itemID>
 String EventID = doc.select("EventID").text(); 
 String output = itemObj.deleteItem(EventID); 
return output; 
}


}