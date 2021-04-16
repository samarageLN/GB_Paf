package com;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.UserCard;


@Path("UserCards")
public class UserCardAPI {

	UserCard useCard = new UserCard();
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCardDetails(String cardData)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(cardData).getAsJsonObject();
	
	 //Read the values from the JSON object
	 
	 int userId = itemObject.get("userId").getAsInt();      
	 String nameOnCard = itemObject.get("nameOnCard").getAsString();
	 String cardNumber = itemObject.get("cardNumber").getAsString();
	 String expDate = itemObject.get("expiredate").getAsString();
	 int secCode = itemObject.get("securityCode").getAsInt();
	 int postalCode = itemObject.get("postalCode").getAsInt();
	 
	 System.out.println(userId+nameOnCard+cardNumber+expDate+secCode+postalCode);
	 
	 String output = useCard.insertCardDetails(userId, nameOnCard, cardNumber, expDate, secCode, postalCode);
	 return output;
	}	
}
