<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
div.c {
  font-size: 150%;
}
h2 {
  font-size: 30px;
}
</style>
</head>
<style>
body {
  background-image: url('images/white.gif');
   text-align: center;
   height: 250px;
  background-position: center;
 background-repeat: no-repeat;
 background-size: contain;
  }
  
  .btn-space {
    margin-right: 25px;
}
  
 
}
</style>
<body>
<pre>

 <!-- background-repeat: no-repeat;-->





</pre>
<h2>Personal Signup</h2>
<br>
<body>
<form action="VerifyOTPServlet">
<%
long mobileNo =(Long)request.getAttribute("mobileNo");
int otp =Integer.parseInt(request.getAttribute("otp").toString());
%>
<div class="c"><class="thicker"><pre>Verification OTP :<input type="number" name="otp" placeholder="Enter OTP" autofocus required><br /></pre></div>
<input type="hidden" name="mobileNo" value="<%=mobileNo %>" readonly/>
<input type="hidden" name="sentotp" value="<%=otp %>" readonly/>
<br>
<h5><button type="submit">Verify</button></h5>
</form>

<%
String result=(String)request.getAttribute("errorMessage");
if(result!=null){%>
<br>
	<h2><pre><font color="red"><%= result %> </font></pre></h2>
<%} 

%>
</body>
</html>

