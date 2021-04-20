package model;

import java.sql.*;


public class Login {
	public boolean validate_login(String uname,String password) {
		   try{           
		       Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
		       Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_account_service", "root", "");     
		       PreparedStatement pst = conn.prepareStatement("Select * from user_account where uname=? and password=?");
		       pst.setString(1, uname); 
		       pst.setString(2, password);
		       ResultSet rs = pst.executeQuery();                        
		       if(rs.next())            
		           return true;    
		       else
		           return false;            
		   }
		   catch(Exception e){
		       e.printStackTrace();
		       return false;
		   }       
		}
}	

