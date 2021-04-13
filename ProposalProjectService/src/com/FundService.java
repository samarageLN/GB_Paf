package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Fund;

@Path("/Funds")
public class FundService {
	
	Fund fund = new Fund();

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFunds(@FormParam("proID") String proID,
			 @FormParam("actualAmount") String actualAmount
			 )
			{ 
		String output = fund.insertFunds(proID, actualAmount); 
		return output;
			}
	
	
	
}
