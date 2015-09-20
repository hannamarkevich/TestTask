<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="myPackage.Car, myPackage.DbLayer,java.util.ArrayList" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=request.getAttribute("title") %></title>
</head>
<body>
<form action="car.jsp" method="GET">
<%
System.out.println("id in carsList:"+request.getAttribute("clientId"));
	request.setAttribute("clientId", request.getAttribute("clientId"));
	ArrayList<Car> cars=(ArrayList<Car>)request.getAttribute("carsList");
	if(cars.size()>0){
	for(Car car:cars)
	{
		String carString=car.parseForHref();
		out.println("<a href=\"car.jsp?edit=true&"+carString+"&clientId="+request.getAttribute("clientId")+"\"   type=\"submit\">" + car.toString()
				+ "</a><br>");
	}
	}
	else
	{
		out.println("<h3>No car for this client</h3>");
	}
	out.println("<a href=\"car.jsp?clientId="+request.getAttribute("clientId")+"\" id=\"id\">Add new car</a><br>");
%>
</form>
</body>
</html>