<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<% String email = (String) session.getAttribute("currentSessionUser"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Account</title>
</head>
<body>
	<p>Welcome <b><c:out value="${parent.parentName}" /></b></p>

	<p><b>Email:</b> <c:out value="<%=email%>" /></p>
	<p><b>Name:</b> <c:out value="${parent.parentName}" /></p>
	<p><b>Address:</b> <c:out value="${parent.parentAddress}" /></p>
	<p><b>Phone:</b> <c:out value="${parent.parentPhone}" /></p>

    <br/><br/>
    <p><a href="ParentController?action=updateAccount&email=<c:out value="<%=email%>" />" class="btn btn-primary"><b>Update Account</b></a></p>
</body>
</html>