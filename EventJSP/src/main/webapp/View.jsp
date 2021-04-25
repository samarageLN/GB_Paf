<%@page import="com.ViewItem"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<style>
           body{
               margin: 0;
               padding: 0;
               background: rgb(63, 150, 231);
               background-size: 280%;
               background-position: -480px 0px;


           }
           .main{
               width: 400px;
               margin: 100px auto 0px auto;
           }
            .h2{
                text-align: center;
                padding: 20px;
                font-family: sans-serif;

            }
            .reg{
                background-color: rgba(0, 0, 0, 0.5);
                width: 150%;
                font-size: 18px;
                border-radius: 10px;
                border:  1px solid(0,0,0,0.3);
                color: #fff;

            }
            form#register{
                margin: 40px;
            }
            .label{
                font-family: sans-serif;
                font-size: 18px;
                font-style: italic;
            }
            input#name{
                width: 300px;
                border: 1px solid #ddd;
                border-radius: 3px;
                outline: 0;
                padding: 7px;
                background-color: white;

            }
            .abc{
                display: flex;
                justify-content: space-between;
            }
            .button{
                width: 180px;
                color:black;
                font-size: 20px;
                padding: 12px 0;
                background:red;
                border: 0;
                border-radius: 20px;
                outline: none;
                margin-top: 30px;
                margin-left: 30px;
   
            }
            .table{
            padding-right:20px}
          
           

       </style>
</head>
<body>
<div class="main">
<div class="reg">
                 
                      <form id="register" method="post" action="View.jsp">  
                        
                        <h2 class="h2">Show All</h2>
						<div class="table">
                        <%
						 ViewItem itemObj = new ViewItem(); 
						 out.print(itemObj.readItems()); 
						%>
						</div>
						
<br><br>
                   </form>
                </div>
            </div>
           


</body>
</html>