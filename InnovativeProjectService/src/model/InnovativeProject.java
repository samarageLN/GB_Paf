package model;

import java.sql.*;

import util.DBConnection;

public class InnovativeProject {

	private int innovativeProjectID;
	private String projectName;
	private double projectPrice;
	private String imageURL;
	private String projectType;
	private String projectDescription;
	private int researcherID;

	public InnovativeProject() {

	}

	public InnovativeProject(int innovativeProjectID, String projectName, double projectPrice, String imageURL,
			String projectType, String projectDescription, int researcherID) {
		super();
		this.innovativeProjectID = innovativeProjectID;
		this.projectName = projectName;
		this.projectPrice = projectPrice;
		this.imageURL = imageURL;
		this.projectType = projectType;
		this.projectDescription = projectDescription;
		this.researcherID = researcherID;
	}

	public int getInnovativeProjectID() {
		return innovativeProjectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public double getProjectPrice() {
		return projectPrice;
	}

	public String getImageURL() {
		return imageURL;
	}

	public String getProjectType() {
		return projectType;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public int getResearcherID() {
		return researcherID;
	}

	@Override
	public String toString() {
		return "InnovativeProject [innovativeProjectID=" + innovativeProjectID + ", projectName=" + projectName
				+ ", projectPrice=" + projectPrice + ", imageURL=" + imageURL + ", projectType=" + projectType
				+ ", projectDescription=" + projectDescription + ", researcherID=" + researcherID + "]";
	}

	// calling db connect method

	Connection con = DBConnection.connect();

	// insert method

	public String uploadProject(String pname, String pPrice, String pUrl, String pType, String pDescription,int pResearcherID) {

		String output = "";

		try {

			Connection con = DBConnection.connect();
			if (con == null) {

				return "Error while connecting to the database";

			}

			// insert query

			String query = "insert into innovativeprojects (`innovativeProjectID`,`projectName`,`projectPrice`,`imageURL`,`projectType`,`projectDescription`,`researcherID`)"
					+ " values(?, ?, ?, ?, ?, ?, ?)";

			// create a prepared statement

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, pname);
			preparedStmt.setDouble(3, Double.parseDouble(pPrice));
			preparedStmt.setString(4, pUrl);
			preparedStmt.setString(5, pType);
			preparedStmt.setString(6, pDescription);
			preparedStmt.setInt(7, pResearcherID);

			// execute the statement

			preparedStmt.execute();

			// close the connection

			con.close();

			output = "Inserted Successfully";

		} catch (Exception e) {

			output = "Error while inserting";
			System.out.println(e.getMessage());

		}

		return output;
	}// end of insert method

}