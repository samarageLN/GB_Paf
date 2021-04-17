package com;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import model.Fund;
import model.ProposalProject;


/**
 * ProposalProjectService
 * 
 ********************************************************************************************************
 *  ###   Date             Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   14-04-2021       David        Created
 *    
 ********************************************************************************************************
 */


@Path("/Project")

public class ProposalProjectService {

	ProposalProject pro = new ProposalProject();
	Fund f = new Fund();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProjects() {
		return pro.readProjects();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProject(@FormParam("projectname") String projectname, @FormParam("doclinks") String doclinks,
			@FormParam("description") String description, @FormParam("projectType") String projectType) {
		String output = pro.insertProject(projectname, doclinks, description, projectType);
		return output;
	}

	@GET
	@Path("/{ProId}")
	@Produces(MediaType.TEXT_HTML)
	public String readFundById(@PathParam("ProId") String ProId) {
		String output = f.readFundById(ProId);
		return output;
	}

	@PUT
	@Path("/{ProId}/{Msg}")
	@Produces(MediaType.TEXT_HTML)
	public String getApproval(@PathParam("ProId") String ProId, @PathParam("Msg") String Msg) {

		String output = pro.getApproval(ProId, Msg);
		return output;
	}
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProjects(String proData)
	{
		Document doc = Jsoup.parse(proData,"",Parser.xmlParser());
		String projectID = doc.select("projectID").text();
		String output = pro.deleteProjects(projectID);
		return output;
	}

}
