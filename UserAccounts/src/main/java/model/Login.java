package model;

import java.sql.*;


public class Login {
	
	// A common method to connect to the DB
	
		private Connection connect() {
			Connection con = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				// Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_account_service", "root", "");
			}

			catch (Exception e) {
				e.printStackTrace();
			}

			return con;
		}
		
		
		//method to validate user inputs for login

	public boolean validate_login(String uname,String password) {
			String output = "";
			Connection con=null;
		   try{           
			    con = connect();
				if (con == null) {
					output= "Error while connecting to the database for updating.";
				}
				
		       PreparedStatement pst = con.prepareStatement("Select * from user_account where uname=? and password=?");
		       pst.setString(1, uname); 
		       pst.setString(2, password);
		       ResultSet rs = pst.executeQuery();
		      boolean a=rs.next();
		      con.close();
		       if(a) {
		    	   loggingTheUser(uname);
		    	   System.out.println(uname);
		       
		           return true;  
		       }
		       else {
		    	   return false;  
		      
		       }
		      
		   }
		   catch(Exception e){
		       e.printStackTrace();
		       return false;
		   } 
		  
		}
	
	//method to display logged in users
	
	public String loggingTheUser(String Username) {
		String output = "";
		//Db connection
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
		String query="UPDATE user_account SET isLoggedIn=? WHERE uname=?";
		System.out.println("inside logging the user");
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setBoolean(1,true);
		preparedStmt.setString(2,Username);
		
		// execute the statement
					preparedStmt.execute();
					con.close();
					output = " login to the system successfully.";
				} catch (Exception e) {
					output = "Error while updating logging ststus.";
					System.err.println(e.getMessage());
				}

		
		return output;	
		
	}
	
	
	//method for display logged out users 

	public String logoutTheuser(String UserName) { 
		String output = "";
		//Db connection
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			String query="UPDATE user_account SET isLoggedIn=? WHERE uname=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setBoolean(1,false);
			preparedStmt.setString(2,UserName);
	// execute the statement
				preparedStmt.execute();
				con.close();
				output = "logout from system successfully.";
			} catch (Exception e) {
				output = "Error while updating logging status.";
				System.err.println(e.getMessage());
			}

	
	return output;	
	
	}
	
	//logged in method
	public String getLoggedUserInfo() { 

		String output = "";

		// connect to the database

		try {

		Connection con = connect();

		if (con == null) {

		return "Error while connecting to the database for reading";

		}

		String query = "select* from user_account where isLoggedIn="+true;
		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery(query);

		// iterate through the rows in the result set

		while (rs.next()) {

		String uid = Integer.toString(rs.getInt("uid"));
		String uname = rs.getString("uname");
		String password = rs.getString("password");
		String email = rs.getString("email");
		String age = Integer.toString(rs.getInt("age"));
		String address = rs.getString("address");
		String type = rs.getString("type");
		Boolean isLoggedIn=rs.getBoolean("isLoggedIn");

		// create a JSON String

		output = "{\"UId\":\"" + uid + "\",\"UserName\":\"" + uname + "\",\"Password\":\""
		+ password + "\",\"Email\":\""+email+"\",\"Age\":\""+age+"\",\"Address\":\""+address+"\",\"Type\":\""+type+"\",\"LoggedIn\":\""+isLoggedIn+"\"}";

		 
		}
		con.close(); 

		} 
		catch (Exception e) {

			output = "Error while getting logged user information";
			System.out.println(e.getMessage());
		}
		
		return output;
	}

}

