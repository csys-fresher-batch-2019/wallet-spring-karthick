<%
String infoMessage=(String)request.getAttribute("infoMessage");
if(infoMessage!=null){%>
<br>
	<h4><pre><font color="green"><%= infoMessage %> </font></pre></h4>
<%} 

String result=(String)request.getAttribute("errorMessage");
if(result!=null){%>
<br>
	<h4><pre><font color="red"><%= result %> </font></pre></h4>
<%} 

%>