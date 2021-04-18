package com;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.UserCard;

@Path("UserCards")
public class UserCardAPI {

	UserCard userCard = new UserCard();

	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String readItems(String userIdjson) {

		JsonObject cardObject1 = new JsonParser().parse(userIdjson).getAsJsonObject();

		int userId = cardObject1.get("userId").getAsInt();
		return userCard.readCardDetails(userId);
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCardDetails(String cardData) {
		// Convert the input string to a JSON object
		JsonObject cardObject = new JsonParser().parse(cardData).getAsJsonObject();

		// Read the values from the JSON object

		int userId = cardObject.get("userId").getAsInt();
		String nameOnCard = cardObject.get("nameOnCard").getAsString();
		String cardNumber = cardObject.get("cardNumber").getAsString();
		String expDate = cardObject.get("expiredate").getAsString();
		int secCode = cardObject.get("securityCode").getAsInt();
		int postalCode = cardObject.get("postalCode").getAsInt();

		// System.out.println(userId + nameOnCard + cardNumber + expDate + secCode +
		// postalCode);

		String output = userCard.insertCardDetails(userId, nameOnCard, cardNumber, expDate, secCode, postalCode);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String cardData) {

		JsonObject cardObject = new JsonParser().parse(cardData).getAsJsonObject();
		int userId = cardObject.get("userId").getAsInt();
		String output = userCard.removeCard(userId);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCardDetails(String itemData) {

		JsonObject cardObject = new JsonParser().parse(itemData).getAsJsonObject();
		// Read the values from the JSON object
		int userId = cardObject.get("userId").getAsInt();
		String nameOnCard = cardObject.get("nameOnCard").getAsString();
		String cardNumber = cardObject.get("cardNumber").getAsString();
		String expDate = cardObject.get("expiredate").getAsString();
		int secCode = cardObject.get("securityCode").getAsInt();
		int postalCode = cardObject.get("postalCode").getAsInt();
		String output = userCard.updateCardDetails(userId, nameOnCard, cardNumber, expDate, secCode, postalCode);
		return output;
	}

}
