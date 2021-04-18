package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

		// implement the read one particular feedback method

		@GET
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_HTML)
		public String readOneFeedback(String feedbackidJSON) {

			// create a JSON Object

			JsonObject FeedbackObject = new JsonParser().parse(feedbackidJSON).getAsJsonObject();

			int feedback_id = FeedbackObject.get("feedbackID").getAsInt();
			return fObj.readOneFeedback(feedback_id);
		}
	
	
	// implement the insert feedback method

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

}
