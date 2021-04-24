package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import model.Login;


@Path("/User_logins")
public class LoginService {

	Login lobj= new  Login();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String validate_login(@FormParam("uname") String uname,@FormParam("password") String password)
	{
		
		boolean output = lobj.validate_login(uname, password);
		if(output) {
			return "Login success";
		}
		else {
			return "Login Failed";
		}
		
	}
	
	
	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String logout(@FormParam("uname") String uname)
	{
		
		String output = lobj.logoutTheuser(uname);
		return output;
		
	}
	
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String readUserDetails()
	{
			return lobj.getLoggedUserInfo();
	}
	
	
	
	
	
}
