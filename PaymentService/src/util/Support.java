package util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		
}
