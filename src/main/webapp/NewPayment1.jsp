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
<h2>New Payment</h2>
<form action="WalletToWallet">
<%
long mobileNo =Long.parseLong((request.getParameter("ReceivermobileNo").toString()));

%>
<br>
<br>
<div class="c"><class="thicker"><pre> Transfer Amount :<input type="number" name="amount" required placeholder="Enter transfer amount" autofocus><br /></pre></div>
<div class="c"><class="thicker"><pre>        Comments :<input type="text" name="comments" required placeholder="Enter any comments" autofocus><br /></pre></div>
<input type="hidden" name="mobileNo" value="<%=mobileNo %>" readonly/>
<br>
<button type="submit" class="btn btn-space" class="btn btn-primary" >Submit</button>
<br>


</form>

</body>
</html>