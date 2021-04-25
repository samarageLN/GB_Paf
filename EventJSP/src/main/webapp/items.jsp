<%@page import="com.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%
if (request.getParameter("Catagory") != null) 
 { 
 Item itemObj = new Item(); 
 String stsMsg = itemObj.insertItem(request.getParameter("Catagory"), 
 request.getParameter("Starting_Date"), request.getParameter("Closing_Date")); 
 session.setAttribute("statusMsg", stsMsg); 
 } 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
      <style>
      body {
        background-color: rgb(63, 150, 231);
      }
      input {
        border-top-style: hidden;
        border-right-style: hidden;
        border-left-style: hidden;
        border-bottom-style: double;
        background-color:   white;
      }
      
      .no-outline:focus {
        outline: none;
        font-size: 30px;
      }
      
      .aa{
          text-align: center;
          
      }
      

           
      .bb{
          font-size: 30px;
      }
      .h1{
          font-size: 50px;
      }
      .reg{
          background-color: rgba(0, 0, 0, 0.5);
          width: 50%;
          font-size: 18px;
          border-radius: 10px;
          border:  1px solid(0,0,0,0.3);
          color: #fff;
          margin-left: 350px;
          margin-top: 60px;
          margin-bottom: 30px;
          
      }
    </style>
</head>
<body>
        <form id="register" method="post" action="items.jsp">
        <div class="reg">
        <div class="aa">
           
            <br>
        <h1 class="h1">Add Event Information</h1>
      
        <div class="bb">
      <p>Category:</p>
      <input type="text" name="Catagory" class="no-outline" placeholder="Enter Category">
      <br>
      <p>Starting Date:</p>
      <input type="date" name="Starting_Date" class="no-outline" placeholder="Enter Something">
      <br>
      <p>Closing Date:</p>
      <input type="date" name="Closing_Date" class="no-outline" placeholder="Enter Something">
     <br><br>             
        <input type="Submit" name="email" id="name"
        placeholder="Email">
        <br><br>

     
      </div>
      </div>
      </div>
    </form>                   
           
<%
 out.print(session.getAttribute("statusMsg")); 
%> 
</body>
</html>