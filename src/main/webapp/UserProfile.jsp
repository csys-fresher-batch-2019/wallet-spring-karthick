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
<form>
<br>
<br>
<pre>
<!--  <input type="submit" id="reg" class="btn btn-warning" value="Payment Activity" onclick="form.action='registerServlet';">-->
</pre>

<input type="submit" id="reg" class="btn btn-info" value="New Payment" onclick="form.action='Newpayment.jsp';">
<input type="submit" id="reg" class="btn btn-success" value="Balance Check" onclick="form.action='CheckBalance';">
<input type="submit" id="reg" class="btn btn-danger" value="Topup Wallet" onclick="form.action='TopupWallet.jsp';">
<pre></pre>
<div class="btn-group">
  <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Payment Activity
  </button>
   <div class="dropdown-menu">
    <a class="dropdown-item" href="TransactionMerchant">Merchant Payments</a>
    <a class="dropdown-item" href="TransactionDebited">Money Sent</a>
    <a class="dropdown-item" href="TransactionCredited">Money Received</a>
  </div>
</div>
</form>