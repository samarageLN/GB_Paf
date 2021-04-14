package com;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import model.Fund;
import model.ProposalProject;

@Path("/Project")

public class ProposalProjectService {

	ProposalProject pro = new ProposalProject();
	Fund f = new Fund();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return pro.readProjects();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProject(@FormParam("projectname") String projectname,
			 @FormParam("doclinks") String doclinks,
			 @FormParam("description") String description,
			 @FormParam("projectType") String projectType
			 )
			{ 
		String output = pro.insertProject(projectname, doclinks, description, projectType); 
		return output;
			}
	
	@GET
	@Path("/{ProId}")
	@Produces(MediaType.TEXT_HTML)
	public String readFundById(@PathParam("ProId") String ProId){
		
		String output = f.readFundById(ProId);
		//String output = pro.readProjectById(ProId);
		return output;
	}
	
}
