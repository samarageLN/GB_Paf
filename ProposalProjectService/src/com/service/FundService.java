package com.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import model.Fund;

/**
 * FundService
 * 
 ********************************************************************************************************
 *  ###   Date             Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   15-04-2021       David        Created
 *    
 ********************************************************************************************************
 */



@Path("/Funds")
public class FundService {

	Fund fund = new Fund();

	//*****************************************************Insert Fund Details*******************************************//
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFunds(@FormParam("proID") String proID, @FormParam("actualAmount") String actualAmount) {
		String output = fund.insertFunds(proID, actualAmount);
		return output;
	}
	
	//**************************************************************END***************************************************//

	//*****************************************************Get All Fund Details*******************************************//
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFunds() {
		return fund.readFunds();
	}

	//**************************************************************END***************************************************//
	
	//*****************************************Get Fund Details and Projects details By ID********************************//
	
	@GET
	@Path("/{ID}")
	@Produces(MediaType.TEXT_HTML)
	public String readFundById(@PathParam("ID") String id) {

		String output = fund.readFundById(id);
		return output;
	}
	
	//**************************************************************END***************************************************//

	//**********************Update Funds and if the actual payment received updating Project Status***********************//
	
	@PUT
	@Path("/{fundID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String fundData) {
		JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject();
		String fundID = fundObject.get("fundID").getAsString();
		String amount = fundObject.get("currentTotalFunds").getAsString();

		String output = fund.updatePayment(fundID, amount);
		return output;
	}
	
	//**************************************************************END***************************************************//	
		
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String sendProDetais(String projectData) {

		JsonObject proObj = new JsonParser().parse(projectData).getAsJsonObject();
		String ProID = proObj.get("projectID").getAsString();
		String Proname = proObj.get("projectname").getAsString();
		String amount = proObj.get("amount").getAsString();
		
		Client client = Client.create();
		String url = "http://localhost:8083/FeedBackService/FeedBack_Service/Feedbacks/feeds";
		WebResource resource = client.resource(url);
		String input = "{\"projectID\":\"" + ProID + "\",\"projectname\":\"" + Proname + " \",\"projectname\":\"" + amount + " \" }";
		ClientResponse response = resource.type("application/json").post(ClientResponse.class, input);
		String output = response.getEntity(String.class);

		return output;
	}

	
}
