package model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;

import java.util.Calendar;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import util.DBconnection;
import util.Support;

public class Payment {

	private int paymentid;
	private int referenceNumber;
	private String cardNumber;
	private String paidBy;
	private String paidFor;
	private String type;
	private double amount;
	private String date;
	private String time;
	
	UserCard userCard = new UserCard();
	Support support = new Support(); // to get a random unique number

	// constructors
	public Payment() {

	}

	public Payment(int paymentid, int referenceNumber, String cardNumber, String paidBy, String paidFor, String type,
			double amount, String date, String time) {
		super();
		this.paymentid = paymentid;
		this.referenceNumber = referenceNumber;
		this.cardNumber = cardNumber;
		this.paidBy = paidBy;
		this.paidFor = paidFor;
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.time = time;
	}

	// getters and setters
	public int getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(int paymentid) {
		this.paymentid = paymentid;
	}

	public int getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(int referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getPaidBy() {
		return paidBy;
	}

	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}

	public String getPaidFor() {
		return paidFor;
	}

	public void setPaidFor(String paidFor) {
		this.paidFor = paidFor;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

	// ----------------------------------DB Operations ---------------------------

	// Obtaining Database connection
	DBconnection dbConnection = new DBconnection();

	// method for insert payment/transaction details
	public String insertPaymentDetails(String paidBy, int paidUserid, String mailaddress, int projid,
			String projectName, String type, double amount) {
		String output = "";		
		int referencNumber;

		try {
			Connection conn = dbConnection.connect();
			if (conn == null) {
				output = " Error while Connecting to the database";
			}

			String query = " insert into payments(`paymentId`, `referenceNumber`, `cardNumber`, `paidBy`,`paidUserId`,`paidFor`, `type`, `amount`, `date`, `time`)"
					+ " values(?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement preparedstatement = conn.prepareStatement(query);

			preparedstatement.setInt(1, 0);
			referencNumber = support.getRandomNumber();
			preparedstatement.setInt(2, referencNumber);			
			preparedstatement.setString(3, userCard.getCardNumber(paidUserid));
			preparedstatement.setString(4, paidBy);
			preparedstatement.setInt(5, paidUserid);
			preparedstatement.setString(6, projectName);
			preparedstatement.setString(7, type);
			preparedstatement.setDouble(8, amount);

			// create a java calendar instance
			Calendar calendar = Calendar.getInstance();
			java.util.Date currentDate = calendar.getTime();

			// now, create a java.sql.Date from the java.util.Date
			java.sql.Date date = new java.sql.Date(currentDate.getTime());
			preparedstatement.setDate(9, date);

			// time
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String ctime = sdf.format(cal.getTime()).toString();
			System.out.println(ctime);
			Time currentTime = Time.valueOf(ctime);
			preparedstatement.setTime(10, currentTime);

			preparedstatement.execute();
			conn.close();

			output = "Payment details Inserted SuccessFully";

			notifyUserByMail(mailaddress, referencNumber);
			informProjectService(projid, paidUserid, date.toString(), ctime);

		} catch (Exception e) {

			output = "Error while Inserting Payment details";
			e.printStackTrace();
		}

		return output;
	}

	
	public void notifyUserByMail(String recepient, int referencNumber) {
		// communicate with Mail Service
		Client client = Client.create();
		String url = "http://localhost:8080/MailService/Mail_Service/email";
		WebResource resource = client.resource(url);
		String input = "{\"recepient\":\""+recepient+"\",\"refNumber\":\"" + referencNumber + "\"}";
		ClientResponse response = resource.type("application/json").post(ClientResponse.class, input);

	}

	public void informProjectService(int projId, int customerid, String date, String time) {

		Client client = Client.create();
		String url = "http://localhost:8080/InnovativeProjectService/InnovativeProject_Service/Purchase/confirm";
		WebResource resource = client.resource(url);
		String input = "{\"innovativeProjectID\":\"" + projId + "\",\"customerID\":\"" + customerid + "\",\"date\":\""
				+ date + "\",\"time\":\"" + time + "\"}";
		ClientResponse response = resource.type("application/json").post(ClientResponse.class, input);

	}

	// method to get the current logged user's info from accountService
	public String getCurrentLoggedUserinfo() {

		Client client = Client.create();

		WebResource webResource = client.resource("http://localhost:8080/UserAccounts/UserAccountService/User_logins");

		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

		String output = response.getEntity(String.class);

		return output;

	}

	// method to read all the payment details
	public String readAllPaymentDetails() {
		String output = "";

		try {

			Connection conn = dbConnection.connect();
			if (conn == null) {
				output = " Error while Connecting to the database";
			}

			String query = " select * from payments";
			Statement stmt = conn.createStatement();

			// getting the result to the result set
			ResultSet resultSet = stmt.executeQuery(query);

			while (resultSet.next()) {

				// read a row and storing them on our variables
				String paymentId = Integer.toString(resultSet.getInt("paymentId"));
				String refnumber = resultSet.getString("referenceNumber");
				String cardnumber = resultSet.getString("cardnumber");
				String paidBy = resultSet.getString("paidBy");
				String paidFor = resultSet.getString("paidFor");
				String type = resultSet.getString("type");
				double amount = resultSet.getDouble("amount");
				String date = resultSet.getString("date");
				String time = resultSet.getString("time");

				// creating jSON string
				output += "{ ";
				output += "paymentId : \" " + paymentId + "\", ";
				output += "refnumber : \" " + refnumber + "\", ";
				output += "cardnumber : \" " + cardnumber + "\", ";
				output += "paidBy : \" " + paidBy + "\", ";
				output += "paidFor : \" " + paidFor + "\", ";
				output += "type : \" " + type + "\", ";
				output += "amount : \" " + amount + "\", ";
				output += "date : \" " + date + "\", ";
				output += "time : \" " + time + "\"} \n ";

			} // end of while

			// closing the connection
			conn.close();

		} catch (Exception e) {
			output = "Error while reading the payment details";
			e.printStackTrace();
		}
		return output;
	}

	// method to read all the payment details of a specific user
	public String readPaymentDetails(int userId) {
		String output = "";

		try {

			Connection conn = dbConnection.connect();
			if (conn == null) {
				output = " Error while Connecting to the database";
			}

			String query = " select * from payments where paidUserId = " + userId;
			Statement stmt = conn.createStatement();

			// getting the result to the result set
			ResultSet resultSet = stmt.executeQuery(query);

			while (resultSet.next()) {

				// read a row and storing them on our variables
				String paymentId = Integer.toString(resultSet.getInt("paymentId"));
				String refnumber = resultSet.getString("referenceNumber");
				String cardnumber = resultSet.getString("cardnumber");
				String paidBy = resultSet.getString("paidBy");
				String paidFor = resultSet.getString("paidFor");
				String type = resultSet.getString("type");
				double amount = resultSet.getDouble("amount");
				String date = resultSet.getString("date");
				String time = resultSet.getString("time");

				// creating jSON string
				output += "{ ";
				output += "paymentId : \" " + paymentId + "\", ";
				output += "refnumber : \" " + refnumber + "\", ";
				output += "cardnumber : \" " + cardnumber + "\", ";
				output += "paidBy : \" " + paidBy + "\", ";
				output += "paidFor : \" " + paidFor + "\", ";
				output += "type : \" " + type + "\", ";
				output += "amount : \" " + amount + "\", ";
				output += "date : \" " + date + "\", ";
				output += "time : \" " + time + "\"} \n ";

			} // end of while

			// closing the connection
			conn.close();

		} catch (Exception e) {
			output = " Error while reading the payment details";
			e.printStackTrace();
		}
		return output;
	}

	// delete payment details -- allow only for admin
	public String removePayment(int paymentId) {
		String output = "";
		try {
			Connection con = dbConnection.connect();
			if (con == null) {
				output = "Error while Connecfting to the database ";

			}

			String query = "delete from payments where paymentId=" + paymentId + " ";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.executeUpdate();
			con.close();

			output = " payment details  Removed SuccessFully !!!";

		} catch (Exception e) {

			output = " error while deleting  payment details";
			e.printStackTrace();
		}

		return output;
	}

}
