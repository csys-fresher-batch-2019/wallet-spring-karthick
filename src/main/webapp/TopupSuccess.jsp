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
  font-size: 200%;
}
h1 {
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

<%
String result=(String)request.getAttribute("result");
if(result!=null){
	if(result.equals("Transaction Successfull")){%>
	<br>
		<h1><pre><font color="green"><%=result %> </font></pre></h1>
   <%}
	else{%>
	<br>
		<h1><pre><font color="red"><%=result %> </font></pre></h1>
		<% }
	}%>
