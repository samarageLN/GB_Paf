package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Feedback {

	private int feedbackID;
	private int iProjectID;
	private String postedBy;
	private String description;
	private String date;
	private String time;

	public Feedback() {
		super();
	}

	public Feedback(int feedbackID, int iProjectID, String postedBy, String description, String date, String time) {
		super();
		this.feedbackID = feedbackID;
		this.iProjectID = iProjectID;
		this.postedBy = postedBy;
		this.description = description;
		this.date = date;
		this.time = time;
	}

	public int getFeedbackID() {
		return feedbackID;
	}

	public void setFeedbackID(int feedbackID) {
		this.feedbackID = feedbackID;
	}

	public int getiProjectID() {
		return iProjectID;
	}

	public void setiProjectID(int iProjectID) {
		this.iProjectID = iProjectID;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Feedback [feedbackID=" + feedbackID + ", iProjectID=" + iProjectID + ", postedBy=" + postedBy
				+ ", description=" + description + ", date=" + date + ", time=" + time + "]";
	}

	// DB Connection method

	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/feedbackdb", "root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	// insert method

	public String putFeedback(int iProjectID, String FpostedBy, String Fdescription) {

		String output = "";

		// connect to the database

		try {

			Connection con = connect();

			if (con == null) {

				return "Error while connecting to the database";

			}

			// insert query

			String query = "insert into feedback (`feedbackID`,`iProjectID`,`postedBy`,`description`,`date`,`time`)"
					+ " values(?, ?, ?, ?, ?, ?)";

			// create a prepared statement

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, iProjectID);
			preparedStmt.setString(3, FpostedBy);
			preparedStmt.setString(4, Fdescription);

			// create a java calendar instance

			Calendar calendar = Calendar.getInstance();
			java.util.Date currentDate = calendar.getTime();

			// now, create a java.sql.Date from the java.util.Date
			java.sql.Date date = new java.sql.Date(currentDate.getTime());

			preparedStmt.setDate(5, date);

			// time
			Calendar cal = Calendar.getInstance();

			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

			String ctime = sdf.format(cal.getTime()).toString();

			System.out.println(ctime);

			Time currentTime = Time.valueOf(ctime);

			preparedStmt.setTime(6, currentTime);

			// execute the statement

			preparedStmt.execute();

			// close the connection

			con.close();

			output = "Feedback inserted Successfully";

		} catch (Exception e) {

			output = "Error while inserting";
			System.out.println(e.getMessage());

		}

		return output;
	}// end of insert method

	// read all feedbacks method

	public String viewFeedbacks() {

		String output = "";

		// connect to the database

		try {

			Connection con = connect();

			if (con == null) {

				return "Error while connecting to the database for reading";

			}

			// read feedbacks from DB and assign values for result set variable

			String query = "select* from feedback" ;
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set

			while (rs.next()) {

				String feedback_id = Integer.toString(rs.getInt("feedbackID"));
				String iproject_id = Integer.toString(rs.getInt("iProjectID"));
				String f_postedby = rs.getString("postedBy");
				String f_description = rs.getString("description");
				String f_date = rs.getString("date");
				String f_time = rs.getString("time");

				// create JSON string

				output += "{";

				output += "feedbackID : \" " + feedback_id + "\", ";
				output += "iProjectID : \" " + iproject_id + "\", ";
				output += "postedBy : \" " + f_postedby + "\", ";
				output += "description : \" " + f_description + "\", ";
				output += "date : \" " + f_date + "\", ";
				output += "time : \" " + f_time + "\" }";

				output += "\n";

			}
			con.close();

		} catch (Exception e) {

			output = "Error while reading feedbacks";
			System.out.println(e.getMessage());

		}
		return output;
	}// end of read method

	// read feedbacks for project method

	public String readFeedBackForProject(int i_projID) {

		String output = "";

		// connect to the database

		try {

			Connection con = connect();

			if (con == null) {

				return "Error while connecting to the database for reading";

			}

			// read particular feedback from DB and assign values for result set variable

			String query = "select * from feedback where iProjectID =" + i_projID;
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set

			while (rs.next()) {

				String feedback_id = Integer.toString(rs.getInt("feedbackID"));
				String iproject_id = Integer.toString(rs.getInt("iProjectID"));
				String f_postedby = rs.getString("postedBy");
				String f_description = rs.getString("description");
				String f_date = rs.getString("date");
				String f_time = rs.getString("time");

				// create a JSON String

				output += "{";

				output += "feedbackID : \" " + feedback_id + "\", ";
				output += "iProjectID : \" " + iproject_id + "\", ";
				output += "postedBy : \" " + f_postedby + "\", ";
				output += "description : \" " + f_description + "\", ";
				output += "date : \" " + f_date + "\", ";
				output += "time : \" " + f_time + "\" }";

				output += "\n";

			}
			con.close();

		} catch (Exception e) {

			output = "Error while reading feedback";
			System.out.println(e.getMessage());

		}
		return output;
	}// end of read one particular feedback method

	// delete feedback method

	public String deleteFeedback(int feedbackid) {

		String output = "";

		// connect to the database

		try {

			Connection con = connect();

			if (con == null) {

				return "Error while connecting to the database for deleting";

			}

			// delete query

			String query = "delete from feedback where feedbackID = " + feedbackid;

			// create a prepared statement

			PreparedStatement stmt = con.prepareStatement(query);

			// execute the statement

			stmt.executeUpdate();
			output = " Feedback deleted  successfully";
			con.close();

		} catch (Exception e) {

			output = "Error while deleting feedback";
			System.out.println(e.getMessage());
		}
		return output;
	}// end of remove method

	// update feedback method

	public String updateFeedback(int feedbackid, int iProjectID, String FpostedBy, String Fdescription) {

		String output = "";

		try {

			Connection con = connect();
			if (con == null) {

				output = "Error while connecting to the database for updating feedback";

			}

			// update query

			String query = "update feedback set iProjectID = ?, postedBy = ?, description = ?, date = ?,time= ? where feedbackID = ?";

			// create a prepare statement

			PreparedStatement stmt = con.prepareStatement(query);

			// binding values

			stmt.setInt(1, iProjectID);
			stmt.setString(2, FpostedBy);
			stmt.setString(3, Fdescription);

			// create a java calendar instance

			Calendar calendar = Calendar.getInstance();
			java.util.Date currentDate = calendar.getTime();

			// now, create a java.sql.Date from the java.util.Date

			java.sql.Date date = new java.sql.Date(currentDate.getTime());

			stmt.setDate(4, date);

			// time
			Calendar cal = Calendar.getInstance();

			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

			String ctime = sdf.format(cal.getTime()).toString();

			System.out.println(ctime);

			Time currentTime = Time.valueOf(ctime);

			stmt.setTime(5, currentTime);

			stmt.setInt(6, feedbackid);

			stmt.execute();

			output = "feedback updated  successfully";

			// close the connection

			con.close();

		} catch (Exception e) {

			output = "Error while updating feedback";
			System.out.println(e.getMessage());
			e.printStackTrace();

		}

		return output;

	}// end of update method
}
