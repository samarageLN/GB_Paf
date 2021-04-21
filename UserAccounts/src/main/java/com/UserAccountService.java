package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.UserAccount;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/User_accounts")
public class UserAccountService {
	
	UserAccount uaobj= new  UserAccount();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUserDetails()
	{
			return uaobj.readUserDetails();
	}

	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUserDetails(@FormParam("uname") String uname,@FormParam("password") String password,@FormParam("email") String email,@FormParam("age") String age,@FormParam("address") String address,@FormParam("type") String type)
	{
		System.out.println("insert service method");
		String output = uaobj.insertUserDetails(uname, password, email, age, address,type);
		return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUserDetails(String user_accountData)
	{
		//Convert the input string to a JSON object
		JsonObject jsonobj = new JsonParser().parse(user_accountData).getAsJsonObject();
	
		//Read the values from the JSON object
		String uid = jsonobj.get("uid").getAsString();
		String uname = jsonobj.get("uname").getAsString();
		String password = jsonobj.get("password").getAsString();
		String email = jsonobj.get("email").getAsString();
		String age = jsonobj.get("age").getAsString();
		String address = jsonobj.get("address").getAsString();
		String type = jsonobj.get("type").getAsString();
	
		
		String output = uaobj.updateUserDetails(uid, uname, password, email, age, address,type);
		return output;
	}
	
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUserDetails(String user_accountData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(user_accountData, "", Parser.xmlParser());
	
	//Read the value from the element <uid>
	String uid = doc.select("uid").text();
	String output = uaobj.deleteUserDetails(uid);
	return output;
	}
	
	
}
