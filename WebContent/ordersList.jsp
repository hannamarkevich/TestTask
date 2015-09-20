<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import="myPackage.Order, myPackage.DbLayer,java.util.ArrayList" %>
<title>Insert title here</title>
</head>
<body>
<form action="order.jsp" method="GET">
<%
	
	ArrayList<Order> orders=(ArrayList<Order>)request.getAttribute("ordersList");
	if(orders.size()>0){
	for(Order order:orders)
	{
		String orderString=order.parseForHref();
		out.println("<a href=\"order.jsp?edit=true&"+orderString+"\"   type=\"submit\">" + order.toString()
				+ "</a><br>");
	}
	}
	else
	{
		out.println("<h3>No orders for this car</h3>");
	}
	out.println("<a href=\"order.jsp?carId="+request.getParameter("id")+"\"  id=\"id\">Add new order</a><br>");
%>
</form>
</body>
</html>