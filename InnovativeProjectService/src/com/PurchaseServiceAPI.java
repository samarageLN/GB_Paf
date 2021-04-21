package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import model.Purchase;

@Path("/Purchase")
public class PurchaseServiceAPI {
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String proceedToPayment(String ProjectDetails) {

		// create a JSON Object

		JsonObject innovativeProjectObject = new JsonParser().parse(ProjectDetails).getAsJsonObject();

		int iProjID = innovativeProjectObject.get("innovativeProjectID").getAsInt();
		String projectname = innovativeProjectObject.get("projectName").getAsString();
		double projectprice = innovativeProjectObject.get("projectPrice").getAsDouble();
		int customerID = innovativeProjectObject.get("customerid").getAsInt();

		// communicate with Payment Service.send order project details to payment.

		Client client = Client.create();
		String url = "http://localhost:8083/PaymentService/Payment_Service/Payments/purchase";
		WebResource resource = client.resource(url);
		String input = "{\"iProjectID\":\"" + iProjID + "\",\"projectName\":\"" + projectname + "\",\"projectPrice\":\""
				+ projectprice + "\",\"customerid\":\"" + customerID + "\"}";
		ClientResponse response = resource.type("application/json").post(ClientResponse.class, input);
		String output = response.getEntity(String.class);

		return output;

	}

	// this method will be call by informProjectService method of payment service

	@POST
	@Path("/confirm")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String getPurcchaseDetails(String PurchaseDetails) {

		// create a JSON Object

		JsonObject innovativeProjectObject = new JsonParser().parse(PurchaseDetails).getAsJsonObject();

		Purchase purchaseObj = new Purchase();

		int iProjID = innovativeProjectObject.get("innovativeProjectID").getAsInt();
		int customerID = innovativeProjectObject.get("customerID").getAsInt();
		String pDate = innovativeProjectObject.get("date").getAsString();
		String pTime = innovativeProjectObject.get("time").getAsString();

		String output = purchaseObj.insertPurchase(iProjID, customerID, pDate, pTime);

		return output;

	}

}
