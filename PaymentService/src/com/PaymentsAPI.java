package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Payment;

@Path("/Payments")
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
	@Path("/purchase")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String retrieveFromIProjects(String purchaseData) {
		// Convert the input string to a JSON object
		JsonObject purchasejson = new JsonParser().parse(purchaseData).getAsJsonObject();
				
		int cusId = purchasejson.get("customerid").getAsInt();
		String cusName = purchasejson.get("customerName").getAsString();
		String cusomerMail  = purchasejson.get("mail").getAsString();
		int projId = purchasejson.get("iProjectID").getAsInt();
		String projName = purchasejson.get("projectName").getAsString();
		double amount = purchasejson.get("projectPrice").getAsDouble();
		String output = payment.insertPaymentDetails(cusName,cusId,cusomerMail,projId,projName,"purchase",amount);
		return output;
	}
	
	//get details about donations -- for david
//	@POST
//	@Path("/donate")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.TEXT_PLAIN)
//	public String retrieveFromDonates(String paymentData) {
//		// Convert the input string to a JSON object
//		JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();	
//		String cardnumber = paymentObject.get("cardNumber").getAsString();
//		String output = payment.insertPaymentDetails(cardnumber);
//		return output;
//	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String payData) {

		JsonObject jsonObject = new JsonParser().parse(payData).getAsJsonObject();
		int paymentId = jsonObject.get("paymentId").getAsInt();
		String output = payment.removePayment(paymentId);
		return output;
	}
	
	
}
