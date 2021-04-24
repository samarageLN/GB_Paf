package model;

import java.sql.*;

public class AdminLogin {

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

		public boolean validate_Adminlogin(String uname,String password) {
				String output = "";
				Connection con=null;
			   try{           
				    con = connect();
					if (con == null) {
						output= "Error while connecting to the database for updating.";
					}
					
			       PreparedStatement pst = con.prepareStatement("Select * from admin_account where uname=? and password=?");
			       pst.setString(1, uname); 
			       pst.setString(2, password);
			       ResultSet rs = pst.executeQuery();
			       boolean a=rs.next();
			      con.close();
			       if(a) {
			       
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
}
