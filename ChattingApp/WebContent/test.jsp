<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
 <title>online chatting</title>
  <link rel="stylesheet" type="text/css" href="css/style.css" />
 
</head>
<body>
 <div id="main_chat">

  
 
 <div id='screen'>
   <div id='output' ></div>
   <div id='feedback'></div>
 </div>
<%
String var=(String)request.getAttribute("handler");
out.println(var);

 %>
<div>

 


 
 
</body>
</html>