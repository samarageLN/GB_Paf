package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Payment;

@Path("Payments")
public class PaymentsAPI {

	Payment payment = new Payment();
	
	
	//get all the payment details
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String readItems() {		
		return payment.readAllPaymentDetails();
	}
	
	//to get payment details of a specific user 
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String readItems(String userIdjson) {

		JsonObject jsonObj = new JsonParser().parse(userIdjson).getAsJsonObject();

		int userId = jsonObj.get("userId").getAsInt();
		return payment.readPaymentDetails(userId);
	}

	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCardDetails(String paymentData) {
		// Convert the input string to a JSON object
		JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();	
		String cardnumber = paymentObject.get("cardNumber").getAsString();
		String output = payment.insertPaymentDetails(cardnumber);
		return output;
	}
}
