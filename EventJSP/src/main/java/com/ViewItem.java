package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewItem {
	
	public Connection connect()
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/event_db", "root", ""); 
	 //For testing
	 System.out.print("Successfully connected"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 
	}
	
	
	
	
	public String readItems() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<center><table border='1' ><tr><th>Event ID</th><th>Catagory</th>" +
	 "<th>Starting_Date</th>"+"<th>Closing_Date</th></tr>";
	 
	 String query = "select * from event_table;"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String EventID = Integer.toString(rs.getInt("EventID")); 
	 String Catagory = rs.getString("Catagory");
	 String Starting_Date = rs.getString("Starting_Date");
	 String Closing_Date = rs.getString("Closing_Date"); 

	 // Add into the html table
	 output += "<tr><td>" +EventID + "</td>"; 
	 output += "<td>" + Catagory + "</td>"; 
	 output += "<td>" + Starting_Date + "</td>"; 
	 output += "<td>" + Closing_Date + "</td>";  
	 // buttons
	 output += "</td></tr>"; 
	 } 
	 con.close(); 
	 
	 // Complete the html table
	 output += "</table></center>"; 
	 
	 
	 
	 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 

	
}
