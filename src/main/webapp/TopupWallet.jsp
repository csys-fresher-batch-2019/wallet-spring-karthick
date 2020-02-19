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
    margin-right: 40px;
}
  
 
}
</style>
<body>
<pre>

 <!-- background-repeat: no-repeat;-->







</pre>
<h2>Topup Wallet</h2>
<br>
<form action="TopupServlet">
<div class="c"><class="thicker"><pre>Account Number  :<input type="number" name="accountnumber" maxlength="10" placeholder="Enter account number" autofocus required><br /></pre></div>
<div class="c"><class="thicker"><pre>Transfer Amount :<input type="number" name="amount" placeholder="Enter transfer amount" required><br /></pre></div>
<div class="c"><class="thicker"><pre>   Comments     :<input type="text" name="comments" placeholder="Enter any comments" required><br /></pre></div>
<br>
<button type="submit" class="btn btn-space" class="btn btn-primary" >Submit</button> 
</form>