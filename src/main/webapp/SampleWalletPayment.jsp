<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CITIPAY</title>
</head>
<body>
<h1>CitiWallet</h1>
<form action="PaymentServlet">
<pre>Operator Number:<input type="tel" name="merchantid" ><br /></pre>
<pre>Mobile Number  :<input type="tel" name="mobileno" ><br /></pre>
<pre>Amount         :<input type="number" name="amount"><br /></pre>
<input type="hidden" name="redirect_url" value="http://cslh2018:8090/webciti/SampleWalletPayment.jsp">
<button type="submit">submit</button>

<%
String result=(String)request.getParameter("status");
if(result!=null){
	out.println(result); 
}
%>
</form>
</body>
</html>