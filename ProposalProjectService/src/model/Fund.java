package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.Conn;

public class Fund  {

	Conn con = new Conn();
	
public String insertFunds(String proID, String actualAmount) {
		
		String output = "";

		try {
			Connection conn = con.connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			String query = "insert into funds (`fundID`,`proID`,`actualAmount`,`currentTotalFunds`)"
					+ "values (?,?,?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, Integer.parseInt(proID));
			preparedStmt.setString(3, actualAmount);
			preparedStmt.setDouble(4, 0.00);
			preparedStmt.execute();

			conn.close();
			
			output = "Inserted successfully";
			
		} catch (Exception e) {
			output = "Error while inserting";
			System.out.println(e.getMessage());
		}
		return output;
	}

	//public void insertFunds(String projectID, String actualAmount, String currentTotalFunds) {
		// TODO Auto-generated method stub
		
	//}
}
