<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="myPackage.Client, myPackage.DbLayer,java.util.ArrayList" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Client's info</title>
</head>
<body>
<form action="ProcessClient" method="get">
<font color="red"><%=request.getAttribute("message")==null?"":request.getAttribute("message") %></font> 
ID:<%=request.getParameter("id")==null?"":request.getParameter("id") %><br>
<input type="hidden" name="id" value="<%=request.getParameter("id")%>">
Name:<input type="text" name="firstName" value="<%=request.getParameter("firstName")==null?"":request.getParameter("firstName") %>"><br>
Surname:<input type="text" name="lastName" value="<%=request.getParameter("lastName")==null?"":request.getParameter("lastName")%>"><br>
Date of Birth(format:"yyyy-MM-dd"):<input type="text" name="date" value="<%=request.getParameter("date")==null?"":request.getParameter("date")%>"><br>
Phone:<input type="text" name="phone" value="<%=request.getParameter("phone")==null?"":request.getParameter("phone") %>"><br>
Address:<input type="text" name="address" value="<%=request.getParameter("address")==null?"":request.getParameter("address") %>"><br>
Email:<input type="text" name="email" value="<%=request.getParameter("email")==null?"":request.getParameter("email") %>"><br>
<input type="submit" name="save" value="Save changes"><br>
<% if(request.getParameter("id")!=null)
{
	out.println("<input type=\"submit\" name=\"carsList\" value=\"Open Car's List\"><br>");
	out.println("<input type=\"submit\" name=\"delete\" value=\"Delete client\"><br>");
}
%>


</form>
</body>
</html>