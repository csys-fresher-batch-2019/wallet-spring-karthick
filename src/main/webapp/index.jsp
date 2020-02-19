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
    margin-right: 400px;
}
  
 
}
</style>
<body>
<pre>

 <!-- background-repeat: no-repeat;-->










</pre>

	<div class="btn-group">
		<div class="row">
			<div class="col-2"> </div>
			<div class="col-3">
				<a class="btn btn-primary"
					href="signin.jsp">Signin</a>
			</div>
			<div class="col">
				<button type="button" class="btn btn-secondary"
					class="btn dropdown-toggle" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">Signup</button>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="bussSignUp1.jsp">Business</a> <a
						class="dropdown-item" href="persSignUp1.jsp">Personal</a>
				</div>
			</div>
		</div>
	</div>