package com;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.InnovativeProject;

@Path("/InnovativeProjects")
public class InnovativeProjectAPI {
	
	
	InnovativeProject ipObj = new InnovativeProject();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String uploadProjects(String ProjectDetails) {
		
        JsonObject innovativeProjectObject = new JsonParser().parse(ProjectDetails).getAsJsonObject(); 
		
		
		
		String projectname = innovativeProjectObject.get("projectName").getAsString();
		double projectprice = innovativeProjectObject.get("projectPrice").getAsDouble();
		String imageurl = innovativeProjectObject.get("imageURL").getAsString();
		String project_type = innovativeProjectObject.get("projectType").getAsString();
		String projdesc = innovativeProjectObject.get("projectDescription").getAsString();
		int researchid = innovativeProjectObject.get("researcherID").getAsInt();
		
		
		String output = ipObj.uploadProject(projectname, Double.toString(projectprice), imageurl,project_type, projdesc,researchid);
				
	   	return output;
		
		
	}

}
