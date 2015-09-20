<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="myPackage.Client, myPackage.DbLayer,java.util.ArrayList" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Anna Markevich</title>
<script type="text/javascript">
	</script>
</head>
<body>

<form action="ClientsList" method="GET">
<font color="red"><%=request.getAttribute("message")==null?"":request.getAttribute("message") %></font>   
Name:<input type="text" name="first_name" />
Surname:<input type="text" name="surname" />
<input value="Search" type="submit" name="seach" />
</form>


</body>
</html>