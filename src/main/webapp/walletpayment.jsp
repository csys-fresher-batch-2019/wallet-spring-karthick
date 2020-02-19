<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="demo2.jsp"></jsp:include>
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
<%
String OperatorNo=(String) request.getParameter("merchantId");
long UserNo=Long.parseLong(request.getParameter("mobileNo"));
int Amount=Integer.parseInt(request.getParameter("amount"));
%>

<form action="PaymentServlet" method="post">
<div class="c"><class="thicker"><pre>Operator Number:<input type="text" name="sender" value="<%=OperatorNo%>" readonly><br /></pre>
<div class="c"><class="thicker"><pre>Mobile Number  :<input type="tel" name="receiver" value="<%=UserNo%>" readonly ><br /></pre>
<div class="c"><class="thicker"><pre>  Amount       :<input type="number" name="amount" value="<%=Amount%>" readonly><br /></pre>
<div class="c"><class="thicker"><pre>     Pin       :<input type="number" name="pinnumber" min="1000" max="9999" placeholder="Enter pin" required autofocus><br /></pre>
<br>
<button type="submit" class="btn btn-space" class="btn btn-primary" >Submit</button>
</form>

</body>
</html>