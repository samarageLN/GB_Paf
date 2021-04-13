package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import connection.Conn;

public class ProposalProject {
	

	Conn con = new Conn();

	
	public String insertProject(String projectname, String doclinks, String description, String projectType) {
		
		String output = "";

		try {
			Connection conn = con.connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			String query = "insert into proposalproject (`projectID`,`projectname`,`doclinks`,`description`,`projectType`,`status`)"
					+ "values (?,?,?,?,?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, projectname);
			preparedStmt.setString(3, doclinks);
			preparedStmt.setString(4, description);
			preparedStmt.setString(5, projectType);
			preparedStmt.setString(6, null);
			preparedStmt.execute();
			conn.close();
			
			output = "Inserted successfully";
			
		} catch (Exception e) {
			output = "Error while inserting";
			System.out.println(e.getMessage());
		}
		return output;
	}
	
	

	public String readProjects() {
		String output = "";
		try {
			Connection conn = con.connect();
			if (con == null) {
				return "Error while connecting to the database for reading";
			}

			output = "<table border ='1'>" + "<tr><th>project ID</th><th>Project Name</th><th>Document Links</th>"
					+ "<th>Description</th><th>Project Type</th><th>Update</th><th>Remove</th></tr>";

			String query = "select * from proposalProject";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String projectID = Integer.toString(rs.getInt("projectID"));
				String projectname = rs.getString("projectname");
				String doclinks = rs.getString("doclinks");
				String description = rs.getString("description");
				;
				String projectType = rs.getString("projectType");

				output += "<tr><td>" + projectID + "</td>";
				output += "<td>" + projectname + "</td>";
				output += "<td>" + doclinks + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + projectType + "</td>";

				output += "<td><input name='btnUpdate' " + " type='button' value='Update'></td>"
						+ "<td><form method='post' action='projects.jsp'>" + "<input name='btnRemove' "
						+ " type='submit' value='Remove'>" + "<input name='projectID' type='hidden' " + " value='"
						+ projectID + "'>" + "</form></td></tr>";
			}
			conn.close();
			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the projects.";
			System.err.println(e.getMessage());
		}

		return output;
	}
}
