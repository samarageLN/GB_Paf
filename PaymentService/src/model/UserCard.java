package model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.sql.DriverManager;

public class UserCard {
	// this will maintain the card details of the users

	private int userId;
	private String nameOnCard;
	private String cardNumber;
	private String expireDate;
	private int securityCode;
	private int postalCode;

	public UserCard() {
	}

	// constructor
	public UserCard(int userId, String nameOnCard, String cardNumber, String expireDate, int securityCode,
			int postalCode) {
		super();
		this.userId = userId;
		this.nameOnCard = nameOnCard;
		this.cardNumber = cardNumber;
		this.expireDate = expireDate;
		this.securityCode = securityCode;
		this.postalCode = postalCode;
	}

	// getters and setters
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public int getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	// connection
	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paymentsdb", "root", "");

		} catch (Exception e) {

			e.printStackTrace();
		}

		return con;
	}

	// -------------methods for db operations-----

	public String insertCardDetails(int userId, String nameOnCard, String cardNo, String expDate, int secCode,
			int postalCode) {
		String output = "";

		try {
			Connection conn = connect();
			if (conn == null) {
				output = " Error while Connecting to the database";
			}

			String query = " insert into UserCard values(?,?,?,?,?,?)";
			PreparedStatement preparedstatement = conn.prepareStatement(query);
			preparedstatement.setInt(1, userId);
			preparedstatement.setString(2, nameOnCard);
			preparedstatement.setString(3, cardNo);
			// convert string to date ....

			Date date1 = Date.valueOf(expDate);
			preparedstatement.setDate(4, date1);

			preparedstatement.setInt(5, secCode);
			preparedstatement.setInt(6, postalCode);

			preparedstatement.execute();
			conn.close();

			output = "Inserted SuccessFully";

		} catch (Exception e) {

			output = "Error while Inserting ";
			e.printStackTrace();
		}

		return output;
	}

//----------method to read card details of a specific user ------------------------------
	public String readCardDetails(int userId) {
		String output = "";

		try {

			Connection conn = connect();
			if (conn == null) {
				output = " Error while Connecting to the database";
			}

			output = "{ ";

			String query = " select * from usercard where userId = " + userId;
			PreparedStatement stmt = conn.prepareStatement(query);

			// getting the result to the result set
			ResultSet resultSet = stmt.executeQuery(query);

			while (resultSet.next()) {

				// read a row and storing them on our variables
				String userID = Integer.toString(resultSet.getInt("userId"));
				String nameOnCard = resultSet.getString("nameOnCard");
				String cardnumber = resultSet.getString("cardnumber");
				String expireDate = resultSet.getString("cardnumber");
				String securityCode = Integer.toString(resultSet.getInt("securityCode"));
				String postalCode = Integer.toString(resultSet.getInt("postalCode"));

				// creating jSON string
				output += "userId : \" " + userID + "\", ";
				output += "nameOnCard : \" " + nameOnCard + "\", ";
				output += "cardnumber : \" " + cardnumber + "\", ";
				output += "expireDate : \" " + expireDate + "\", ";
				output += "securityCode : \" " + securityCode + "\", ";
				output += "postalCode : \" " + postalCode + "\"}";

			} // end of while

			// closing the connection
			conn.close();

		} catch (Exception e) {
			output = " Error while reading the Items";
		}
		return output;
	}

	// method to remove the card details of a user

	public String removeCard(int userId) {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				output = "Error while Connecfting to the database ";

			}

			String query = "delete from usercard where userId=" + userId + " ";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.executeUpdate();
			con.close();

			output = " Card details Removed SuccessFully !!!";

		} catch (Exception e) {

			output = " error while deleting  card details";
			e.printStackTrace();
		}

		return output;
	}

//method to update  card details
	public String updateCardDetails(int userId, String nameOnCard, String cardNumber, String expireDate, int securityNumber, int postalCode) {

		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				output = "Error While Connecting to the Database!!!";
			}

			String query = "update usercard set nameonCard = ? , cardNumber = ? ,expireDate = ? , securityCode = ?, postalCode=? where userId = ?";

			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setString(1, nameOnCard);
			stmt.setString(2, cardNumber);
			Date date2 = Date.valueOf(expireDate);
			stmt.setDate(3, date2); 
			stmt.setInt(4, securityNumber);
			stmt.setInt(5, postalCode);
			stmt.setInt(6, userId);

			stmt.execute();
			con.close();

			output = "Sucessfully Updated !!!";

		} catch (Exception e) {

			output = "Error while Updating card details ";
			e.printStackTrace();
		}

		return output;
	}

}
