package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import model.AdminLogin;
import model.Login;


@Path("/Admin_logins")

public class AdminLoginService {
AdminLogin alobj= new  AdminLogin();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String validate_Adminlogin(@FormParam("uname") String uname,@FormParam("password") String password)
	{
		
		boolean output = alobj.validate_Adminlogin(uname, password);
		if(output) {
			return "Login success";
		}
		else {
			return "Login Failed";
		}
		
	}

}
