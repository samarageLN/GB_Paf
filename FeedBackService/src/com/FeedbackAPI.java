package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Feedback;

@Path("/Feedbacks")
public class FeedbackAPI {

	// implement the read all feedbacks method

	Feedback fObj = new Feedback();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFeedbacks() {

		return fObj.viewFeedbacks();
	}

	// implement the read feedbacks method for given project id
	// this will call by the innovationprojectService

	@POST
	@Path("/feeds")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String readFeadbacksforaProject(String projJsonString) {

		JsonObject jsonObj = new JsonParser().parse(projJsonString).getAsJsonObject();
		int i_projectid = jsonObj.get("iProjectID").getAsInt();
		return fObj.readFeedBackForProject(i_projectid);
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String putFeedback_(String FeedbackDetails) {

		// create a JSON Object

		JsonObject FeedbackObject = new JsonParser().parse(FeedbackDetails).getAsJsonObject();

		int i_projectid = FeedbackObject.get("iProjectID").getAsInt();
		String posted_by = FeedbackObject.get("postedBy").getAsString();
		String f_description = FeedbackObject.get("description").getAsString();

		String output = fObj.putFeedback(i_projectid, posted_by, f_description);
		return output;

	}

	// implement the delete feedback method

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete_Feedback(String feedbackidJSON) {

		JsonObject FeedbackObject = new JsonParser().parse(feedbackidJSON).getAsJsonObject();

		int feedback_id = FeedbackObject.get("feedbackID").getAsInt();
		String output = fObj.deleteFeedback(feedback_id);
		return output;
	}

	// implement the feedback update method

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String update_Feedback(String FeedbackDetails) {

		JsonObject FeedbackObject = new JsonParser().parse(FeedbackDetails).getAsJsonObject();

		int feedback_id = FeedbackObject.get("feedbackID").getAsInt();
		int i_projectid = FeedbackObject.get("iProjectID").getAsInt();
		String posted_by = FeedbackObject.get("postedBy").getAsString();
		String f_description = FeedbackObject.get("description").getAsString();

		String output = fObj.updateFeedback(feedback_id, i_projectid, posted_by, f_description);
		return output;

	}

}
