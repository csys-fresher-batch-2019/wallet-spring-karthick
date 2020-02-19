<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="citiipay.models.TransactionDetails"%>
<%@page import="java.util.ArrayList"%>
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
    margin-right: 40px;
}
  
 
}
</style>
<body>
<pre>

 <!-- background-repeat: no-repeat;-->









</pre>
<h2>Debited Transactions</h2>
<br>
<%
ArrayList<TransactionDetails> list =(ArrayList)request.getAttribute("list");
if(list.size()>0){
%><center>
<table border="2">
<thead>
<tr>
<th>S.no</th>
<th>Mobile Number</th>
<th>Credited to</th>
<th>Category</th>
<th>Transaction time</th>
<th>Transaction Amount</th>
<th>Status</th>
<th>Comments</th>
</tr>
</thead>
<tbody>
<%
int i = 1;
for (TransactionDetails ud : list) {
%>
<tr>

<td><%=i++%></td>
<td><%=ud.getSenderNumber()%></td>
<td><%=ud.getReceiverNumber()%></td>
<td><%=ud.getCategory()%></td>
<td><%=ud.getTransactionDate()%></td>
<td><%=ud.getTransactionAmount()%></td>
<td><%=ud.getTransactionStatus()%></td>
<td><%=ud.getComments()%></td>
</tr>

<%}%></tbody></table>  <%}else{ %>

<h4><pre><font color="red">No Records found</font></pre></h4><%} %>
<form>
</center>
</form>