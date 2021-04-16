package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;  


import util.DBConnection;

public class UserCard {
	//this will maintain the card details of the users 
	
	private int userId;
	private String nameOnCard;
	private String cardNumber;
	private String expireDate;
	private int securityCode;
	private int postalCode;
	
	public UserCard() {}
	
	//constructor
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
	//getters and setters
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
	
	//-------------methods for db operations-----
	
	public String insertCardDetails(int userId, String nameOnCard, String cardNo, String expDate, int secCode, int postalCode) {
		String output="";
		
		try {
			Connection conn = DBConnection.openConnection();
			if (conn == null) {
				output = " Error while Connecting to the database";
			}
			
			
			String query = " insert into UserCard values(?,?,?,?,?,?)";
			PreparedStatement preparedstatement = conn.prepareStatement(query);
			preparedstatement.setInt(1, userId);
			preparedstatement.setString(2, nameOnCard);
			preparedstatement.setString(3, cardNo);
			//convert string to date ....
			  
		    Date date1=Date.valueOf(expDate); 
			preparedstatement.setDate(4, date1);
			
			
			preparedstatement.setInt(5, secCode);
			preparedstatement.setInt(6, postalCode);
			
			
			preparedstatement.execute();
			conn.close();

			output = "Inserted SuccessFully";
			 
			
		}catch(Exception e) {
			
			output = "Error while Inserting ";
			e.printStackTrace();
		}
		                       
		return output;    
	}
		
	
	
	
	
}
