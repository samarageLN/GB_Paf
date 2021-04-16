package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFunds(@FormParam("proID") String proID, @FormParam("actualAmount") String actualAmount) {
		String output = fund.insertFunds(proID, actualAmount);
		return output;
	}

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFunds() {
		return fund.readFunds();
	}

	@GET
	@Path("/{ID}")
	@Produces(MediaType.TEXT_HTML)
	public String readFundById(@PathParam("ID") String id) {

		String output = fund.readFundById(id);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String fundData) {
		JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject();
		String fundID = fundObject.get("fundID").getAsString();
		String amount = fundObject.get("currentTotalFunds").getAsString();

		String output = fund.updatePayment(fundID, amount);
		return output;
	}
		
}
