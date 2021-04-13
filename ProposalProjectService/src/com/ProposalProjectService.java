package com;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import model.ProposalProject;

@Path("/Project")

public class ProposalProjectService {

	ProposalProject pro = new ProposalProject();
	
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
	 

	
}
