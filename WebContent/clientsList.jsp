<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="myPackage.Client, myPackage.DbLayer,java.util.ArrayList" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Clients</title>
</head>
<body>


	<form action="client.jsp" method="GET">
<%
	
	ArrayList<Client> clients=(ArrayList<Client>)request.getAttribute("clientsList");
	if(clients.size()>0){
	for(Client client:clients)
	{
		String clientString=client.parseForHref();
		out.println("<a href=\"client.jsp?edit=true&"+clientString+"\"  value=\"ttttttt\" type=\"submit\">" + client.toString()
				+ "</a><br>");
	}
	}
	else
	{
		out.println("<h3>There is no such person in database</h3>");
	}
	out.println("<br><br><a href=\"client.jsp\">\"Add new client\"</a><br>");
	
%>
</form>
</body>
</html>