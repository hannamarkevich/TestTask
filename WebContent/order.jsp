<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Info</title>
</head>
<body>
<form action="ProcessOrder" method="get">
<font color="red"><%=request.getAttribute("message")==null?"":request.getAttribute("message") %></font> 
<%=request.getParameter("id")==null ? "Add new order" : "ID:"+request.getParameter("id")%><br>
<input type="hidden" name="id" value="<%=request.getParameter("id") %>"><br>
Date:<%=request.getParameter("date") %><br>
Amount:<input type="text" name="amount" value="<%=request.getParameter("amount")==null?"":request.getParameter("amount")%>"><br>
Status:<p><input type="radio" name="status" value="Completed"> Completed</input></p>
<p><input type="radio" name="status" value="In Progress"> In Progress</input></p>
<p><input type="radio" name="status" value="Cancelled"> Cancelled</input></p>
<input type="hidden" name="carId" value="<%=request.getParameter("carId")%>"><br>

<input type="submit"name="save" value="Save changes">
<% if(request.getParameter("id")!=null)
{
	out.println("<input type=\"submit\" name=\"delete\" value=\"Delete order\"><br>");
}
%>
</form>
</body>
</html>