package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.InnovativeProject;

@Path("/InnovativeProjects")
public class InnovativeProjectAPI {

	// implement the read all InnovativeProjects method

	InnovativeProject ipObj = new InnovativeProject();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readIProjects() {

		return ipObj.readInnovativeProjects();
	}

	// implement the read one particular InnovativeProject method

	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String readOneIProject(String iprojectIDJSON) {

		// create a JSON Object

		JsonObject innovativeProjectObject = new JsonParser().parse(iprojectIDJSON).getAsJsonObject();

		int innovativeProjectID = innovativeProjectObject.get("innovativeProjectID").getAsInt();
		return ipObj.readOneInnovativeProject(innovativeProjectID);
	}

	// implement the innovative project upload method

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String uploadProjects(String ProjectDetails) {

		// create a JSON Object

		JsonObject innovativeProjectObject = new JsonParser().parse(ProjectDetails).getAsJsonObject();

		String projectname = innovativeProjectObject.get("projectName").getAsString();
		double projectprice = innovativeProjectObject.get("projectPrice").getAsDouble();
		String imageurl = innovativeProjectObject.get("imageURL").getAsString();
		String project_type = innovativeProjectObject.get("projectType").getAsString();
		String projdesc = innovativeProjectObject.get("projectDescription").getAsString();
		int researchid = innovativeProjectObject.get("researcherID").getAsInt();

		String output = ipObj.uploadProject(projectname, Double.toString(projectprice), imageurl, project_type,
				projdesc, researchid);

		return output;

	}

	// implement the InnovativeProjects remove method

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteIProjects(String iprojectIDJSON) {

		JsonObject innovativeProjectObject = new JsonParser().parse(iprojectIDJSON).getAsJsonObject();

		int innovativeProjectID = innovativeProjectObject.get("innovativeProjectID").getAsInt();
		String output = ipObj.removeInnnovativeProjects(innovativeProjectID);
		return output;
	}

	// implement the InnovativeProject update method

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateIProjects(String ProjectDetails) {

		JsonObject innovativeProjectObject = new JsonParser().parse(ProjectDetails).getAsJsonObject();

		String projectid = innovativeProjectObject.get("innovativeProjectID").getAsString();
		String projectname = innovativeProjectObject.get("projectName").getAsString();
		String projectprice = innovativeProjectObject.get("projectPrice").getAsString();
		String imageurl = innovativeProjectObject.get("imageURL").getAsString();
		String project_type = innovativeProjectObject.get("projectType").getAsString();
		String projdesc = innovativeProjectObject.get("projectDescription").getAsString();
		String researchid = innovativeProjectObject.get("researcherID").getAsString();

		String output = ipObj.updateInnovativeProject(projectid, projectname, Double.parseDouble(projectprice),
				imageurl, project_type, projdesc, Integer.parseInt(researchid));
		return output;

	}

}
