<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Car Info</title>
</head>
<body>
<form action="ProcessCar" method="get">
<font color="red"><%=request.getAttribute("message")==null?"":request.getAttribute("message") %></font> 
<%String id=request.getParameter("id"); %>
<%=id==null ? "Add new car" : "ID:"+id%><br>
<% request.setAttribute("id",id);
 String clientId=request.getParameter("clientId");
System.out.println(request.getParameter("clientId"));%>

<input type="hidden" name="id" value="<%=id%>">
<input type="hidden" name="clientId" value="<%=clientId%>">
Make:<input type="text" name="make" value="<%=request.getParameter("make")==null ? "" : request.getParameter("make")%>"><br>
Model:<input type="text" name="model" value="<%=request.getParameter("model")==null ? "" : request.getParameter("model")%>"><br>
Year:<input type="text" name="year" value="<%=request.getParameter("year")==null ? "" : request.getParameter("year")%>"><br>
VIN:<input type="text" name="vin" value="<%=request.getParameter("vin")==null ? "" : request.getParameter("vin")%>"><br>
<input type="submit" name="save" value="Save changes"><br>
<% if(id!=null)
{
	out.println("<input type=\"submit\" name=\"ordersList\" value=\"Open Order's List\"><br>");
	out.println("<input type=\"submit\" name=\"delete\" value=\"Delete car\"><br>");
}
%>
</form>
</body>
</html>