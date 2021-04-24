package util;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Random;

public class Support {

	Random random = new Random();
	DBconnection dbConnection = new DBconnection();
	
	//method to return a random number 
	
	public int getRandomNumber() {
		
		do {
			
		    int number = random.nextInt(10000)*5;	
		    if(checkUnique(number)) {
		    	return number;
		    }
		
		}while(true);
			
	}
	
	public boolean checkUnique(int number) {
		boolean flag = false;
		String output ="";	
		try {

			Connection conn = dbConnection.connect();
			if (conn == null) {
				output = " Error while Connecting to the database";
			}
			
			String query = " select * from payments where paymentId = " + number;
			PreparedStatement stmt = conn.prepareStatement(query);

			// getting the result to the result set
			ResultSet resultSet = stmt.executeQuery(query);
		
			if(!resultSet.next()) {
				flag = true;
			}
			
		}catch(Exception e) {
			output = " Error!!";
		}
		
		return flag;
	}
	
	
	//validate card details
	public String validateCardDetails(int userId, String nameOnCard, String cardNumber, String expDate, int secCode,
			int postalCode) {
		String status="";
	
		Date expDatee = Date.valueOf(expDate);
		
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();

		java.sql.Date today = new java.sql.Date(currentDate.getTime());
		
		int secCodelength = String.valueOf(secCode).length();
	
		 if(nameOnCard.matches(".*\\d.*")) {
			 System.out.println("Card Name cannot contain Numbers ");
			 status="Card Name cannot contain Numbers!!";
		 }else if(expDatee.before(today)) {
			 System.out.println("The date cannot be past date");
			 status="The date cannot be past date";
		 }else if(secCodelength>4) {
			 System.out.println("Security Code is not Valid");
			 status="Security Code is not Valid";
		 }else {
			 status ="OK";
		 }
		
		return status;

	}

		
}
