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

import model.Mail;

@Path("email")
public class MailApi {

	Mail mail = new Mail();
	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String readMails() {		
		return mail.readAllMailDetails();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertMailDetails(String maildata) {
		
		JsonObject jsonObject = new JsonParser().parse(maildata).getAsJsonObject();	
		String recepient = jsonObject.get("recepient").getAsString();
		int refNumber  = jsonObject.get("refNumber").getAsInt();
		String output = null;
		try {
			output = mail.sendDefaultMail(recepient,refNumber);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return output;
	}
	
	
	@POST
	@Path("/custom")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomMailDetails(String maildata) {
		
		JsonObject jsonObject = new JsonParser().parse(maildata).getAsJsonObject();	
		String recepient = jsonObject.get("recepient").getAsString();
		String subject = jsonObject.get("subject").getAsString();
		String messageBody = jsonObject.get("messageBody").getAsString();
		String output = null;
		try {
			output = mail.sendCustomMail(recepient, subject, messageBody);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String maildata) {

		JsonObject jsonObject = new JsonParser().parse(maildata).getAsJsonObject();
		int mailId = jsonObject.get("mailId").getAsInt();
		String output = mail.removeEmail(mailId);
		return output;
	}
	
}
