package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.AdminAccount;


//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Admin_accounts")

public class AdminAccountService {

	
AdminAccount aaobj= new  AdminAccount();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAdminDetails()
	{
			return aaobj.readAdminDetails();
	}

	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAdminDetails(@FormParam("uname") String uname,@FormParam("password") String password,@FormParam("email") String email,@FormParam("age") String age,@FormParam("address") String address)
	{
		System.out.println("insert service method");
		String output = aaobj.insertAdminDetails(uname, password, email, age, address);
		return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAdminDetails(String admin_accountData)
	{
		//Convert the input string to a JSON object
		JsonObject jsonobj = new JsonParser().parse(admin_accountData).getAsJsonObject();
	
		//Read the values from the JSON object
		String uid = jsonobj.get("uid").getAsString();
		String uname = jsonobj.get("uname").getAsString();
		String password = jsonobj.get("password").getAsString();
		String email = jsonobj.get("email").getAsString();
		String age = jsonobj.get("age").getAsString();
		String address = jsonobj.get("address").getAsString();
		
	
		
		String output = aaobj.updateAdminDetails(uid, uname, password, email, age, address);
		return output;
	}
	
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAdminDetails(String admin_accountData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(admin_accountData, "", Parser.xmlParser());
	
	//Read the value from the element <uid>
	String uid = doc.select("uid").text();
	String output = aaobj.deleteAdminDetails(uid);
	return output;
	}
	
}
