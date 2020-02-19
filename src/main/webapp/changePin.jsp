<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="demo1.jsp"></jsp:include>
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
<h2>Pin updation</h2>
<br>
<form action="ChangePin">

<div class="c"><class="thicker"><pre>Pin Number    :<input type="password" name="pinnumber" min="1000" max="9999" placeholder="Enter pin" autofocus required><br /></pre></div>
<div class="c"><class="thicker"><pre>Reconfirm Pin :<input type="password" name="confirmation" placeholder="Re-enter pin" required><br /></pre></div>
<br>
<button type="submit" class="btn btn-space" class="btn btn-primary" >Submit</button>

<br>

<%
String result=(String)request.getAttribute("message");
if(result!=null){
	if(result.equals("Pin number updated")){%>
	<br>
		<h2><pre><font color="green"><%=result %> </font></pre></h2>
   <%}
	else{%>
	<br>
		<h2><pre><font color="red"><%=result %> </font></pre></h2>
		<% }
	}%>
</form>


</body>
</html>