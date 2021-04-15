<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
 <title>online chatting</title>
  <link rel="stylesheet" type="text/css" href="css/style.css" />
 
</head>
<body>
<%
String var=(String)request.getAttribute("handler");
String var_2=(String)application.getAttribute("message");

 

 %>
 <%
 final String name=var;
  %>
 <div id="main_chat">
Welcome
<div id="user name">
  <%if(name!=null) {%>
   <%=name%>
   <%} %>
   </div>

 
 <div id='screen'>

   <div id='output'>
   <%if(var_2!=null) {%>
   <%=var_2%>
   <%} %>
   </div>
   <div id='feedback'></div>
 </div>

<div>
 
  <input id='message' type='text'  name="message" placeholder="message"/> <br>
  
	  <input id='send' type="button" value='send' onclick="doPost()"/>	

		</div>
 </div>
 <script>
 var input = document.getElementById("message");
input.addEventListener("keyup", function(event) {
  if (event.keyCode === 13) {
   event.preventDefault();
   document.getElementById("send").click();
   
  }
});
  var messagesWaiting = false;
 function loadAsyn() {
 if(!messagesWaiting)
 var xhttp=new XMLHttpRequest();
messagesWaiting=true;
 xhttp.onreadystatechange=function(){
 
 if(this.readyState==4 && this.status==200)
 {messagesWaiting=false;
    var contentElement = document.getElementById("output");


 contentElement.innerHTML=contentElement.innerHTML+xhttp.responseText;
 

 
 }
 };
  
 xhttp.open("GET", "MediumServlet?t="+new Date(), true);
 //xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send();
 }
 function doPost()
 {
 
 var xhttp=new XMLHttpRequest();
   var nameText = escape(document.getElementById("user name").innerHTML);
 var messageText = escape(document.getElementById("message").value);
 xhttp.open("POST", "MediumServlet?t="+new Date(), false);
 xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send("handler="+nameText+"&message="+messageText);
  document.getElementById("message").value='';
 }
 var messagesWaitingLoginPage = false;
 function loadAsynLoginPage() {
 if(!messagesWaitingLoginPage)
 var xhttp=new XMLHttpRequest();
messagesWaitingLoginPage=true;
 xhttp.onreadystatechange=function(){
 
 if(this.readyState==4 && this.status==200)
 {messagesWaitingLoginPage=false;
    var contentElement = document.getElementById("output");

var str = new String(xhttp.responseText);

 contentElement.innerHTML=contentElement.innerHTML+"<br>"+str.fontcolor( "red" );
 

 
 }
 };
  
 xhttp.open("GET", "LoginPage?t="+new Date(), true);
 //xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send();
 }
 setInterval(loadAsyn, 1000);
 setInterval(loadAsynLoginPage, 1000);
 
 </script>
 


 
 
</body>
</html>