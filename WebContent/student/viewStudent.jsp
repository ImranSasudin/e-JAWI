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
	<p>Welcome <b><c:out value="${student.studentName}" /></b></p>

	<p><b>Email:</b> <c:out value="<%=email%>" /></p>
	<p><b>Name:</b> <c:out value="${student.studentName}" /></p>
	<p><b>Address:</b> <c:out value="${student.studentAddress}" /></p>
	<p><b>Phone:</b> <c:out value="${student.studentPhone}" /></p>

    <br/><br/>
    <p><a href="StudentController?action=updateAccount&email=<c:out value="<%=email%>" />" class="btn btn-primary"><b>Update Account</b></a></p>
</body>
</html>